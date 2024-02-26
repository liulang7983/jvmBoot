package test;

/**
 * @author 李明
 * @date Created in 2024/2/26 23:31
 */
public class Demo27 {
    /*给定两个32位整数n和m，同时给定i和j，将m的二进制数位插入到n的二进制的第j到第i位,
    保证n的第j到第i位均为零，且m的二进制位数小于等于i-j+1，其中二进制的位数从0开始由低到高*/

    /*将m和n分别转换成二进制， 发现将m的二进制数位插入到n的二进制的第j到第i位，
    即将m左移j位后求和（或使用或运算）即为所求。代码非常简单，
    但是想到使用左移却不容易，可以在以后遇到题目中包含“二进制”时，考虑到移位运算符*/
    public static void main(String[] args) {
        int i = binInsert(1024,19,2,6);
        System.out.println(i);

    }
    public static int binInsert(int n, int m, int j, int i) {
        m<<=j;//m左移j位
        return m|n;//或m+n
    }

}
