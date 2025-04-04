package com.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*Jmm03_CodeVisibility.refresh
 *      -Djava.compiler=NONE
 **/
@Slf4j
public class Volatile1 {

    /**
     此时线程A是会走下去的，因为counter被volatile修饰，此时也会导致线程A工作内存里面的initFlag重新去主内存里面取
     */

    private static boolean initFlag = false;

    private volatile static int counter = 0;

    public static void refresh(){
        System.out.println("refresh data.......");
        initFlag = true;
        System.out.println("refresh data success.......");
    }

    public static void main(String[] args){
        System.out.println("counter:"+counter);
        Thread threadA = new Thread(()->{
            while (!initFlag){
                //System.out.println("runing");
                counter++;
            }
            System.out.println("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
        System.out.println("counter:"+counter);
    }
}
