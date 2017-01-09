package com.algorithm;

/**
 * Created by lin on 16/12/22.
 */
public class BubberSort {
    public static void main(String[] args) {
        int[] a = { 3, 9, 6, 5, 2, 1, 7 };
        sort(a);
    }


    public static void sort(int[] a){
        for (int i = a.length - 1; i > 0; i--) {
            // 取出a[i]前边的数，和a[i]进行比较
            for (int j = 0; j < i; j++) {
                // 如果a[j]大于a[j+1],则进行交换数据，小数向上浮动，达到冒泡的效果
                if (a[j] > a[j + 1]) {
                    // 借助第三者达到位置的变化
                    int temp;
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
            //打印排序后的数组
            for (int n = 0; n < a.length; n++) {
                System.out.print(a[n]+",");
            }
            System.out.println();
        }

    }


}
