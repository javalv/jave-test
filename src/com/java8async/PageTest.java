package com.java8async;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * ClassName:com.java8async.PageTest
 * 描述: TODO
 * 日期:     2017/3/13
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class PageTest {
    public static void main(String[] args) throws Exception{
        PageTest pageTest = new PageTest();
        pageTest.test(5, 10);

//        System.out.println(pageTest.query1_skip + "," + pageTest.query1_limit);
//        System.out.println(pageTest.query2_skip + "," + pageTest.query2_limit);
        byte[] b = new byte[2];

            System.in.read(b);
        System.out.println(b);

    }

    int query1_skip = -1, query1_limit = -1, query2_skip = -1, query2_limit = -1;

    private <T> void test(int currentPage, int pageSize) {
        if (currentPage == 0) { //从1开始
            currentPage = 1;
        }

        /**
         * 获取 aggregation1 总页数
         */
        int aggregation1Count = 41;

        //整页的总页数
        int len = aggregation1Count / pageSize;

        //最后一页的长度
        int lastPageSize = aggregation1Count % pageSize;

        if (len >= currentPage) {//直接获取 aggregation1 中的信息
            setPage1((currentPage - 1) * pageSize, pageSize);
        } else if (currentPage - len == 1) {
            if (lastPageSize > 0 && lastPageSize < pageSize) {//混合返回
                setPage1((currentPage - 1) * pageSize, lastPageSize);
                setPage2(0, pageSize - lastPageSize);
                try {
                    List<T> list = merge(process((input) -> {
                        System.out.println("future1 start...");
                        int n = 5;
                        do{
                            try {
                                TimeUnit.MILLISECONDS.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("future1 ... " + n + " " + Thread.currentThread().getName());
                        }while (n-->=0);
                        ArrayList list_ = new ArrayList();
                        list_.add("zhangSan");
                        System.out.println("future1 end at " + new Date());
                        return list_;
                    }), process((input) -> {
                        System.out.println("future2 start...");
                        int n = 2;
                        do{
                            try {
                                TimeUnit.MILLISECONDS.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("future2 ... " + n + " " + Thread.currentThread().getName());
                        }while (n-->=0);
                        ArrayList list_ = new ArrayList();
                        list_.add("lisi");
                        System.out.println("future1 end at " + new Date());
                        return list_;
                    }));
                    System.out.println("complete at " + list);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                setPage2(0, pageSize);
            }
        } else {//多于1条
            if (lastPageSize > 0 && lastPageSize < pageSize) {//混合返回
                setPage2((pageSize - lastPageSize) + (currentPage - len - 2) * pageSize, pageSize);
            } else {
                setPage2((currentPage - len - 1) * pageSize, pageSize);
            }
        }
    }

    private void setPage1(int skip, int limit) {
        this.query1_skip = skip;
        this.query1_limit = limit;
    }

    private void setPage2(int skip, int limit) {
        this.query2_skip = skip;
        this.query2_limit = limit;
    }

    ExecutorService es = Executors.newFixedThreadPool(1);
    private <T> CompletableFuture<List<T>> process(Function<List, List<T>> fn) {
        CompletableFuture<List<T>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("process start... " + new Date());
            return fn.apply(null);
        },es);
        return future;
    }

    private <T> List<T> merge(CompletableFuture<List<T>> future1,
                              CompletableFuture<List<T>> future2)
            throws ExecutionException, InterruptedException {
        List<T> list = new ArrayList<T>();
        future1.thenAcceptBoth(future2, (list1, list2) -> {
            System.out.println("thenAcceptBoth at " + new Date());

            if (list1 != null) {
                list.addAll(list1);
            }
            if (list2 != null) {
                list.addAll(list2);
            }
        }).get();

        return list;

    }

}


