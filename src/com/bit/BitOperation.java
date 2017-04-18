package com.bit;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.Arrays;

/**
 * ClassName:com.bit.BitOperation 
 * 描述: TODO  
 * 日期:     2017/4/18 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class BitOperation {

    public static void main(String[] args) {
        byte[] test = new byte[]{0b01110001,0b01010011,0b00010001};
        calculation(test);
    }

    private static void calculation(byte[] datas){
        int temp ;
        int pre = 0x00;
        Integer[] newb = new Integer[4];
        for (int i = 0 ;i<3;i++){
            byte b = datas[i];
            switch (i){
                case 0 :

                    temp = 0x03 & b;
                    pre = temp;
                    int t = b >> 2;
//                    System.out.println(Integer.toBinaryString(t));
                    newb[0] = t;
                    break;

                case 1 :

                    temp = 0x0F & b;
                    t = b >> 4;
                    t |= pre << 4;
//                    System.out.println(Integer.toBinaryString(t));
                    newb[1] = t;
                    pre = temp;
                    break;

                case 2 :

                    temp = 0x3F & b;
                    newb[3] = temp;
                    t = b >> 6;
                    t |= pre << 2;
//                    System.out.println(Integer.toBinaryString(t));
                    newb[2] =  t;
                    break;

            }
        }

        Arrays.asList(newb).stream().forEach(b -> {
            System.out.println(Integer.toBinaryString(b));//011100,010101,001100,010001
        });

    }

    //01110001,01010011,00010001
}


