/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application time counting.
 * @author Stas Torgashov (crimcat@yandex.ru)
 */
public class MinutesTimer {
    
    private final static int GRANULARITY_IN_SECONDS = 2;
    private final static int ONE_MINUTE = 60;
    
    /**
     * Callback interface needed to indicate a minute has elapsed.
     */
    public interface IOneMinuteNotifier {
        void oneMinutePassed();
    }
    
    /**
     * Callback interface needed to indicate the time period is over.
     */
    public interface ITimeCounterFinishedNotifier {
        void timeCounterFinished();
    }
    
    /**
     * Create the timer.
     * Callbacks parameters cannot be null.
     * @param minutesNotifier callback for minutes
     * @param timeNotifier  callback for the whole time interval
     */
    public MinutesTimer(IOneMinuteNotifier minutesNotifier, ITimeCounterFinishedNotifier timeNotifier) {
        if((null == minutesNotifier) || (null == timeNotifier)) {
            throw new RuntimeException("Callbacks cannot be null");
        }
        
        this.minutesNotifier = minutesNotifier;
        this.timeNotifier = timeNotifier;
    }
    
    /**
     * Start the timer.
     * @param minutes number of minutes to go
     */
    public void go(int minutes) {
        minutesToCount = minutes;
        if(minutesToCount > 0) {
            worker = new Thread(new Runnable() {
                @Override
                public void run() {
                    Logger.getLogger(MinutesTimer.class.getName()).log(Level.INFO, "Timer worker: {0} min", minutesToCount);
                    int gransCounter = 0;
                    while(!Thread.currentThread().isInterrupted()) try {
                        Thread.sleep(GRANULARITY_IN_SECONDS * 1000);
                        if(!isPaused) {
                            gransCounter += GRANULARITY_IN_SECONDS;
                            if(gransCounter >= ONE_MINUTE) {
                                gransCounter = 0;
                                if(--minutesToCount == 0) {
                                    timeNotifier.timeCounterFinished();
                                    break;
                                }
                                minutesNotifier.oneMinutePassed();
                            }
                        }
                    } catch(InterruptedException ex) {
                        Logger.getLogger(MinutesTimer.class.getName()).log(Level.INFO, "Timer worker interrupted");
                    }
                    Logger.getLogger(MinutesTimer.class.getName()).log(Level.INFO, "Timer worker thread finished");
                }
            });
            worker.start();
        }
    }
    
    /**
     * @return how many minutes are still left to go
     */
    public int minutesToGo() {
        return minutesToCount;
    }
    
    /**
     * Pause the timer.
     */
    public void pause() {
        isPaused = true;
    }
    
    /**
     * Start the timer again if it was paused before.
     */
    public void proceed() {
        isPaused = false;
    }
    
    /**
     * Cancel running timer.
     */
    public void cancel() {
        if((worker != null) && worker.isAlive()) {
            isPaused = false;
            worker.interrupt();
        }
        worker = null;
    }
    
    private int minutesToCount = 0;
    private Thread worker = null;
    private boolean isPaused = false;
    private final IOneMinuteNotifier minutesNotifier;
    private final ITimeCounterFinishedNotifier timeNotifier;
}
