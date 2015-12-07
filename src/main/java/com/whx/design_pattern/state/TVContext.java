package com.whx.design_pattern.state;

/**
 * Created by wanghongxing on 15/12/7.
 */
public class TVContext implements State {

    private State tvState;

    public State getTvState() {
        return tvState;
    }

    public void setTvState(State tvState) {
        this.tvState = tvState;
    }

    @Override
    public void doAction() {
        this.tvState.doAction();
    }
}
