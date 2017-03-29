/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

/**
 *
 * @author Stas Torgashov  (crimcat@yandex.ru)
 */
public class TomatoEngine {
    
    public enum State {
        IDLE,
        WORKING,
        BREAK,
        FINISH,
        PAUSED,
        PAUSED_IN_BREAK,
    }
    
    public interface StateChangeInformer {
        void stateChanged();
    }
    public interface OneMinuteTimerInformer {
        void oneMinutePass();
    }
    
    private State currentState = State.IDLE;
    
    public TomatoEngine(int bunchSize, int tomatoDuration, int breakDuration) {
        if(bunchSize < 1) {
            throw new RuntimeException("Bunch size cannot be 0 or negative");
        }
        if((tomatoDuration < 1) || (breakDuration < 1))  {
            throw new RuntimeException("Duration cannot be 0 or negative");
        }
        this.bunchSize = bunchSize;
        this.tomatoDuration = tomatoDuration;
        this.breakDuration = breakDuration;
        
        this.minutesTimer = new MinutesTimer(
            () -> { if(null != omtCb) { omtCb.oneMinutePass(); }; },
            () -> {
                checkAndUpdateCounter();
                setCurrentState(getNextState());
                minutesTimer.go(getCurrentStateTimerDuration());
                if(null != scCb) { scCb.stateChanged(); }
            }
        );
    }
    
    public final int getBunchSize() {
        return bunchSize;
    }
    
    public final int getTomatoDuration() {
        return tomatoDuration;
    }
    
    public final int getBreakDuration() {
        return breakDuration;
    }
    
    public final int getCurrentPhaseDuration() {
        if(getCurrentState() == State.WORKING) {
            return getTomatoDuration();
        } else if(getCurrentState() == State.BREAK) {
            return getBreakDuration();
        }
        return 0;
    }
    
    public TomatoEngine setStateChangeCallback(StateChangeInformer sciCb) {
        this.scCb = sciCb;
        return this;
    }
    
    public TomatoEngine setMinuteElapsedCallback(OneMinuteTimerInformer omtiCb) {
        this.omtCb = omtiCb;
        return this;
    }
    
    public boolean start() {
        if(State.IDLE == getCurrentState()) {
            setCurrentState(State.WORKING);
            minutesTimer.go(getCurrentStateTimerDuration());
            return true;
        }
        return false;
    }
    
    public boolean pause() {
        if(null == getCurrentState()) {
            return false;
        } else switch (getCurrentState()) {
            case WORKING:
                setCurrentState(State.PAUSED);
                minutesTimer.pause();
                break;
            case BREAK:
                setCurrentState(State.PAUSED_IN_BREAK);
                break;
            default:
                return false;
        }
        return true;
    }
    
    public boolean proceed() {
        if(State.PAUSED == getCurrentState()) {
            setCurrentState(State.WORKING);
            minutesTimer.proceed();
            return true;
        }
        return false;
    }
    
    public boolean cancel() {
        if(State.IDLE != getCurrentState()) {
            setCurrentState(State.FINISH);
            minutesTimer.cancel();
            return true;
        }
        return false;
    }
    
    public final State getCurrentState() {
        return currentState;
    }
    
    public final int getRemainingTomatoes() {
        return bunchSize;
    }
    
    public final int getMinutesToGo() {
        return minutesTimer.minutesToGo();
    }
    
    private void setCurrentState(State newState) {
        currentState = newState;
    }
    
    private State getNextState() {
        switch(getCurrentState()) {
            case IDLE:
                return State.WORKING;
            case WORKING:
                return State.BREAK;
            case BREAK:
                return bunchSize > 0 ? State.WORKING : State.FINISH;
            case FINISH:
                return State.FINISH;
            
            case PAUSED:
                return State.WORKING;
            case PAUSED_IN_BREAK:
                return State.PAUSED;
        }
        return State.IDLE;
    }
    
    private int getCurrentStateTimerDuration() {
        if(getCurrentState() == State.WORKING) {
            return tomatoDuration;
        } else if(getCurrentState() == State.BREAK) {
            return breakDuration;
        }
        return 0;
    }
    
    private void checkAndUpdateCounter() {
        if(bunchSize < 0) {
            throw new RuntimeException("Algorithm error!");
        }
        if((State.BREAK == getCurrentState()) && (State.WORKING == getNextState())) {
            --bunchSize;
        }
    }
     
    private int bunchSize;
    private int tomatoDuration;
    private int breakDuration;
    
    private StateChangeInformer scCb = null;
    private OneMinuteTimerInformer omtCb = null;
    
    private MinutesTimer minutesTimer;
}
