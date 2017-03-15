package com.java8async;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Test {
    public static void main(String[] args) throws Exception {

        Test test = new Test();
        test.thenCombineTest();

        System.in.read(new byte[2]);

    }

    ExecutorService es = Executors.newFixedThreadPool(1);

    void thenApplyTest() throws Exception {
        CompletableFuture.runAsync(() -> {
            System.out.println("start ... " + " " + Thread.currentThread().getName());
            sleep(1000);
        }).thenApplyAsync((a) -> {
            StringBuffer buffer = new StringBuffer("str:");
            sleep(1000);
            buffer.append("a");
            System.out.println(buffer.toString() + " " + Thread.currentThread().getName());
            return buffer;
        }, es).thenApplyAsync((buffer) -> {
            sleep(1000);
            buffer.append("b");
            System.out.println(buffer.toString() + " " + Thread.currentThread().getName());

            return buffer;
        }, es).thenApplyAsync((buffer) -> {
            sleep(1000);
            buffer.append("c");
            System.out.println(buffer.toString() + " " + Thread.currentThread().getName());

            return buffer;
        }, es).get();
        System.out.println("thenApplyTest complete");
    }

    void thenAcceptTest() throws Exception {
        CompletableFuture.runAsync(() -> {
            System.out.println("start ... " + " " + Thread.currentThread().getName());
            sleep(1000);
        }).thenApplyAsync((a) -> {
            StringBuffer buffer = new StringBuffer("thenAccept");
            sleep(1000);
            System.out.println(buffer.toString() + " " + Thread.currentThread().getName());
            return buffer;
        }).thenAccept((a) -> {
            System.out.println(a);
        });
    }

    void thenCombineTest(){

        CompletableFuture<String> A = CompletableFuture.supplyAsync(() -> {
            System.out.println("start a ... " + " " + Thread.currentThread().getName() + " " + new Timestamp(System.currentTimeMillis()));
            sleep(2000);
            return "a";
        });

        CompletableFuture<String> B = CompletableFuture.supplyAsync(() -> {
            System.out.println("start b ... " + " " + Thread.currentThread().getName() + " " + new Timestamp(System.currentTimeMillis()));
            sleep(4000);
            return "b";
        });

        CompletableFuture C = A.thenCombine(B,(a,b)->{
            System.out.println("start c ... " + " " + Thread.currentThread().getName() + " " + new Timestamp(System.currentTimeMillis()));
            return (a + b);
        });

        try {
            System.out.println(C.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void asyncTest() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("a: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        int i = 100;
        final Map<String, Integer> ref = new HashMap<>();
        ref.put("key", i);
        while (i-- > 0) {

            future.thenRun(() -> {
                int count = ref.get("key");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);

                    ref.put("key", --count);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "====" + count + " ==== " + new Timestamp(System.currentTimeMillis()));
            });

        }
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ref);

    }

    private void runAfterEitherTest() throws ExecutionException, InterruptedException {
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

    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



