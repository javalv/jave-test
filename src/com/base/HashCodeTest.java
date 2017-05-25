package com.base;

import java.util.HashMap;

/**
 * Created by lin on 16/11/27.
 */
public class HashCodeTest {

    static class A{
        String a = "abc";
        int b = 1;
    }

    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

//        A c = new A();
//        A b = new A();
//        System.out.println(c.hashCode());
//        System.out.println(b.hashCode());
//        System.out.println(b.equals(c));
//
//        HashMap map = new HashMap<>();
//        map.put("abc",123);
//        System.out.println(map.get("abc"));
    }
}
