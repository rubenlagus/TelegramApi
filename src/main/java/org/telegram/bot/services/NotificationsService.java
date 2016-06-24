package org.telegram.bot.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Notifications service
 * @date 28 of August of 2015
 */
public class NotificationsService {
    private static final String LOGTAG = "NOTIFICATIONSSERVICE";
    private static NotificationsService instance;

    private static int notificationsCounter;
    public static final int updatesInvalidated = notificationsCounter++;
    public static final int needGetUpdates = notificationsCounter++;

    private final NotificationsThread thread;
    private final ConcurrentLinkedDeque<Notification> notificationsQueue = new ConcurrentLinkedDeque<>();
    private final ConcurrentHashMap<Integer, ConcurrentLinkedDeque<NotificationObserver>> observers = new ConcurrentHashMap<>();

    private NotificationsService() {
        this.thread = new NotificationsThread();
        this.thread.start();
    }

    public static NotificationsService getInstance() {
        if (instance == null) {
            synchronized (NotificationsService.class) {
                if (instance == null) {
                    instance = new NotificationsService();
                }
            }
        }
        return instance;
    }

    public void addObserver(NotificationObserver observer, int notificationId) {
        synchronized (observers) {
            if (observers.containsKey(notificationId)) {
                if (!observers.get(notificationId).contains(observer)) {
                    observers.get(notificationId).add(observer);
                }
            } else {
                final ConcurrentLinkedDeque<NotificationObserver> newObservers = new ConcurrentLinkedDeque<>();
                newObservers.add(observer);
                observers.put(notificationId, newObservers);
            }
        }
    }

    public void removeObserver(NotificationObserver observer, int notificationId) {
        synchronized (observers) {
            if (observers.containsKey(notificationId)) {
                observers.get(notificationId).remove(observer);
            }
        }
    }

    public void postNotification(int notificationId, Object... args) {
        final Notification notification = new Notification(notificationId, args);
        notificationsQueue.addLast(notification);
        synchronized (notificationsQueue) {
            notificationsQueue.notifyAll();
        }
    }

    private void handleNotification(Notification notification) {
        ConcurrentLinkedDeque<NotificationObserver> notificationObservers = null;
        synchronized (observers) {
            if (observers.containsKey(notification.notificationId)) {
                notificationObservers = observers.get(notification.notificationId);
            }
        }

        if (notificationObservers != null) {
            for (NotificationObserver observer : notificationObservers) {
                observer.onNotificationReceived(notification.notificationId, notification.args);
            }
        }
    }

    public interface NotificationObserver {
        void onNotificationReceived(int notificationId, Object... args);
    }

    private static class Notification {
        public int notificationId;
        public Object[] args;

        public Notification(int notificationId, Object... args) {
            this.notificationId = notificationId;
            this.args = args;
        }
    }

    private class NotificationsThread extends Thread {
        public NotificationsThread() {
            super();
            this.setName("NotificationsThread#" + this.getId());
        }

        @Override
        public void run() {
            Notification currentNotification;
            while (true) {
                currentNotification = notificationsQueue.pollFirst();
                if (currentNotification == null) {
                    try {
                        synchronized (notificationsQueue) {
                            notificationsQueue.wait();
                        }
                    } catch (InterruptedException e) {
                        BotLogger.error(LOGTAG, e);
                    }
                } else {
                    handleNotification(currentNotification);
                }
            }
        }
    }
}
