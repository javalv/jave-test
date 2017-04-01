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

        /**
         * lockInterruptibly()也是阻塞，同样要通过tryAcquire（1），和lock()的区别是可以被中断
         *
         *  1）如果当前线程未被中断，则获取锁。

            2）如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。

            3）如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。

            4）如果锁被另一个线程保持，则出于线程调度目的，禁用当前线程，并且在发生以下两种情况之一以
                前，该线程将一直处于休眠状态：
            1）锁由当前线程获得；或者

            2）其他某个线程中断当前线程。

            5）如果当前线程获得该锁，则将锁保持计数设置为 1。
                如果当前线程：
            1）在进入此方法时已经设置了该线程的中断状态；或者

            2）在等待获取锁的同时被中断。

                则抛出 InterruptedException，并且清除当前线程的已中断状态。


            6）在此实现中，因为此方法是一个显式中断点，所以要优先考虑响应中断，而不是响应锁的普通获取或
            重入获取。

            指定者： 接口 Lock 中的 lockInterruptibly
            抛出：   InterruptedException   如果当前线程已中断。

         */

        //lock
        Thread t1 = thread1process();

        //lockInterruptibly
        Thread t2 = thread2process();

        //中断
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


    private void ReentrantTest(){
        lock.lock();
        try{

            lock.lock();
            try{
                System.out.println("");
            }finally {
                lock.unlock();
                System.out.println("unlock 1 ...");
            }

        }finally {
            lock.unlock();
            System.out.println("unlock 2 ...");
        }
    }

    public static void main(String[] args) {

        LockTest lockTest = new LockTest();
//        lockTest.test();

        lockTest.ReentrantTest();

    }
}


