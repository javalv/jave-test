package com.serialization;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.serialization.RpcSerialTest
 * 描述: TODO
 * 日期:     2017/4/12
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class RpcSerialTest {
    public static void main(String[] args) {
        //Hello.hello("哈哈哈");

        //2.0.00!com.dubbo.apps.thrift.Hello$Iface0.0.0helloStringLjava/lang/String;哈哈ooooooHpath0!com.dubbo.apps.thrift.Hello$Iface	interface0!com.dubbo.apps.thrift.Hello$Ifacetimeout6000000version0.0.0Z
        byte[] dubbo = new byte[] {-38, -69, -62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -48, 5, 50, 46, 48, 46, 48, 48,
                33, 99, 111, 109, 46, 100, 117, 98, 98, 111, 46, 97, 112, 112, 115, 46, 116, 104, 114, 105, 102, 116,
                46, 72, 101, 108, 108, 111, 36, 73, 102, 97, 99, 101, 5, 48, 46, 48, 46, 48, 11, 104, 101, 108, 108,
                111, 83, 116, 114, 105, 110, 103, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114,
                105, 110, 103, 59, 8, -27, -109, -120, -27, -109, -120, 111, 111, 111, 111, 111, 111, 72, 4, 112, 97,
                116, 104, 48, 33, 99, 111, 109, 46, 100, 117, 98, 98, 111, 46, 97, 112, 112, 115, 46, 116, 104, 114,
                105, 102, 116, 46, 72, 101, 108, 108, 111, 36, 73, 102, 97, 99, 101, 9, 105, 110, 116, 101, 114, 102,
                97, 99, 101, 48, 33, 99, 111, 109, 46, 100, 117, 98, 98, 111, 46, 97, 112, 112, 115, 46, 116, 104, 114,
                105, 102, 116, 46, 72, 101, 108, 108, 111, 36, 73, 102, 97, 99, 101, 7, 116, 105, 109, 101, 111, 117,
                116, 7, 54, 48, 48, 48, 48, 48, 48, 7, 118, 101, 114, 115, 105, 111, 110, 5, 48, 46, 48, 46, 48, 90
        };
        System.out.println(new String(dubbo));

        // aڼ   a 6   !com.dubbo.apps.thrift.Hello$Iface        �    helloString       哈哈oooooo
        byte[] dubbo_thrift = new byte[] { 0, 0, 0, 97, -38, -68, 0, 0, 0, 97, 0, 54, 1, 0, 0, 0, 33, 99, 111, 109, 46,
                100, 117, 98, 98, 111, 46, 97, 112, 112, 115, 46, 116, 104, 114, 105, 102, 116, 46, 72, 101, 108, 108,
                111, 36, 73, 102, 97, 99, 101, 0, 0, 0, 0, 0, 0, 0, 0, -128, 1, 0, 1, 0, 0, 0, 11, 104, 101, 108, 108,
                111, 83, 116, 114, 105, 110, 103, 0, 0, 0, 1, 11, 0, 1, 0, 0, 0, 12, -27, -109, -120, -27, -109, -120,
                111, 111, 111, 111, 111, 111, 0

        };
        System.out.println(new String(dubbo_thrift));

        //   helloString       哈哈oooooo
        byte[] thrift = new byte[] { -128, 1, 0, 1, 0, 0, 0, 11, 104, 101, 108, 108, 111, 83, 116, 114, 105, 110, 103,
                       0, 0, 0, 1, 11, 0, 1, 0, 0, 0, 12, -27, -109, -120, -27, -109, -120, 111, 111, 111, 111, 111, 111, 0


        };
        System.out.println(new String(thrift));


    }
}


