package test;

import java.util.*;

public class Demo3 {
    /*给定一个正数数组arr，arr的累加和代表金条的总长度，arr的每个数代表金条要分成的长度。
    规定长度为k的金条分成两块，费用为k个铜板。返回把金条分出arr中的每个数字需要的最小代价
    如果先分成40和20两块，将花费60个铜板，再把长度为40的金条分成10和30两块，将花费40个铜板，总花费为100个铜板；
    如果先分成10和50两块，将花费60个铜板，再把长度为50的金条分成20和30两块，将花费50个铜板，总花费为110个铜板；
    如果先分成30和30两块，将花费60个铜板，再把其中一根长度为30的金条分成10和20两块，将花费30个铜板，总花费为90个铜板；
    因此最低花费为90*/
    public static void main(String[] args) {
        int[] arr={10,30,20,40};
        System.out.println(split(arr));
    }
    public static int split(int[] arr) {
        if(arr.length < 2) return 0;
        int res = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for(int num : arr) {
            System.out.println(num);
            queue.add(num); // 将所有数字加入队列，从小到大合并
        }              // 这样可以保证合并到只有两个数时，这两个数是最接近的，花费会最小
        while (queue.size() >= 2) {
            Integer poll = queue.poll();
            Integer poll1 = queue.poll();
            System.out.println("poll:"+poll);
            System.out.println("poll1:"+poll1);
            int sum = poll+poll1;// 每次取两个最小的合并
            res += sum;
            queue.add(sum);
        }
        return res;
    }

}
