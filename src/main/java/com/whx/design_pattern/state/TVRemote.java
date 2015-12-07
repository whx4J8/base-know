package com.whx.design_pattern.state;

/**
 * Created by wanghongxing on 15/12/7.
 */
public class TVRemote {

    public static void main(String[] args){

        TVContext tvContext = new TVContext();
        State startState = new TVStartState();
        State stopState = new TVStopState();

        tvContext.setTvState(startState);
        tvContext.doAction();

        tvContext.setTvState(stopState);
        tvContext.doAction();

    }
}
