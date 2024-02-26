package test;

import java.util.Scanner;

/**
 * @author 李明
 * @date Created in 2024/2/26 23:11
 */
public class Demo28 {
    /*有一种兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子。
    例子：假设一只兔子第3个月出生，那么它第5个月开始会每个月生一只兔子。
    一月的时候有一只兔子，假如兔子都不死，问第n个月的兔子总数为多少？*/
    public static void main(String[] args) {

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要查询的月份：");
        int month = scanner.nextInt();*/
        //1,1,2,3,5,8,13,21....
        System.out.println(getCount(5));
    }

    public static Integer getCount(Integer a){
        if (a==1||a==2){
            return 1;
        }else {
            return getCount(a-1)+getCount(a-2);
        }
    }

}
