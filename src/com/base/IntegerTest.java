package com.base;

/**
 * Created by lin on 16/11/23.
 */
public class IntegerTest {


    public static void main(String[] args) {
        Integer.valueOf("123");
        Integer.parseInt("123");

        // == 比较堆是栈, equals比较的是堆
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a==b);//false?
        System.out.println(a.equals(b));//true

        System.out.println(" ========== "  );

        a = 1;
        b = 1;
        System.out.println(a==b);//true
        System.out.println(a.equals(b));//true

        System.out.println(" ========== "  );

        // IntegerCache -128~127 同样缓存的还有 Long\Short\Character , Byte和Boolean全缓存
        a = 128;
        b = 128;
        System.out.println(a==b);//false
        System.out.println(a.equals(b));//true

        System.out.println(" ========== "  );

        String aa = new String("hello");
        String bb = new String("hello");
        System.out.println(aa == bb);//false

        aa = "hello";
        System.out.println(aa == bb);//false

        //string中也有缓存,string pool,当执行 aa="hello"的时候创建,Integer是加载时创建
        bb = "hello";
        System.out.println(aa == bb);//true

    }
}
