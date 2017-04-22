package org.telegram.mtproto.schedule;

import org.telegram.mtproto.CallWrapper;
import org.telegram.mtproto.MTProto;
import org.telegram.mtproto.log.Logger;
import org.telegram.mtproto.time.TimeOverlord;
import org.telegram.mtproto.tl.MTInvokeAfter;
import org.telegram.mtproto.tl.MTMessage;
import org.telegram.mtproto.tl.MTMessagesContainer;
import org.telegram.mtproto.tl.MTMsgsAck;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:51
 */
public class Scheduller {

    // Share identity values across all connections to avoid collisions
    private static final AtomicInteger messagesIds = new AtomicInteger(1);
    private static final ConcurrentHashMap<Long, Long> idGenerationTime = new ConcurrentHashMap<Long, Long>();
    private static final int SCHEDULLER_TIMEOUT = 15 * 1000;//15 sec
    private static final long CONFIRM_TIMEOUT = 60 * 1000;//60 sec
    private static final int MAX_WORKLOAD_SIZE = 3 * 1024;
    private static final int BIG_MESSAGE_SIZE = 1024;
    private static final long RETRY_TIMEOUT = 5 * 1000;
    private static final int MAX_ACK_COUNT = 5;
    private static final int PRIORITY_HIGH = 1;
    private static final int PRIORITY_NORMAL = 0;
    private static final int STATE_QUEUED = 0;
    private static final int STATE_SENT = 1;
    private static final int STATE_CONFIRMED = 2;
    private final String TAG;//  = "MTProtoScheduller";
    private SortedMap<Integer, SchedullerPackage> messages = Collections.synchronizedSortedMap(new TreeMap<Integer, SchedullerPackage>());
    private HashSet<Long> currentMessageGeneration = new HashSet<Long>();
    private HashSet<Long> confirmedMessages = new HashSet<Long>();
    private long firstConfirmTime;
    private long lastMessageId;
    private long lastDependId;
    private int seqNo;
    private CallWrapper wrapper;

    public Scheduller(MTProto mtProto, CallWrapper wrapper) {
        this.TAG = "MTProto#" + mtProto.getInstanceIndex() + "#Scheduller";
        this.wrapper = wrapper;
    }

    public synchronized void updateMessageId(long newLastMessageId) {
        if (newLastMessageId > lastMessageId) {
            lastMessageId = newLastMessageId;
        }
    }

    private synchronized long generateMessageId() {
        long messageId = TimeOverlord.getInstance().createWeakMessageId();
        if (messageId <= this.lastMessageId) {
            messageId = this.lastMessageId = this.lastMessageId + 4;
        }
        while (idGenerationTime.containsKey(messageId)) {
            messageId += 4;
        }
        this.lastMessageId = messageId;
        idGenerationTime.put(messageId, getCurrentTime());
        this.currentMessageGeneration.add(messageId);
        return messageId;
    }

    private synchronized int generateSeqNoWeak() {
        return this.seqNo * 2;
    }

    private synchronized int generateSeqNo() {
        int res = this.seqNo * 2 + 1;
        this.seqNo++;
        return res;
    }

    private synchronized void generateParams(SchedullerPackage schedullerPackage) {
        schedullerPackage.messageId = generateMessageId();
        schedullerPackage.seqNo = generateSeqNo();
        schedullerPackage.idGenerationTime = getCurrentTime();
        schedullerPackage.relatedMessageIds.add(schedullerPackage.messageId);
        schedullerPackage.generatedMessageIds.add(schedullerPackage.messageId);
    }

    private long getCurrentTime() {
        return System.nanoTime() / 1000000;
    }

    public long getMessageIdGenerationTime(long msgId) {
        if (idGenerationTime.containsKey(msgId)) {
            return idGenerationTime.get(msgId);
        }
        return 0;
    }

    public int postMessageDelayed(TLObject object, boolean isRpc, long timeout, int delay, int contextId, boolean highPrioroty) {
        int id = messagesIds.incrementAndGet();
        SchedullerPackage schedullerPackage = new SchedullerPackage(id);
        schedullerPackage.object = object;
        schedullerPackage.addTime = getCurrentTime();
        schedullerPackage.scheduleTime = schedullerPackage.addTime + delay;
        schedullerPackage.expiresTime = schedullerPackage.scheduleTime + timeout;
        schedullerPackage.ttlTime = schedullerPackage.scheduleTime + timeout * 2;
        schedullerPackage.isRpc = isRpc;
        schedullerPackage.queuedToChannel = contextId;
        schedullerPackage.priority = highPrioroty ? PRIORITY_HIGH : PRIORITY_NORMAL;
        schedullerPackage.isDepend = highPrioroty;
        schedullerPackage.supportTag = object.toString();
        schedullerPackage.serverErrorCount = 0;
        this.messages.put(id, schedullerPackage);
        return id;
    }

