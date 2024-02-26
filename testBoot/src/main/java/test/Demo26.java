package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 李明
 * @date Created in 2024/2/26 23:36
 */
public class Demo26 {
    /*给定一个整形数组arr，返回排序后相邻两数的最大差值
    arr = [9, 3, 1, 10]。如果排序，结果为[1, 3, 9, 10]，9和3的差为最大差值，故返回6。
    arr = [5, 5, 5, 5]。返回0*/
    public static void main(String[] args) throws Exception {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().trim().split(" ");*/
        int len=4;
        int[]  arr = {9,3, 1, 10};
      /*  for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }*/
        int res = getRes(arr);
        System.out.println(res);
    }

    public static int getRes(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        if (min == max) return 0;
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            //桶号
            bid = bucket(arr[i], len, max, min);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        //第一个桶里肯定有数
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long max, long min) {
        //这么做可以保证只有最大的值才能落入最后一栏
        return (int) ((num - min) * len / (max - min));
    }


}
