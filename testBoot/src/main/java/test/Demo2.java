package test;

public class Demo2 {
    //给一个数组arr，其中只有一个数出现了奇数次，其它数出现了偶数次，打印这个数
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,2,2,2,2,3,3,3,3,3,3,12,12,13,3,3};//2就出现了奇数次
        //方法很简单，就是用异或的性质：N^0=N,N^N=0
        // 说明1次【奇数次】异或完还是自己，2次【偶数次】异或完为0
        int eor = 0;//0不影响异或的结果
        for (int i = 0; i < arr.length; i++) {
            eor=eor ^ arr[i];
            System.out.println( arr[i]);
            System.out.println("結果:"+eor);
        }
        System.out.println("---");
        System.out.println(eor);
        System.out.println(14^13);
    }
}
