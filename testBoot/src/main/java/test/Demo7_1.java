package test;

public class Demo7_1 {
    public static int head=0;
    public static int tail=0;
    /*给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，
    玩家B后拿，但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数*/
    public static void main(String[] args) {
        int[] arr={1,5,4,2,5,4};
        tail=arr.length-1;
        System.out.println(win1(arr));
    }

    //纯暴力的方法
    //根据规则，返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int second=0,first=0;
        for (int i = 0; i <arr.length ; i++) {
            if (i%2==0){
                System.out.println(i);
                first+=getMax(arr);
            }else {
                second=getMax(arr);
            }
        }
        return Math.max(first,second);
    }
    public static int getMax(int[] arr){
        System.out.println("head:"+head+",tail:"+tail);
        int headNum = arr[head];
        int tailNum = arr[tail];
        if (headNum>tailNum){
            head++;
            return headNum;
        }else {
            tail--;
            return tailNum;
        }
    }
}
