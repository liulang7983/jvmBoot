package com.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*Jmm03_CodeVisibility.refresh
 *      -Djava.compiler=NONE
 **/
@Slf4j
public class Volatile2 {


 //    此时线程是会卡住在线程A的while的,线程B修改了initFlag的值对他无感，线程A开启的时候读取到了false，此时不会感知到修改

    //此时有无volatile完全不一样
    private  static  boolean initFlag = false;

    private  static int counter = 0;

    public static void refresh(){
        System.out.println("refresh data.......");
        initFlag = true;
        System.out.println("refresh data success.......");
    }

    public static void main(String[] args){
        Thread threadA = new Thread(()->{
            System.out.println("进入线程A");
            while (!initFlag){
                //System.out.println("runing");
                counter++;
            }
            System.out.println("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
    }
}
