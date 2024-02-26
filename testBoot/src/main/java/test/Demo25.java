package test;

import java.util.Scanner;

/**
 * @author 李明
 * @date Created in 2024/2/26 23:46
 */
public class Demo25 {
    //给定一个数字arr，其中只有有两个数字出现了奇数次，其它数字都出现了偶数次，按照从小到大顺序输出这两个数

    /*数组中只有两个数出现了 1 次，其他数都出现了 2 次，所以只要将出现 1 次的两个数放入不同的数组中，
    并保证数组中其他的数都出现 2 次的。将两个数组中的值连续异或就能得到出现 1 次的数。
    如{3, 4, 4}: 3 ^ 4 = 7, 7 ^ 4 = 3.
    如何将两个数分到不同的数组呢，如 3 ^ 4 = 7,7 为 111


    异或结果为 1 的位，说明参与异或运算的两个数的该位一个是 1 一个是 0，所以只要找到异或结果位为 1 的位置，
    将该位置位是 1 的数字分到一组，位是 0 的数字分到一组就能保证出现一次的数被分别分到一组，
    并组的其他数出现的次数为 2（两个相同的数各个位数相同）*/

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int x = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                x ^= arr[i];
            }
            //此时 x 为两个出现 1 次的数的异或结果
            int index = 0;  //index 记录 x 第一个位为 1 的位置
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    index = i;
                    break;
                }
            }
            //将数组分为两部分
            int num1 = 0;   //第一部分的异或结果
            int num2 = 0;   //第二部分的异或结果
            for (int i = 0; i < n; i++) {
                if (((arr[i] >> index) & 1) == 1) {     //index位为 1
                    num1 ^= arr[i];
                } else {    //index位为 0
                    num2 ^= arr[i];
                }
            }
            // 从小到大输出
            if (num1 < num2) {
                System.out.println(num1 + " " + num2);
            } else {
                System.out.println(num2 + " " + num1);
            }
        }
    }
}
