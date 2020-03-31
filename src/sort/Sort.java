package sort;

import java.util.Arrays;

public class Sort {
    /**
     * 冒泡排序
     * 思想：两两交换
     * @param arr 待排序得数组
     */
    public static void bubbleSort(int arr[]) {
        //设置标识位 如果排序在中途已经完成我们就不需要在让它继续排序下去直接跳出即可
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //在一趟排序中一次都没发生交换
            if (!flag) {
                break;
            } else {
                //重置标识用于下次
                flag = false;
            }
        }
    }

    /**
     * 选择排序
     * 思想：选定最小值 标记最小索引
     * @param arr 待排序得数组
     */
    public static void selectSort(int arr[]) {
        int min;
        int minIndex;
        int count = 0;
        //开始比较一共length - 1 次
        for (int i = 0; i < arr.length - 1; i++) {
            //设最小值
            min = arr[i];
            //记录索引
            minIndex = i;
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值依次赋值
            if (minIndex != i) {
                //例如i=0将第一个元素和最小索引(最小值)及进行交换
                arr[minIndex] = arr[i];
                //例如i=0将最小值赋给第i个
                arr[i] = min;
            }
        }
    }

    /**
     * 插入排序
     * 思想：选定插入值 记录下标索引
     * @param arr 待排序得数组
     */
    public static void insertSort(int arr[]){
        //保存insertValue
        int insetValue = 0;
        //和前面的下标比较
        int insertIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            insetValue = arr[i + 1];
            insertIndex = i;
            /**
             * 给 insertIndex >= 0 找插入位置是不能越界
             *  insertValue < arr[insertIndex] 说明待插入的数还没有找到适当的位置
             *  需要将arr[insertIndex] 需要后移
             */
            while(insertIndex >= 0 && arr[insertIndex] > insetValue){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }
            //举例 如果插入位置 0 那么我们保存 位置1 的值进行操作
            //举例 如果插入位置 1 那么我们保存 位置2 的值进行操作
            //当退出while循环时说明插入位置找到了，位置即是insertIndex + 1
            arr[insertIndex + 1] = insetValue;
        }
    }
    //希尔排序 交换法低效率
    public static void shellSort(int arr[]){
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /=  2){
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] =  arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 思想:取一个Key以Key为中值划分左右然后将其递归循环
     * @param arr 传入得数组
     * @param left 左起始位置或者说左指针
     * @param right 右起始位置或者说右指针
     */
    public static void quickSort(int arr[], int left, int right){
        int tempLeft = left;
        int tempRight = right;
        //记录Key
        int pivotKey = arr[tempLeft];
        //循环条件
        while (tempRight > tempLeft){
            //当右边的值与Key比较将大于Key得放过并左移
            while (tempRight > tempLeft && arr[tempRight] > pivotKey){
                tempRight--;
            }
            if (tempRight > tempLeft) {
                //将右边的值分配给左边 || pivotKey已经记录了Key值
                arr[tempLeft] = arr[tempRight];
                //此数字是被调整过来的所以不需在比较了放过即可
                tempLeft++;
            }
            //当左边的值与Key比较将小于Key得放过并右移
            while (tempRight > tempLeft && arr[tempLeft] < pivotKey){
                tempLeft++;
            }
            if (tempRight > tempLeft) {
                //将左边得值赋值给右边 || pivotKey已经记录了Key值
                arr[tempRight] = arr[tempLeft];
                //此数字是被调整过来得不需在比较了所以放过即可
                tempRight--;
            }
        }
        //当左右相等时说明已经此轮比较完毕了所以将Key值放回其中
        if (tempLeft == tempRight){
            arr[tempLeft] = pivotKey;
        }

        //左半边递归
        if (left < tempRight - 1){
            quickSort(arr,left,tempRight - 1);
        }
        //右半边递归
        if (right > tempLeft + 1){
            quickSort(arr,tempLeft + 1,right);
        }
    }
}
