package com.basetest;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.basetest.Node 
 * 描述: TODO  
 * 日期:     2017/7/20 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class Node {

    int i ;

    public Node(int i){
        this.i = i;
    }

    public int hashCode(){
        return 0x0001;
    }

    public String toString(){
        return i + "";
    }
}


