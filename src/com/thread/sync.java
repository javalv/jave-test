package com.thread;
/**
 * Copyright © 2016 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:com.thread.sync 
 * 描述: TODO  
 * 日期:     2016/12/27 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class sync {

    private static volatile Object instance;//volatile 可见性，但是没有解决指令重排的问题

    final static AtomicInteger count = new AtomicInteger();

    private static final Object LOCK = new Object();
    public static Object get(){
        if(instance == null){
            System.out.println("... ...");
            synchronized (LOCK){
                Object ref = instance;//双重锁，这样写也没有防止java指令重排问题
                if(ref == null){
                    instance = new Object();
                    count.incrementAndGet();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        final Map map = new HashMap();
        for(int i=0;i<1000;i++){
            new Thread(){
                public void run(){
                    map.put(get(),System.currentTimeMillis());
                }
            }.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size() + "," + count.get());
        System.out.println(map);
    }
}




