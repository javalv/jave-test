package com.lambda;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.Arrays;
import java.util.List;

/**
 * ClassName:com.lambda.Test1 
 * 描述: TODO  
 * 日期:     2017/1/6 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test1 {

    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // 以前的循环方式
//        for (String player : players) {
//            System.out.print(player + "; ");
//        }

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((String player) -> System.out.println(player));


        // 在 Java 8 中使用双冒号操作符(double colon operator)
//        players.forEach(System.out::println);
    }

}


