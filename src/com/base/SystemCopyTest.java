package com.base;
/**
 * Copyright © 2016 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:com.base.SystemCopyTest 
 * 描述: TODO  
 * 日期:     2016/11/29 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class SystemCopyTest {

    public static void main(String[] args) {
//        ArrayList src = new ArrayList();
//        src.add("a");
//        src.add("b");
//        src.add("c");
        Set<String> set = new HashSet<String>();
        set.add("zhangsan");
        Object[] src = new Object[]{'a',set,'c'};
        Object[] target = new Object[3];
        System.arraycopy(src,0,target,0,3);

        target[0] = "zhangsan";
        ((Set)target[1]).add("lisi");
        target[2] = set;

        for (Object a : src){
            System.out.println(a);
        }
        System.out.println("===============");
        for (Object a : target){
            System.out.println(a);
        }
    }

}


