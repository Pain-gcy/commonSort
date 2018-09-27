package com.demo.sort;

import java.util.Random;

/**
 * @author guochunyuan
 * @create on  2018-07-23 16:10
 */
public class SelectSort {


    //选择排序
    private int[] selectSort(int[] arr) {
        int len = arr.length;
        int maxIndex, tmp;
        for (int i = 0; i < len - 1; i++) {
            maxIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] > arr[maxIndex])//查找最大的值放在最左边
                    maxIndex = j;
            }
            tmp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = tmp;
        }
        return arr;
    }

    // 打印序列
    public void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 初始化一个随机序列
        final int MAX_SIZE = 10;
        int[] array = new int[MAX_SIZE];
        Random random = new Random();
        for (int i = 0; i < MAX_SIZE; i++) {
            array[i] = random.nextInt(MAX_SIZE);
        }

        // 调用选择排序方法
        SelectSort selectSort = new SelectSort();
        System.out.print("排序前:\t\t");
        selectSort.printPart(array, 0, array.length - 1);
        selectSort.selectSort(array);
        System.out.print("排序后:\t\t");
        selectSort.printPart(array, 0, array.length - 1);
    }
}
