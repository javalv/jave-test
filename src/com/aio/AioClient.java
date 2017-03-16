package com.aio;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import org.omg.CORBA.SystemException;
import org.omg.CORBA.TIMEOUT;

import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:com.aio.AioClient 
 * 描述: TODO  
 * 日期:     2017/3/16 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class AioClient {
    public static void main(String... args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 9888));
        while (true){
            client.write(ByteBuffer.wrap(String.valueOf(System.currentTimeMillis()).getBytes())).get();
            TimeUnit.SECONDS.sleep(10);
        }
    }
}


