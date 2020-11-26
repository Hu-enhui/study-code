package fun.enhui.algorithm.base.a_binary;

import fun.enhui.algorithm.base.a_sort.SelectionSort;
import fun.enhui.algorithm.sdk.ArrayUtil;
import fun.enhui.algorithm.sdk.BaseSort;

/**
 * 二分查找
 * 有序数组中，找<=某个数最右侧的位置
 *
 * @author 胡恩会
 * @date 2020/11/15 23:50
 */
public class BinarySearch_03 {
    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 500000;
        boolean succed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);
            BaseSort sort = new SelectionSort();
            sort.sort(arr);
            int random = (int) (Math.random() * maxValue - Math.random() * maxValue);
            int positionByBinary = binarySearch(arr, random);
            int positionByCycle = cycleSearch(arr, random);
            if (positionByBinary != positionByCycle) {
                succed = false;
                ArrayUtil.printArray(arr);
                System.out.println(random);
                System.out.println("循环查找" + positionByCycle);
                System.out.println("二分查找" + positionByBinary);
                break;
            }
        }
        System.out.println(succed ? "Nice!" : "Fucking fucked!");
    }

    /**
     * 二分查找
     *
     * @param sortedArr:
     * @param random:
     * @Author: 胡恩会
     * @Date: 2020/6/6 13:32
     * @return: int
     **/
    public static int binarySearch(int[] sortedArr, int random) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int index = -1;
        int L = 0, R = sortedArr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = R + ((L - R) >> 1);
            if (sortedArr[mid] <= random) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    /**
     * 遍历数组查找
     *
     * @param sortedArr:
     * @param random:
     * @Author: 胡恩会
     * @Date: 2020/6/6 13:31
     * @return: int
     **/
    private static int cycleSearch(int[] sortedArr, int random) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int index = -1;
        for (int i = sortedArr.length - 1; i >= 0; i--) {
            if (sortedArr[i] <= random) {
                index = i;
                break;
            }
        }
        return index;
    }
}