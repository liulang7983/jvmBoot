package com.test.intTest;

/**
 * @Author ming.li
 * @Date 2025/1/17 13:49
 * @Version 1.0
 */
public class Demo8 {
    public static void main(String[] args) {
        int[] nums={3,2,2,3};
        int val=3;
        System.out.println(removeElement(nums,val));
    }
    public static int removeElement(int[] nums, int val) {
        int a=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                a++;
            }
        }
        return a;
    }
}
