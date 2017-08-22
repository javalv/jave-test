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
 * ClassName:com.lambda.Test
 * 描述: TODO  
 * 日期:     2017/1/6 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test2 {

    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            System.out.println("this is " + Thread.currentThread());
        });
        a.start();

        Runnable b = () -> System.out.println("this runnable is " + Thread.currentThread());
        b.run();
    }

}


