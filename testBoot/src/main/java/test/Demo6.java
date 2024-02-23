package test;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Demo6 {
    //给定一个整型数组arr，再给定一个整数k，打印所有出现次数大于n/k的数，如果没有这样的数,请打印”-1“
    // 用于存储n/k的值
    public static int nlk;
    // 利用表存储数据
    public static Map<Integer, Integer> map;
    // 数据初始化
    public static void init(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        System.out.println("n:"+n+",k"+k);
        nlk = n / k;
        map = new TreeMap<Integer, Integer>();
        int temp;
        Object target;
        for(int i=0; i<n; i++){
            temp = scan.nextInt();
            // 如果元素存在，就设置为 value+1
            if((target = map.get(temp)) != null) {
                map.replace(temp, (Integer) target + 1);
            }else {
                // 如果元素不存在，就设置为(key, 1)
                map.put(temp, 1);
            }
        }
        scan.close();
    }

    public static void print() {

        // 获取key的迭代器,在HashMap中以Integer为key的map中,获取到的key是有序的
        Iterator<Integer> iterator = map.keySet().iterator();
        int key;

        // 统计是否有符合条件的元素
        int count = 0;
        while(iterator.hasNext()) {
            key = iterator.next();
            int temp = map.get(key);
            if(temp > nlk) {
                count ++;
                System.out.print(key + " ");
            }
        }
        // 如果没有这样的元素就打印为-1
        if(count == 0) {
            System.out.println(-1);
        }
    }

    public static void main(String[] args){
        init();
        print();
    }
}
