package com.whx.design_pattern.state;

/**
 * Created by wanghongxing on 15/12/7.
 */
public class TVStopState implements State {

    @Override
    public void doAction() {
        System.out.println("tv is turned off");
    }
}
