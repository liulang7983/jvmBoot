package com.yg.edu.jmm;


public class Jmm02_CpuCache {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs;

//     空间局部性（Spatial Locality）：如果一个存储器的位置被引用，那么将来他附近的位置也会被引用。
//     比如顺序执行的代码、连续创建的两个对象、数组等
//     所以此时循环long[i][j]的时候,此时先循环i再在i里面循环j，此时循环j的时候会把他后续的连续空间也取过来存放到L1，速度更快
//     但是先循环j再循环i，此时j取了连续空间也是没有用的，因为下一次加取的是下一个i的相同位置的j，此时是不连续的需要再次回到L3取值,速度更慢
    public static void main(String[] args) throws Exception {
        /*
         * 初始化数组
         */
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("初始化完毕....");

        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {//DIMENSION_1=1024*1024
                for (int j=0;j<DIMENSION_2;j++){//6
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("long[i][j]在i循环j  spend time1:"+(System.currentTimeMillis()-start));
        System.out.println("sum1:"+sum);

        sum = 0L;
        start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int j=0;j<DIMENSION_2;j++) {//6
                for (int i = 0; i < DIMENSION_1; i++){//1024*1024
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("long[i][j]在j循环i spend time2:"+(System.currentTimeMillis()-start));
        System.out.println("sum2:"+sum);
        /**
         解释:空间局限性所谓的连续空间指的是比如longs[1][0],longs[1][1],longs[1][2],longs[1][3],longs[1][4]
         那么代码中的第一段求和就是前面的不变，后面的0-5相加，再前面的变，再0-5相加，空间上0-5是连续的，一次可能六个全部读取，可能读取次数是1024*1024
         第二段的话是比如long[0][0],long[1][0],long[2][0],long[3][0],long[4][0],long[5][0],从空间上是不连续的，每次都需要一个一个去读，
         那此时的读取速度可能就是1024*1024*6
         */

    }
}
