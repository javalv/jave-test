package com.io;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.io.*;
import java.nio.ByteBuffer;

/**
 * ClassName:com.io.IoTest 
 * 描述: TODO  
 * 日期:     2017/5/16 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class IoTest {

    public static void main(String[] args) throws Exception {

        FileInputStream in = new FileInputStream("C:\\Users\\lvfang\\Desktop\\filter.log");

        ByteBuffer b = ByteBuffer.allocate(13893632);

        InputStream bin = new BufferedInputStream(in);
//        InputStream bin = new DataInputStream(in);

        long start = System.currentTimeMillis();

        byte[] bytes = new byte[10];
        int n = -1;
        while ((n = bin.read(bytes)) > 0){
            b.put(bytes,0,n);
            System.out.println(n);
        }

        b.flip();

        System.out.println(b.limit());

        System.out.println(bin.getClass() + ":" + (System.currentTimeMillis() - start));
    }
}


