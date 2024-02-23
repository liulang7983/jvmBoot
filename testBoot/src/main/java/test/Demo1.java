package test;

import java.util.Scanner;

public class Demo1 {
    //给定一个括号字符串str，返回最长的能够完全正确匹配括号字符字串的长度
    static int getMaxSeqLength(String str){
        int n=str.length();
        if(n==0||n==1) return 0;
        int left=0,right=0;
        int ans=0;
        for(int i=0;i<n;i++){
            char c=str.charAt(i);
            if(c=='(') left++;
            else right++;
            if(left==right){
                ans=ans>right*2?ans:right*2;
            }if(left<right){
                left=0;
                right=0;
            }
        }
        left=0;
        right=0;
        for(int i=n-1;i>=0;i--){
            char c=str.charAt(i);
            if(c=='(') left++;
            else right++;
            if(left==right){
                ans=ans>left*2?ans:left*2;
            }else if(left>right){
                left=0;
                right=0;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        while(cin.hasNext()){
            String str=cin.nextLine();
            System.out.println(getMaxSeqLength(str));
        }
    }

}
