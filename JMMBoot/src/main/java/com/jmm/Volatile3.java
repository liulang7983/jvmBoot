package com.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*Jmm03_CodeVisibility.refresh
 *      -Djava.compiler=NONE
 **/
@Slf4j
public class Volatile3 {

    /**
     此时线程A等待到线程B修改initFlag的值后是会往下走的，因为此时initFlag被volatile修饰，主内存发生修改是会同步到工作内存的
     因为initFlag他是被volatile修饰，while每次使用的时候都强制去主内存里取,所以发生修改时他被修改了
     */

    private volatile static boolean initFlag = false;

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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
    }
}
