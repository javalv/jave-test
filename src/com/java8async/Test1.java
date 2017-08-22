package com.java8async;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;

/**
 * ClassName:com.java8async.Test
 * 描述: TODO  
 * 日期:     2017/3/14 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test1 {

    public static void main(String[] args) throws IOException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("A start ..." + Thread.currentThread().getName() + " " + new Timestamp(System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A end ..." + new Timestamp(System.currentTimeMillis()));
        });
        CompletableFuture<Void> f2 = future.thenApplyAsync((a)->{
            System.out.println("a:" + " " + Thread.currentThread().getName() + new Timestamp(System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        });
        System.out.println("main " + Thread.currentThread().getName());
        System.in.read(new byte[2]);
    }

}


