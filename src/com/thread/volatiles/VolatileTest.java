package com.thread.volatiles;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lin on 16/11/13.
 */
public class VolatileTest extends  Thread{

    private boolean isRunning = true;

//    private AtomicBoolean atomicRunning = new AtomicBoolean(true);

    private  int i = 0;

    public VolatileTest(){
    }

    public void setRunning(){
        this.isRunning = false;
//        atomicRunning.set(false);
        this.i = 1;
    }

    public void run(){

        while (isRunning){
//            i++;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(i);
        }
//        while(atomicRunning.get()){
//
//        }

        System.out.println("thread is out " + i);
    }

    public static void main(String[] args) {

        VolatileTest test = new VolatileTest();
        test.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        test.i++;
        test.setRunning();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit ... " + test.i);
        System.exit(0);
    }


}
