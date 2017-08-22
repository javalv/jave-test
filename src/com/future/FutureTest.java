package com.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lvfang01 on 2017/8/21.
 */
public class FutureTest {
    static AtomicLong aLong = new AtomicLong(0);
    static int queueSize = 5000;
    static ThreadPoolExecutor pool = new ThreadPoolExecutor(500,500,10000000, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(queueSize));
//    static ThreadPoolExecutor pool = new ThreadPoolExecutor(200,200,10000000, TimeUnit.MINUTES,
//            new LinkedBlockingQueue<>());
    public void futureTest(){

        List<Callable<Long>> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Callable<Long> call = new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5));
//                    System.out.println(Thread.currentThread().getName());
                    return System.currentTimeMillis();
                }
            } ;
            list.add(call);

        }

        try {
            List<Future<Long>> futures =  pool.invokeAll(list);
            for(Future<Long> f:futures){
                try {
                    f.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            aLong.incrementAndGet();
//            System.out.println("==================================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        FutureTest futureTest = new FutureTest();
        ExecutorService es = Executors.newFixedThreadPool(200);
        new Thread(()->{
            long start = System.currentTimeMillis();
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long l = aLong.get();
                long cost = (System.currentTimeMillis() - start );
                System.out.println(l + " at " + cost + " " + cost * 1.0/l + " " + l * 1000.0 / cost + " "  + pool.getQueue().size());
            }
        }).start();
        for(int i = 0;i<150000;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    futureTest.futureTest();
                }
            });

        }

    }
}
