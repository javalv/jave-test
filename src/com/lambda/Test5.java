package com.lambda;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName:com.lambda.Test5 
 * 描述: 函数方式并不是函数回调，还是在同一个线程内
 * 日期:     2017/5/4 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test5 {

    public static void main(String[] args) {
        String[] players = {"Rafael Nadal"};

        List<String> list = Arrays.asList(players);
        try{
            list.stream().forEach((p) -> {
                try{
                    Long.valueOf(p);
//                    doError();
                }catch (Throwable e){
                    System.out.println("inner exception ");
                    throw e;
                }
            });
        }catch (Exception e){
            System.out.println("outer exception ");
//            e.printStackTrace();
        }

    }

    private static void doError() throws Exception{
        throw new RuntimeException();
    }


}


