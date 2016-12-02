package com.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lin on 16/11/30.
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) {

        Thread[] threads = new Thread[10];
        for(int i=0;i<threads.length;i++){
            Thread t = new Thread(){
                public void run(){
                    System.out.println(Thread.currentThread().getName() + "-" + getInstance());
                }
            };
            threads[i] = t;
        }

        for(Thread t : threads){
            t.start();
        }
    }

    //putIfAbsent 当不存在时才赋值
    private static void test(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        System.out.println(map.put("A","aa"));
        System.out.println(map.putIfAbsent("B","bb"));
        System.out.println(map.putIfAbsent("A","bb"));
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
    }


    private final static ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String, Object>();
    private final static Map<String, Object> map = new HashMap<String, Object>();
    public static Object getInstance() {
        //...
        String key = "some";

//        Object locale = map.get(key);
        Object locale = concurrentMap.get(key);
        if (locale == null) {
            locale = new Object();
            //如果是map,则会出现两个"locale null = null" 的情况,说明map不具备原子性
//            map.put(key, locale);

            Object l = concurrentMap.putIfAbsent(key, locale);
            System.out.println(Thread.currentThread().getName() + "-" + "locale null = " + l);

            //这个判断非常重要,因为当出现2个线程进入此代码块时,两个都会创建一个新的local,如果不判断,也可以在结果返回 concurrentMap.get(key)
//            if (l != null) {
//                locale = l;
//            }

            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        return locale;
        return concurrentMap.get(key);
    }
}
