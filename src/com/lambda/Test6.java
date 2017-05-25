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
 * ClassName:com.lambda.Test6 
 * 描述: TODO  
 * 日期:     2017/5/15 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Test6 {

    public static void main(String[] args) {
        String[] players = {"Rafael Nadal","lisi"};

        List<String> list = Arrays.asList(players);
        try{
            list.stream().forEach((p) -> {
                System.out.println(p);
                return ;
            });
        }catch (Exception e){
            System.out.println("outer exception ");
        }
    }

}


