package com.lambda;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName:com.lambda.Test3
 * 描述: TODO
 * 日期:     2017/1/6
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test3 {

    public static void main(String[] args) {
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.1 使用匿名内部类根据 name 排序 players
//        Arrays.sort(players, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return (s1.compareTo(s2));
//            }
//        });

        Arrays.sort(players,(s1,s2) -> (s1.compareTo(s2)));


        for (String p:players) {
            System.out.println(p);
        }
    }

}


