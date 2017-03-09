package com.java8async;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Test{
    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> {
            System.out.println("A start ..." + new Timestamp(System.currentTimeMillis()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A end ..." + new Timestamp(System.currentTimeMillis()));
        });
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> {
            System.out.println("B start ..." + new Timestamp(System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B end ..." + new Timestamp(System.currentTimeMillis()));
        });
        futureA.runAfterEither(futureB, () -> {
            System.out.println("C start ..." + new Timestamp(System.currentTimeMillis()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C end ..." + new Timestamp(System.currentTimeMillis()));
        }).get();

        Thread.sleep(10000);
        System.out.println("all end ...");


    }


}

class TestThread extends Thread{
    public void run(){
    }
}


