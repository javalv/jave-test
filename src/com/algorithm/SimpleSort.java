package com.algorithm;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.algorithm.SimpleSort 
 * 描述: TODO  
 * 日期:     2017/1/9 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] a = { 3, 9, 6, 5, 2, 1, 7 };
        sort(a);
    }
    private static void sort(int[] a){
        for(int i = 0; i<= a.length-1; i++){
            int min = a[i];
            int index = i;
            //在剩下的集合中找到最少的
            for(int j=i ; j < a.length -1 ;j++){
                if(min > a[j+1]){
                    min = a[j+1];
                    index = j+1;
                }
            }
            if(i != index){
                int tmp = a[i];
                a[i] = min;
                a[index] = tmp;
            }

            for (int n = 0; n < a.length; n++) {
                System.out.print(a[n]+",");
            }
            System.out.println();
        }
    }

}


