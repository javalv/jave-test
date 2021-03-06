package com.thread;

/**
 * 1.jvm会为每个线程提供一个栈内存,同时为成员变量复制一个副本
 * 2.volatile保证:当子线程在使用变量时,一定能拿到最新当值(主线程修改后)
 * 3.但是当其它子线程也在并发当情况下修改了这个值,如thread1执行了i++,同时thread2执行了i++,则不能保证i=i+2;
 * 4.如果想保证以上这点,需要使用原子操作
 */
public class VolatileTest extends  Thread{

//    private boolean isRunning = true;

//    private AtomicBoolean atomicRunning = new AtomicBoolean(true);

    private volatile int i = 0;

    public VolatileTest(){
    }

    public void setRunning(){
//        this.isRunning = false;
//        atomicRunning.set(false);
        this.i = 1;
    }

    public void run(){

        while (i == 0){
//            i++;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(i);
        }
//        while(atomicRunning.get()){
//
//        }

        System.out.println("thread is out " + i);
    }

    public static void main(String[] args) {

        VolatileTest test = new VolatileTest();
        test.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        test.i++;
        test.setRunning();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit ... " + test.i);
        System.exit(0);
    }


}
