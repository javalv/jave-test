package com.gc;
/**
 * Copyright © 2016 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:com.gc.GCTest 
 * 描述: TODO  
 * 日期:     2016/12/30 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class GCTest {
    public static void main(String[] args) {
        oldGc();
    }

    /**
     * 使用到s0/s1，占比85%，老生代使用率是0%
     * PSYoungGen      total 19456K, used 13346K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
         eden space 18432K, 67% used [0x00000000fec00000,0x00000000ff82cb58,0x00000000ffe00000)
         from space 1024K, 85% used [0x00000000fff00000,0x00000000fffdc020,0x0000000100000000)
         to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
         ParOldGen       total 30720K, used 8K [0x00000000fce00000, 0x00000000fec00000, 0x00000000fec00000)
         object space 30720K, 0% used [0x00000000fce00000,0x00000000fce02000,0x00000000fec00000)
     *
     */
    private static void yongGc(){
        for(int i=0;i<100000;i++){
            byte[] b = new byte[1 * 1024];
        }
    }


    /**
     * 因为都是老生代对象，所以不会使用到s0/s1，回收时直接放到老生代了
     *  PSYoungGen      total 17920K, used 2716K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
         eden space 15360K, 17% used [0x00000000fec00000,0x00000000feea7020,0x00000000ffb00000)
         from space 2560K, 0% used [0x00000000ffd80000,0x00000000ffd80000,0x0000000100000000)
         to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
         ParOldGen       total 30720K, used 28961K [0x00000000fce00000, 0x00000000fec00000, 0x00000000fec00000)
         object space 30720K, 94% used [0x00000000fce00000,0x00000000fea48708,0x00000000fec00000)
     *
     */
    private static void oldGc(){
        List<byte[]> list = new ArrayList<>();
        for(int i=0;i<30000;i++){
            byte[] b = new byte[1 * 1024];
            list.add(b);
        }
    }
}


