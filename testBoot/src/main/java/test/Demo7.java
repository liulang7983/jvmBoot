package test;

public class Demo7 {
    /*给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，
    玩家B后拿，但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数*/
    public static void main(String[] args) {
        int[] arr={1,5,4,2,6};
        System.out.println(win1(arr));
    }

    //纯暴力的方法
    //根据规则，返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int first = f(arr, 0, arr.length - 1);
        int second = g(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    //arr[l...r] 先手获得的最好分数返回
    public static int f(int[] arr, int l, int r) {
        if (l == r) return arr[l];

        int p1 = arr[l] + g(arr, l + 1, r);
        int p2 = arr[r] + g(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    //arr[l...r]，后手获得的最好分数返回
    public static int g(int[] arr, int l, int r) {
        if (l == r) return 0;

        int p1 = f(arr, l + 1, r); //对手拿走了l位置的数
        int p2 = f(arr, l, r - 1); //对手拿走了r位置的数
        return Math.min(p1, p2);
    }

}
