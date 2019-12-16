package com.fma.ratelimit.schedulars;

import com.fma.ratelimit.dal.CommonHelper;

public class RemoveKeys extends Thread{
    public void run() {

        while (true) {
          //  System.out.println("-->> thread is running..." + CommonHelper.counter);
            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException exp){
                System.err.println("exp :: "+exp.getMessage());
            }
        //    CommonHelper.counter++;
        }
    }
}
