package com.demo.sort;

import java.util.Random;

/**
 * @author guochunyuan
 * @create on  2018-09-27 10:32
 */
public class ShellSort {


    /**
     * 希尔排序
     * @param list
     */
    public void shellSort(int[] list) {
        int gap = list.length / 2;

        while (1 <= gap) {
            // 把距离为 gap 的元素编为一个组，扫描所有组
            for (int i = gap; i < list.length; i++) {
                int j = 0;
                int temp = list[i];

                // 对距离为 gap 的元素组进行排序
                for (j = i - gap; j >= 0 && temp < list[j]; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }

            System.out.format("gap = %d:\t", gap);
            printPart(list,0,list.length-1);
            gap = gap / 2; // 减小增量
        }
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

        // 调用冒泡排序方法
        ShellSort insert = new ShellSort();
        System.out.print("排序前:\t\t");
        insert.printPart(array, 0, array.length - 1);
        insert.shellSort(array);
        System.out.print("排序后:\t\t");
        insert.printPart(array, 0, array.length - 1);
    }
}
