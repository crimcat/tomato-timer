/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

/**
 * The engine: this class defines the logic of tomato timer.
 * @author Stas Torgashov  (crimcat@yandex.ru)
 */
public class TomatoEngine {
    
    /**
     * Possible state the engine use.
     */
    public enum State {
        IDLE,               // engine is just started, doing nothing
        WORKING,            // work time (tomato) period
        BREAK,              // break time period
        FINISH,             // all tomatoes are finished
        PAUSED,             // engine is paused being in work time
        PAUSED_IN_BREAK,    // engine is paused being in break time
    }
    
    /**
     * Callback interface to indicate that engine state has been changed.
     */
    public interface StateChangeInformer {
        void stateChanged();
    }
    
    /**
     * Callback interface to indicate that one minute has passed.
     */
    public interface OneMinuteTimerInformer {
        void oneMinutePass();
    }
    
    private State currentState = State.IDLE;
    
    /**
     * Create the engine with timing parameters.
     * @param bunchSize number of tomatoes to run
     * @param tomatoDuration work time (tomato) duration in minutes
     * @param breakDuration break time duration in minutes
     */
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
    
    /**
     * @return work time (tomato) duration in minutes
     */
    public final int getTomatoDuration() {
        return tomatoDuration;
    }
    
    /**
     * @return break time duration in minutes
     */
    public final int getBreakDuration() {
        return breakDuration;
    }
    
    /**
     * Set state change callback.
     * @param sciCb state change callback interface object
     * @see StateChangeInformer
     * @return this
     */
    public TomatoEngine setStateChangeCallback(StateChangeInformer sciCb) {
        this.scCb = sciCb;
        return this;
    }
    
    /**
     * Set one minute passed callback.
     * @param omtiCb one minute passed callback interface object
     * @see OneMinuteTimerInformer
     * @return this
     */
    public TomatoEngine setMinuteElapsedCallback(OneMinuteTimerInformer omtiCb) {
        this.omtCb = omtiCb;
        return this;
    }
    
    /**
     * Start the engine.
     * @return true if the engine is really started
     */
    public boolean start() {
        if(State.IDLE == getCurrentState()) {
            setCurrentState(State.WORKING);
            minutesTimer.go(getCurrentStateTimerDuration());
            return true;
        }
        return false;
    }
    
    /**
     * Pause the engine, stop time counting.
     * @return true if the engine is successfully paused
     */
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
    
    /**
     * Start the engine again from paused state
     * @return true if succeeded
     */
    public boolean proceed() {
        if(State.PAUSED == getCurrentState()) {
            setCurrentState(State.WORKING);
            minutesTimer.proceed();
            return true;
        }
        return false;
    }
    
    /**
     * Cancel the engine, drop all operations and time counting.
     * @return true if cancel operation is successfull
     */
    public boolean cancel() {
        if(State.IDLE != getCurrentState()) {
            setCurrentState(State.FINISH);
            minutesTimer.cancel();
            return true;
        }
        return false;
    }
    
    /**
     * @see State
     * @return current engine state
     */
    public final State getCurrentState() {
        return currentState;
    }
    
    /**
     * @return how many tomatoes are still left to go
     */
    public final int getRemainingTomatoes() {
        return bunchSize;
    }
    
    /**
     * @return minutes to go for current state
     */
    public final int getMinutesToGo() {
        return minutesTimer.minutesToGo();
    }
    
    /**
     * Set current engine state.
     * No validation is performed.
     * @param newState new state value
     */
    private void setCurrentState(State newState) {
        currentState = newState;
    }
    
    /**
     * Calculate next possible engine state.
     * @return state value which can be next from the current one
     */
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
    
    /**
     * @return duration of the current state or zero if currect state doesn't have a duration
     */
    public int getCurrentStateTimerDuration() {
        if(getCurrentState() == State.WORKING) {
            return tomatoDuration;
        } else if(getCurrentState() == State.BREAK) {
            return breakDuration;
        }
        return 0;
    }
    
    /**
     * Check if we can decrement the number of tomatoes, and if we can - decrement the number of tomatoes.
     */
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
