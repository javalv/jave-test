package com.thread;
/**
 * Copyright © 2016 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:com.thread.volatiles.LockTest 
 * 描述: TODO  
 * 日期:     2016/11/30 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class LockTest {

    ReentrantLock lock = new ReentrantLock();

    private void test(){

        Thread t1 = thread1process();
        Thread t2 = thread2process();
        thread3process(t2);

    }


    private Thread thread1process(){
        Thread thread = new Thread(){

            @Override
            public void run(){
                lock.lock();
                try{
                    try {
                        Thread.sleep(1000 * 15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    while (true){
//                        System.out.println("runnig ...");
//                    }

                    System.out.println("thread1 run over ");

                }finally {
                    lock.unlock();
                    System.out.println("unlock ...");
                }
            }
        };

        thread.start();
        return thread;
    }


    private Thread thread2process(){
        Thread thread = new Thread(){

            @Override
            public void run(){
                try {
                    try {
                        Thread.sleep(1000 * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lockInterruptibly();
                    while (true){
                        System.out.println("thread2 ok");
                    }
                } catch (InterruptedException e) {
                    System.out.println("lockInterrupt");
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        return thread;
    }



    private void thread3process(Thread t){
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    int i = 0;
                    while (i++ < 10){

                        System.out.println(i);
                        Thread.sleep(1000 * 1);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    t.interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    public static void main(String[] args) {

        LockTest lockTest = new LockTest();
        lockTest.test();

    }
}


