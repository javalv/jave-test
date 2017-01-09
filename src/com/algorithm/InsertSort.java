package com.algorithm;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.algorithm.InsertSort 
 * 描述: TODO  
 * 日期:     2017/1/9 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = { 3, 9, 6, 5, 2, 1, 7 };
        sort(a);
    }
    private static void sort(int a[]){
        //从第二个元素开始
        for(int i = 1 ; i < a.length; i++){
            int position = i;
            int temp = a[position];
            //比指定元素的值大,在指定元素位置之前的元素往后整体移动一位
            int j=position-1;
            for(;j>=0 && temp < a[j];j--){
                a[j+1] = a[j];
            }
            a[j+1] = temp;

            for (int n = 0; n < a.length; n++) {
                System.out.print(a[n]+",");
            }
            System.out.println();
        }
    }

}


