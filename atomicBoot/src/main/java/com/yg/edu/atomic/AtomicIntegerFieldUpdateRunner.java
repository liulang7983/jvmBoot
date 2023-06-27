package com.yg.edu.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdateRunner {

    static AtomicIntegerFieldUpdater aifu = AtomicIntegerFieldUpdater.newUpdater(Student.class,"old");
    //此时是修改AtomicIntegerFieldUpdater定义的Student里面的old字段，aifu.getAndIncrement(stu)是字段+1返回原值
    //aifu.get(stu)获取现在的值
    public static void main(String[] args) {
        Student stu = new Student("杨过",18);
        //返回旧值
        System.out.println(aifu.getAndIncrement(stu));
        System.out.println(aifu.getAndIncrement(stu));
        System.out.println(aifu.get(stu));
    }
    static class Student{
        private String name;
        public volatile int old;
        public Student(String name ,int old){
            this.name = name;
            this.old = old;
        }
        public String getName() {
            return name;
        }
        public int getOld() {
            return old;
        }
    }

}
