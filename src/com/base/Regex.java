package com.base;
/**
 * Copyright © 2016 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.base.Regex 
 * 描述: TODO  
 * 日期:     2016/12/27 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Regex {

    public static void main(String[] args) {
        String a = "abc.def.";
        System.out.println(a.replaceFirst("[.]",""));
    }
}


