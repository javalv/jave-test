package com.thread;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:com.thread.ABA1 
 * 描述: TODO  
 * 日期:     2017/3/30 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class ABA1 {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(20000000);

        for(int i=0;i<10000000;i++){
            Thread t1 = new Thread (()->{
                for(long l =0;l<1L;l++){
                    atomicInteger.incrementAndGet();
                }
                count.decrementAndGet();
            });
            t1.start();

            Thread t2 = new Thread (()->{
                for(long l =0;l<1L;l++){
                    atomicInteger.decrementAndGet();
                }
                count.decrementAndGet();
            });
            t2.start();
        }

        while (count.get() != 0){
            System.out.println(atomicInteger.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("final: " + atomicInteger.get());

    }



}


