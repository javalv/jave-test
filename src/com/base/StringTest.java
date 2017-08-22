package com.base;

/**
 * Created by lin on 16/11/23.
 */
public class StringTest {


    public static void main(String[] args) {

        final String a_ = "hello";
        final String b_ = "Word";

        String aa = new String("helloWord");
        String bb = "helloWord";
        System.out.println(aa == bb);

        aa = "hello" + "Word";
        System.out.println(aa == bb);

        aa = new String("hello") + "Word";
        System.out.println(aa == bb);

        String a = "hello";
        aa = a + b_;
        System.out.println(aa == bb);

        String b = "Word";
        aa = a_ + b;
        System.out.println(aa == bb);

        String.valueOf("helloWord");

    }
}
