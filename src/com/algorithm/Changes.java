package com.algorithm;

/**
 * Created by lin on 17/1/11.
 * 不依赖第三方,交换数据
 */
public class Changes {

    public static void main(String[] args) {

        int a = 10;
        int b = 7;

        change(a,b);

    }

    private static void change(int a,int b){
        System.out.printf("a:%d,b:%d\n",a,b);

        a = a+b;
        b = a-b;
        a = a-b;

        System.out.printf("a:%d,b:%d\n",a,b);
    }
}
