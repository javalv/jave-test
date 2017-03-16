package com.lambda;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:com.lambda.StreamTest 
 * 描述: TODO  
 * 日期:     2017/3/16 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class StreamTest {
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList();
        Integer[] nn = {1,1,2,3,4,5,6,7,8,9,10};
        for(int n:nn){
            nums.add(n);
        }
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));
        System.out.println(numsWithoutNull);
    }
}


