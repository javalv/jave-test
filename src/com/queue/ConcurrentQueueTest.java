package com.queue;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName:com.queue.ConcurrentQueueTest 
 * 描述: TODO  
 * 日期:     2017/5/25 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class ConcurrentQueueTest {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentQueueTest test = new ConcurrentQueueTest();
        for (int i=0;i<1000 ;i++){
            new Thread(()->{
                long time = System.currentTimeMillis();
                for (int j=0; j<100000; j++){
                    test.offer(System.currentTimeMillis());
                }
                System.out.println(Thread.currentThread() + ": " + (System.currentTimeMillis() - time));
            }).start();
        }
        Thread.sleep(1000);
        long last = 0;
        while (true){

            Long n = test.take();
            if(n == null){
//                System.out.println("take is null");
                continue;
            }
            if(n < last){
//                System.out.println(n);
            }

            last = n;
        }
    }

    //当并发数量非常大时，LinkedBlockingQueue 的效率明显降低
//    ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
    LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>();

    private Long take(){
        return queue.poll();
    }

    private void offer(long n){
        queue.offer(n);
    }
}


