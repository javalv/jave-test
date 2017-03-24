package com.base;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.base.FinallyTest 
 * 描述: TODO  
 * 日期:     2017/3/23 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class FinallyTest {

    public static void main(String[] args) {
        FinallyTest test = new FinallyTest();
        System.out.println(test.test());
    }

    public Integer test(){
        int i = 0;
        try{
            i++;
            return getValue(i); // return的时候把i的值保存起来，不管finally里怎么执行
        }catch (Exception e){
            return null;
        }
        finally {
            i = 10;
            System.out.println("finally...");
        }
//        return getValue(i);
    }

    private int getValue(int i){
        System.out.println("getValue ... ");
        return i;
    }
}




