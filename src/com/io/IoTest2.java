package com.io;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:com.io.IoTest 
 * 描述: TODO  
 * 日期:     2017/5/16 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class IoTest2 {

    public static void main(String[] args) throws Exception {


        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-12.1.log");
        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-12.2.log");
        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-12.3.log");
        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-11.1.log");
        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-11.2.log");
        readFileByLines("C:\\Users\\lvfang\\Desktop\\票夹\\filter.log.2017-07-11.3.log");



        readFileByLines1("C:\\Users\\lvfang\\Desktop\\票夹\\android补.txt");

                for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey()) ;
        }
    }

    private static HashMap<String,Integer> map = new HashMap<>();

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号 tempString.contains("2.0.0") &&
                if(
//                            tempString.contains("different_download")&&
                                    tempString.contains("android")
//                                    &&
//                                    tempString.contains("update_id=1.")
                            ){
                    int s = tempString.indexOf("\"user_id\":\"") + 11;
                    int e = tempString.indexOf("\",\"device\":\"android");
                    int e1 = tempString.indexOf("\",\"merchant_token");
//                        int s = tempString.indexOf("\"user_id\":\"") + 11;
//                        int e = tempString.indexOf("\",\"merchant_id\"");
//                        int e1 = tempString.indexOf("\",\"data_version\"");
                        String code = null;
                        if(e > s){
                        code = tempString.substring(s,e);
                    }else if(e1>s) {
                        code = tempString.substring(s,e1);
                    }
                    if(code == null){
//                        System.out.println(tempString);
                    }
                    if(map.get(code) == null){
                        map.put(code,1);
                    }else {
                        int n = map.get(code);
                        map.put(code,++n);
                    }

                }
                line++;
            }
            System.out.println(map.size());

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines1(String fileName) {
        Map<String,Integer> map1 = new HashMap<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(map.containsKey(tempString)){
                   map.remove(tempString);
                }

                map1.put(tempString,1);
            }


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }


//        for (Map.Entry entry : map1.entrySet()){
//            System.out.println(entry.getKey()) ;
//        }

    }
}