    public int postMessage(TLObject object, boolean isApi, long timeout) {
        return postMessageDelayed(object, isApi, timeout, 0, -1, false);
    }

    public int postMessage(TLObject object, boolean isApi, long timeout, boolean highPrioroty) {
        return postMessageDelayed(object, isApi, timeout, 0, -1, highPrioroty);
    }

    public synchronized void prepareScheduller(PrepareSchedule prepareSchedule, int[] connectionIds) {
        long time = getCurrentTime();

        // Clear packages for unknown channels
        outer:
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.queuedToChannel != -1) {
                for (int id : connectionIds) {
                    if (schedullerPackage.queuedToChannel == id) {
                        continue outer;
                    }
                }
                forgetMessage(schedullerPackage.id);
            }
        }

        // If there are no connections provide default delay
        if (connectionIds.length == 0) {
            prepareSchedule.setDelay(SCHEDULLER_TIMEOUT);
            prepareSchedule.setAllowedContexts(connectionIds);
            prepareSchedule.setDoWait(true);
            return;
        }

        long minDelay = SCHEDULLER_TIMEOUT;
        boolean allConnections = false;
        boolean doWait = true;
        HashSet<Integer> supportedConnections = new HashSet<Integer>();
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            boolean isPendingPackage = false;
            long packageTime = 0;

            if (schedullerPackage.state == STATE_QUEUED) {
                isPendingPackage = true;
                if (schedullerPackage.scheduleTime <= time) {
                    packageTime = 0;
                } else {
                    packageTime = Math.max(schedullerPackage.scheduleTime - time, 0);
                }
            } else if (schedullerPackage.state == STATE_SENT) {
                if (getCurrentTime() <= schedullerPackage.expiresTime) {
                    if (time - schedullerPackage.lastAttemptTime >= RETRY_TIMEOUT) {
                        isPendingPackage = true;
                        packageTime = 0;
                    }
                }
            }

            if (isPendingPackage) {
                if (schedullerPackage.queuedToChannel == -1) {
                    allConnections = true;
                } else {
                    supportedConnections.add(schedullerPackage.queuedToChannel);
                }

                if (packageTime == 0) {
                    minDelay = 0;
                    doWait = false;
                } else {
                    minDelay = Math.min(packageTime, minDelay);
                }
            }
	}
       	/*if (this.confirmedMessages.size() > MAX_ACK_COUNT) {
           	doWait = false;
           	allConnections = true;
        }*/
        prepareSchedule.setDoWait(doWait);
        prepareSchedule.setDelay(minDelay);

        if (allConnections) {
            prepareSchedule.setAllowedContexts(connectionIds);
        } else {
            Integer[] allowedBoxed = supportedConnections.toArray(new Integer[0]);
            int[] allowed = new int[allowedBoxed.length];
            for (int i = 0; i < allowed.length; i++) {
                allowed[i] = allowedBoxed[i];
            }
            prepareSchedule.setAllowedContexts(allowed);
        }
    }

    public synchronized void registerFastConfirm(long msgId, int fastConfirm) {
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            boolean contains = false;
            for (Long relatedMsgId : schedullerPackage.relatedMessageIds) {
                if (relatedMsgId == msgId) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                schedullerPackage.relatedFastConfirm.add(fastConfirm);
            }
        }
    }

    public int mapSchedullerId(long msgId) {
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.generatedMessageIds.contains(msgId)) {
                return schedullerPackage.id;
            }
        }
        return 0;
    }

    public void resetMessageId() {
        this.lastMessageId = 0;
        this.lastDependId = 0;
    }

    public void resetSession() {
        this.lastMessageId = 0;
        this.lastDependId = 0;
        this.seqNo = 0;
        this.currentMessageGeneration.clear();
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            schedullerPackage.idGenerationTime = 0;
            schedullerPackage.dependMessageId = 0;
            schedullerPackage.messageId = 0;
            schedullerPackage.seqNo = 0;
        }
    }

    public boolean isMessageFromCurrentGeneration(long msgId) {
        return this.currentMessageGeneration.contains(msgId);
    }

    public void resendAsNewMessage(long msgId) {
        resendAsNewMessageDelayed(msgId, 0);
    }

    public void resendAsNewMessageDelayed(long msgId, long delay) {
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.relatedMessageIds.contains(msgId)) {
                schedullerPackage.idGenerationTime = 0;
                schedullerPackage.dependMessageId = 0;
                schedullerPackage.messageId = 0;
                schedullerPackage.seqNo = 0;
                schedullerPackage.state = STATE_QUEUED;
                schedullerPackage.scheduleTime = getCurrentTime() + delay;
                Logger.d(this.TAG, "Resending as new #" + schedullerPackage.id);
            }
        }
    }

    public void resendMessage(long msgId) {
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.relatedMessageIds.contains(msgId)) {
                // schedullerPackage.relatedMessageIds.clear();
                schedullerPackage.state = STATE_QUEUED;
                schedullerPackage.lastAttemptTime = 0;
            }
        }
    }

    public int[] mapFastConfirm(int fastConfirm) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.state == STATE_SENT) {
                if (schedullerPackage.relatedFastConfirm.contains(fastConfirm)) {
                    res.add(schedullerPackage.id);
                }
            }
        }
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res2.length; i++) {
            res2[i] = res.get(i);
        }
        return res2;
    }

    public void onMessageFastConfirmed(int fastConfirm) {
        for (SchedullerPackage schedullerPackage : messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.state == STATE_SENT) {
                if (schedullerPackage.relatedFastConfirm.contains(fastConfirm)) {
                    schedullerPackage.state = STATE_CONFIRMED;
                }
            }
        }
    }

    public void onMessageConfirmed(long msgId) {
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.state == STATE_SENT) {
                if (schedullerPackage.relatedMessageIds.contains(msgId)) {
                    schedullerPackage.state = STATE_CONFIRMED;
                }
            }
        }
    }

    public void confirmMessage(long msgId) {
        synchronized (this.confirmedMessages) {
            this.confirmedMessages.add(msgId);
            if (this.firstConfirmTime == 0) {
                this.firstConfirmTime = getCurrentTime();
            }
        }
    }

    public boolean isMessaveConfirmed(long msgId) {
        synchronized (this.confirmedMessages) {
            return this.confirmedMessages.contains(msgId);
        }
    }

    public synchronized void forgetMessageByMsgId(long msgId) {
        int scId = mapSchedullerId(msgId);
        if (scId > 0) {
            forgetMessage(scId);
        }
    }

    public synchronized void forgetMessage(int id) {
        Logger.d(this.TAG, "Forgetting message: #" + id);
        this.messages.remove(id);
    }

    private synchronized ArrayList<SchedullerPackage> actualPackages(int contextId) {
        ArrayList<SchedullerPackage> foundedPackages = new ArrayList<SchedullerPackage>();
        long time = getCurrentTime();
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.queuedToChannel != -1 && contextId != schedullerPackage.queuedToChannel) {
                continue;
            }
            boolean isPendingPackage = false;

            if (schedullerPackage.ttlTime <= getCurrentTime()) {
                forgetMessage(schedullerPackage.id);
                continue;
            }

            if (schedullerPackage.state == STATE_QUEUED) {
                if (schedullerPackage.scheduleTime <= time) {
                    isPendingPackage = true;
                }
            } else if (schedullerPackage.state == STATE_SENT) {
                if (getCurrentTime() <= schedullerPackage.expiresTime) {
                    if (getCurrentTime() - schedullerPackage.lastAttemptTime >= RETRY_TIMEOUT) {
                        isPendingPackage = true;
                    }
                }
            }

            if (isPendingPackage) {
                if (schedullerPackage.serialized == null) {
                    try {
                        if (schedullerPackage.isRpc) {
                            schedullerPackage.serialized = this.wrapper.wrapObject((TLMethod) schedullerPackage.object).serialize();
                        } else {
                            schedullerPackage.serialized = schedullerPackage.object.serialize();
                        }
                    } catch (IOException e) {
                        Logger.e(this.TAG, e);
                        forgetMessage(schedullerPackage.id);
                        continue;
                    }
                }

                foundedPackages.add(schedullerPackage);
            }
        }
        return foundedPackages;
    }

    public synchronized PreparedPackage doSchedule(int contextId, boolean isInited) {
        ArrayList<SchedullerPackage> foundedPackages = actualPackages(contextId);

        synchronized (this.confirmedMessages) {
            if (foundedPackages.size() == 0 &&
                    (this.confirmedMessages.size() <= MAX_ACK_COUNT || (System.nanoTime() - this.firstConfirmTime) < CONFIRM_TIMEOUT)) {
                return null;
            }
        }

        boolean useHighPriority = false;

        for (SchedullerPackage p : foundedPackages) {
            if (p.priority == PRIORITY_HIGH) {
                useHighPriority = true;
                break;
            }
        }

        ArrayList<SchedullerPackage> packages = new ArrayList<SchedullerPackage>();

        if (useHighPriority) {
            Logger.d("Scheduller", "Using high priority scheduling");
            int totalSize = 0;
            for (SchedullerPackage p : foundedPackages) {
                if (p.priority == PRIORITY_HIGH) {
                    packages.add(p);
                    totalSize += p.serialized.length;
                    if (totalSize > MAX_WORKLOAD_SIZE) {
                        break;
                    }
                }
            }
        } else {
            int totalSize = 0;
            for (SchedullerPackage p : foundedPackages) {
                packages.add(p);
                Logger.d("Scheduller", "Prepare package: " + p.supportTag + " of size " + p.serialized.length);
                totalSize += p.serialized.length;
                Logger.d("Scheduller", "Total size: " + totalSize);
                if (totalSize > MAX_WORKLOAD_SIZE) {
                    break;
                }
            }
        }

        Logger.d(this.TAG, "Iteration: count: " + packages.size() + ", confirm:" + this.confirmedMessages.size());
        Logger.d(this.TAG, "Building package");
        if (foundedPackages.size() == 0 && this.confirmedMessages.size() != 0) {
            Long[] msgIds;
            synchronized (this.confirmedMessages) {
                msgIds = this.confirmedMessages.toArray(new Long[this.confirmedMessages.size()]);
                this.confirmedMessages.clear();
            }
            MTMsgsAck ack = new MTMsgsAck(msgIds);
            Logger.d(this.TAG, "Single msg_ack");
            try {
                return new PreparedPackage(generateSeqNoWeak(), generateMessageId(), ack.serialize(), useHighPriority);
            } catch (IOException e) {
                Logger.e(this.TAG, e);
                return null;
            }
        } else if (foundedPackages.size() == 1 && this.confirmedMessages.size() == 0) {
            SchedullerPackage schedullerPackage = foundedPackages.get(0);
            schedullerPackage.state = STATE_SENT;
            if (schedullerPackage.idGenerationTime == 0) {
                generateParams(schedullerPackage);
            }
            Logger.d(this.TAG, "Single package: #" + schedullerPackage.id + " " + schedullerPackage.supportTag + " (" + schedullerPackage.messageId + ", " + schedullerPackage.seqNo + ")");
            schedullerPackage.writtenToChannel = contextId;
            schedullerPackage.lastAttemptTime = getCurrentTime();
            return new PreparedPackage(schedullerPackage.seqNo, schedullerPackage.messageId, schedullerPackage.serialized, useHighPriority);
        } else {
            MTMessagesContainer container = new MTMessagesContainer();
            if ((this.confirmedMessages.size() > 0 && !useHighPriority) || (!isInited)) {
                try {
                    Long[] msgIds;
                    synchronized (this.confirmedMessages) {
                        msgIds = this.confirmedMessages.toArray(new Long[0]);
                        this.confirmedMessages.clear();
                    }
                    MTMsgsAck ack = new MTMsgsAck(msgIds);
                    Logger.d(this.TAG, "Adding msg_ack: " + msgIds.length);
                    container.getMessages().add(new MTMessage(generateMessageId(), generateSeqNoWeak(), ack.serialize()));
                } catch (IOException e) {
                    Logger.e(this.TAG, e);
                }
            }
            for (SchedullerPackage schedullerPackage : packages) {
                schedullerPackage.state = STATE_SENT;
                if (schedullerPackage.idGenerationTime == 0) {
                    generateParams(schedullerPackage);
                }

                if (schedullerPackage.isDepend) {
                    if (schedullerPackage.dependMessageId == 0) {
                        if (this.lastDependId > 0) {
                            schedullerPackage.dependMessageId = this.lastDependId;
                        } else {
                            schedullerPackage.dependMessageId = -1;
                        }
                    }

                    this.lastDependId = schedullerPackage.messageId;
                }
                schedullerPackage.writtenToChannel = contextId;
                schedullerPackage.lastAttemptTime = getCurrentTime();
                if (schedullerPackage.isDepend && schedullerPackage.dependMessageId > 0) {

                    Logger.d(this.TAG, "Adding package: #" + schedullerPackage.id + " " + schedullerPackage.supportTag + " (" + schedullerPackage.messageId + " on " + schedullerPackage.dependMessageId + ", " + schedullerPackage.seqNo + ")");

                    MTInvokeAfter invokeAfter = new MTInvokeAfter(schedullerPackage.dependMessageId, schedullerPackage.serialized);
                    try {
                        container.getMessages().add(new MTMessage(schedullerPackage.messageId, schedullerPackage.seqNo, invokeAfter.serialize()));
                    } catch (IOException e) {
                        Logger.e(this.TAG, e);
                        // Never happens
                    }
                } else {
                    Logger.d(this.TAG, "Adding package: #" + schedullerPackage.id + " " + schedullerPackage.supportTag + " (" + schedullerPackage.messageId + ", " + schedullerPackage.seqNo + ")");
                    container.getMessages().add(new MTMessage(schedullerPackage.messageId, schedullerPackage.seqNo, schedullerPackage.serialized));
                }
            }

            long containerMessageId = generateMessageId();
            int containerSeq = generateSeqNoWeak();

            for (SchedullerPackage schedullerPackage : packages) {
                schedullerPackage.relatedMessageIds.add(containerMessageId);
            }

            Logger.d(this.TAG, "Sending Package (" + containerMessageId + ", " + containerSeq + ")");

            try {
                return new PreparedPackage(containerSeq, containerMessageId, container.serialize(), useHighPriority);
            } catch (IOException e) {
                // Might not happens
                Logger.e(this.TAG, e);
                return null;
            }
        }
    }

    public synchronized void onServerError(long msgId) {

    }

    public synchronized void onConnectionDies(int connectionId) {
        Logger.d(this.TAG, "Connection dies " + connectionId);
        for (SchedullerPackage schedullerPackage : this.messages.values().toArray(new SchedullerPackage[0])) {
            if (schedullerPackage.writtenToChannel != connectionId) {
                continue;
            }

            if (schedullerPackage.queuedToChannel != -1) {
                Logger.d(this.TAG, "Removing: #" + schedullerPackage.id + " " + schedullerPackage.supportTag);
                forgetMessage(schedullerPackage.id);
            } else {
                if (schedullerPackage.isRpc) {
                    if (schedullerPackage.state == STATE_CONFIRMED || schedullerPackage.state == STATE_QUEUED) {
                        Logger.d(this.TAG, "Re-schedule: #" + schedullerPackage.id + " " + schedullerPackage.supportTag);
                        schedullerPackage.state = STATE_QUEUED;
                        schedullerPackage.lastAttemptTime = 0;
                    }
                } else {
                    if (schedullerPackage.state == STATE_SENT) {
                        Logger.d(this.TAG, "Re-schedule: #" + schedullerPackage.id + " " + schedullerPackage.supportTag);
                        schedullerPackage.state = STATE_QUEUED;
                        schedullerPackage.lastAttemptTime = 0;
                    }
                }

            }
        }
    }

    private class SchedullerPackage {

        public String supportTag;
        public int id;
        public TLObject object;
        public byte[] serialized;
        public long addTime;
        public long scheduleTime;
        public long expiresTime;
        public long ttlTime;
        public long lastAttemptTime;
        public int writtenToChannel = -1;
        public int queuedToChannel = -1;
        public int state;
        public int priority;
        public boolean isDepend;
        public boolean isSent;
        public long idGenerationTime;
        public long dependMessageId;
        public long messageId;
        public int seqNo;
        public HashSet<Integer> relatedFastConfirm = new HashSet<Integer>();
        public HashSet<Long> relatedMessageIds = new HashSet<Long>();
        public HashSet<Long> generatedMessageIds = new HashSet<Long>();
        public int serverErrorCount;
        public boolean isRpc;

        public SchedullerPackage(int id) {
            this.id = id;
        }
    }
}
