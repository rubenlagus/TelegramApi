package org.telegram.bot.kernel;

import org.telegram.bot.services.BotLogger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Exectue a task periodically
 * @date 27/01/25
 */
public class TimerExecutor {
    private static final String LOGTAG = "TIMEREXECUTOR"; ///< Logger
    private static volatile TimerExecutor instance; ///< Instance
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1); ///< Thread to execute operations

    /**
     * Private constructor due to singleton
     */
    private TimerExecutor() {
    }

    /**
     * Singleton pattern
     *
     * @return Instance of the executor
     */
    public static TimerExecutor getInstance() {
        final TimerExecutor currentInstance;
        if (instance == null) {
            synchronized (TimerExecutor.class) {
                if (instance == null) {
                    instance = new TimerExecutor();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }

        return currentInstance;
    }

    /**
     * Add a new CustomTimerTask to be executed
     *
     * @param task       Task to execute
     * @param targetHour Hour to execute it
     * @param targetMin  Minute to execute it
     * @param targetSec  Second to execute it
     */
    public void startExecutionEveryDayAt(CustomTimerTask task, int targetHour, int targetMin, int targetSec) {
        BotLogger.warning(LOGTAG, "Posting new task" + task.getTaskName());
        final Runnable taskWrapper = () -> {
            try {
                task.execute();
                task.reduceTimes();
                startExecutionEveryDayAt(task, targetHour, targetMin, targetSec);
            } catch (Exception e) {
                BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at TimerExecutor", e);
            }
        };
        if (task.getTimes() != 0) {
            final long delay = computNextDilay(targetHour, targetMin, targetSec);
            executorService.schedule(taskWrapper, delay, TimeUnit.SECONDS);
        }
    }

    /**
     * Add a new CustomTimerTask to be executed
     *
     * @param task       Task to execute
     * @param targetHour Hour to execute it
     * @param targetMin  Minute to execute it
     * @param targetSec  Second to execute it
     */
    public void startExecutionEach(CustomTimerTask task, int targetHour, int targetMin, int targetSec) {
        BotLogger.warning(LOGTAG, "Posting new task" + task.getTaskName());
        final Runnable taskWrapper = () -> {
            try {
                task.execute();
                task.reduceTimes();
                startExecutionEach(task, targetHour, targetMin, targetSec);
            } catch (Exception e) {
                BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at TimerExecutor", e);
            }
        };
        if (task.getTimes() != 0) {
            long delay = computNextTime(targetHour, targetMin, targetSec);
            if (delay < 10) {
                delay = 10;
            }
            executorService.schedule(taskWrapper, delay, TimeUnit.SECONDS);
        }
    }

    /**
     * Find out next daily execution
     *
     * @param targetHour Target hour
     * @param targetMin  Target minute
     * @param targetSec  Target second
     * @return time in second to wait
     */
    private long computNextDilay(int targetHour, int targetMin, int targetSec) {
        final LocalDateTime localNow = LocalDateTime.now();
        final ZoneId currentZone = ZoneId.systemDefault();
        final ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        while (zonedNow.compareTo(zonedNextTarget) > 0) {
            zonedNextTarget = zonedNextTarget.plusDays(1);
        }

        final Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

    /**
     * Find out next with time offset
     *
     * @param hourOffset hour offset
     * @param minOffset  minute offset
     * @param secOffset  second offset
     * @return time in second to wait
     * @note It will add one minute extra until next time is bigger than target time
     */
    private long computNextTime(int hourOffset, int minOffset, int secOffset) {
        final LocalDateTime localNow = LocalDateTime.now();
        final ZoneId currentZone = ZoneId.systemDefault();
        final ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.plusHours(hourOffset)
                .plusMinutes(minOffset).plusSeconds(secOffset);
        while (zonedNow.compareTo(zonedNextTarget) > 0) {
            zonedNextTarget = zonedNextTarget.plusSeconds(5);
        }

        final Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

    @Override
    public void finalize() {
        this.stop();
    }

    /**
     * Stop the thread
     */
    public void stop() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            BotLogger.severe(LOGTAG, ex);
        } catch (Exception e) {
            BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at TimerExecutor", e);
        }
    }
}
