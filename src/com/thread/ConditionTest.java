package com.thread;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:com.thread.ConditionTest
 * 描述: TODO
 * 日期:     2017/1/5
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class ConditionTest {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("我要等一个信号");

                try {

                    condition.await();//this.wait();
                    System.out.println("我拿到这个信号");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("我拿到锁了");
                try {
                    condition.signal();
                    System.out.println("我发了一个信号");
                }
                finally {
                    lock.unlock();
                }
            }
        });
        thread2.start();
    }




}


