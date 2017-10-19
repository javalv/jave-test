package com.thread;
/**
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName:com.thread.ThreadPool
 * 描述: TODO
 * 日期:     2017/6/12
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class ThreadPool {

    public static void main(String[] args) {
        ThreadPool poolTest = new ThreadPool();
        poolTest.test();

    }

    void test() {
        ExecutorService es = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
                3, TimeUnit.SECONDS, new LinkedTransferQueue<>());
//        es = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
//                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(96793));
//        es = Executors.newCachedThreadPool();
        monitor((ThreadPoolExecutor) es);
        execute(es);
    }

    AtomicLong ai = new AtomicLong(0);
    void execute(ExecutorService es) {
        List<Future> list = new ArrayList<>();
        long n = 100000L;
        for (int i = 0; i < n; i++) {
            es.submit(() -> {
                try {
                    Random r = new Random();
                    Thread.sleep(r.nextInt(5));
                    ai.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        long start = System.currentTimeMillis();
        while (ai.get() != n){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    void monitor(ThreadPoolExecutor tpe) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("monitor es : " + tpe.getQueue().size() + "," +
                            tpe.getTaskCount() + "," + tpe.getActiveCount() + "," + tpe.getPoolSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}


