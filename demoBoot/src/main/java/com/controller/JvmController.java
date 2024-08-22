package com.controller;

import com.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/8/13 16:37
 * @Version 1.0
 */
@RestController
@RequestMapping("jvm")
public class JvmController {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,4,0, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    @RequestMapping("/test1")
    public List<User> test1(){
        List<User> list=new ArrayList<>();
        for (int i = 0; i <200 ; i++) {
            for (int j = 0; j < 300000; j++) {
                list.add(new User(1L,"张三","张三"));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
    //此时会死锁
    @RequestMapping("/test2")
    public String test2(){
        for (int i = 0; i <20 ; i++) {
            threadPoolExecutor.submit(()->getUser());
        }
        return "SS";
    }
    public void getUser(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatch=new CountDownLatch(1);
        threadPoolExecutor.submit(()->getUser1(countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getUser1(CountDownLatch countDownLatch){
        System.out.println("进来了");
        countDownLatch.countDown();
    }
}
