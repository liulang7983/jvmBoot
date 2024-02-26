package test;

import java.util.ArrayList;

/**
 * @author 李明
 * @date Created in 2024/2/26 22:37
 */
public class Demo29 {
    /*假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半;
    再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高*/
    public static void main(String[] args) {
        Double h=1d;
        ArrayList<Double> arrayList = new ArrayList<Double>();
        double sum=0;
        arrayList.add(h);
        for (int i = 0; i < 5; i++) {
            double h1 = h / 2;
            //第一次落地后,以后每次落地都会运动2倍的高度且第五落地后的距离不再计算入内
            if(i<4){
                arrayList.add(2*h1);
            }
            h = h1;
        }
        for (Double d : arrayList) {
            sum+=d;
        }
        System.out.println(sum);
        System.out.println(h);

    }
}
