package com.classloader;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * ClassName:com.classloader.CustomClassTest 
 * 描述: TODO  
 * 日期:     2017/1/4 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class CustomClassTest {
    public static void main(String[] args) {
        /**要进行热加载的类名**/
        String name = "com.classloader.Printer";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IPrinter printer = null;
        while (true) {
            System.out.println("输入任意字符进行热加载，直接敲回车键退出程序");
            try {
                String line = reader.readLine();
                if(line != null && line.length() > 0){
                    CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader() , name);
                    Class<?> clazz = loader.loadClass();
                    /**
                     * 被子加载器加载的类拥有被父加载器加载的类的可见性
                     * Printer是由自定义类加载器加载的，
                     * 而它的父类IPrinter是由系统类加载器加载的，
                     * 因此IPrinter对于Printer具有可见性，
                     * 因此转型成功，并不会因为类加载器不同导致ClassCastException异常
                     */
                    printer = (IPrinter) clazz.newInstance();
                    /**看看是否热加载成功了**/
                    printer.print();
                }else{
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}


