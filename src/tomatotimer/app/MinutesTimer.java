/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas Torgashov (crimcat@yandex.ru)
 */
public class MinutesTimer {
    
    private final static int GRANULARITY_IN_SECONDS = 2;
    private final static int ONE_MINUTE = 60;
    
    public interface IOneMinuteNotifier {
        void oneMinutePassed();
    }
    
    public interface ITimeCounterFinishedNotifier {
        void timeCounterFinished();
    }
    
    public MinutesTimer(IOneMinuteNotifier minutesNotifier, ITimeCounterFinishedNotifier timeNotifier) {
        if((null == minutesNotifier) || (null == timeNotifier)) {
            throw new RuntimeException("Callbacks cannot be null");
        }
        
        this.minutesNotifier = minutesNotifier;
        this.timeNotifier = timeNotifier;
    }
    
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
    
    public int minutesToGo() {
        return minutesToCount;
    }
    
    public void pause() {
        isPaused = true;
    }
    
    public void proceed() {
        isPaused = false;
    }
    
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
