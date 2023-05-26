package com.yg.edu;

public class Main {

    public void method(){
        try {
            boolean flag = true;
            if(flag){
                throw new IllegalMonitorStateException();
            }
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        //Main main = new Main();
        //main.method();
    }

}
