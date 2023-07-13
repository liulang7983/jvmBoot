package com.yg.edu.pool;

/**
 * @author ming.li
 * @date 2023/3/20 13:38
 */
public class ThreadService implements Runnable{
    public Integer number;
    public String name;

    public ThreadService(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThreadService{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我的名字:"+name+",我的序号："+number);
    }
}
