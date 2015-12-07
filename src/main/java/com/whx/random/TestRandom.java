package com.whx.random;

import java.util.Random;

/**
 * Created by wanghongxing on 15/12/7.
 */
public class TestRandom {

    public static void main(String[] args){

        Random random = new Random();

        int zeroNum = 0;
        for(int i=0;i<8000;i++){
            int num = random.nextInt(50);
            if(num ==0){
                zeroNum++;
            }
            System.out.println(num);
        }

        System.out.println("zero num : " + zeroNum);

    }
}
