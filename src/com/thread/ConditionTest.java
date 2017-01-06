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


    /**
     *
     * http://ifeve.com/understand-condition/
     *
     1. 线程1调用reentrantLock.lock时，线程被加入到AQS的等待队列中。

     2. 线程1调用await方法被调用时，该线程从AQS中移除，对应操作是锁的释放。

     3. 接着马上被加入到Condition的等待队列中，以为着该线程需要signal信号。

     4. 线程2，因为线程1释放锁的关系，被唤醒，并判断可以获取锁，于是线程2获取锁，并被加入到AQS的等待队列中。

     5.  线程2调用signal方法，这个时候Condition的等待队列中只有线程1一个节点，于是它被取出来，并被加入到AQS的等待队列中。  注意，这个时候，线程1 并没有被唤醒。

     6. signal方法执行完毕，线程2调用reentrantLock.unLock()方法，释放锁。这个时候因为AQS中只有线程1，于是，AQS释放锁后按从头到尾的顺序唤醒线程时，线程1被唤醒，于是线程1回复执行。

     7. 直到释放所整个过程执行完毕。

     *
     *
     *
     */


}


