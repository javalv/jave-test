package com.thread;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:com.thread.ThreadPool 
 * 描述: TODO  
 * 日期:     2017/6/12 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(100);
        for(int i=0;i< 1000 ;i++) {
            es.submit(() -> {
                try {
                    Thread.sleep(100 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}


