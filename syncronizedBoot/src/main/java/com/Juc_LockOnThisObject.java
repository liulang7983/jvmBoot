package com;

import org.openjdk.jol.info.ClassLayout;


public class Juc_LockOnThisObject {

    private Integer stock = 10;

    public synchronized void decrStock(){
        --stock;
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
    }

    public static void main(String[] args) {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Juc_LockOnThisObject to = new Juc_LockOnThisObject();
        //System.out.println(ClassLayout.parseInstance(to).toPrintable());
        to.decrStock();

        Juc_LockOnThisObject to1 = new Juc_LockOnThisObject();
        to1.decrStock();
    }
}
