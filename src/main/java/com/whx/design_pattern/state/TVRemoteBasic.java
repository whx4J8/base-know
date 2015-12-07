package com.whx.design_pattern.state;

/**
 *
 * if else 的状态机模式
 *      客户端需要知道每一个不同的值,代表的遥控器的不同的状态
 *      但是如果这样,大量的状态被正佳,那么对于被紧紧捆绑在一起的状态实现以及相应的可会淡代码,维护会变的一场困难
 * Created by wanghongxing on 15/12/7.
 */
public class TVRemoteBasic {


    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void doAction(){
        if(state.equals("on")){
            System.out.println("tv is turned on ");
        }else if(state.equals("off")){
            System.out.println("tv is turned off ");
        }
    }

    public static void main(String[] args){

        TVRemoteBasic remote = new TVRemoteBasic();
        remote.setState("on");
        remote.doAction();

        remote.setState("off");
        remote.doAction();

    }

}
