package com.algorithm;

import java.util.Arrays;

/**
 * Created by lin on 16/12/21.
 */
public class Binary {

    public static void main(String[] args) {
//        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12};
//        System.out.println(find(a,10));

        int[] b = {1,2,3,4,5,6,7,8,9,10,11,12,-1};
        insert(b,10);
    }

    public static boolean find(int[] a,int desc){

        int begin_idx = 0;
        int end_idx = a.length - 1;

        while (begin_idx <= end_idx){
            int m_idx = (end_idx + begin_idx) / 2;

            if(a[m_idx] == desc){
                return true;
            }else if(a[m_idx] > desc){
                end_idx = m_idx - 1 ;
            }else if(a[m_idx] < desc){
                begin_idx = m_idx + 1 ;
            }

            System.out.println(m_idx + "," + begin_idx + ", " +end_idx);
        }

        return false;

    }

    /**
     * 插入一个数字到已经排序好的数组中
     * @param a
     * @param desc
     * @return
     */
    public static void insert(int[] a,int desc){

        int begin_idx = 0;
        int end_idx = a.length - 1;

        int index = 0;
        while (begin_idx <= end_idx){
            int m_idx = (end_idx + begin_idx) / 2;
            index = m_idx;

            if(a[m_idx] == desc){
                break;
            }else if(a[m_idx] > desc){
                end_idx = m_idx - 1 ;
            }else if(a[m_idx] < desc){
                begin_idx = m_idx + 1 ;
            }

        }

        for (int i=a.length-1;i>index;i--){
            a[i] = a[i-1];
        }
        a[index] = desc;

        System.out.println(Arrays.toString(a));

    }
}
