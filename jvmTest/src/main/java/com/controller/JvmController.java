package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/9/5 16:11
 * @Version 1.0
 */
@RestController
@RequestMapping("jvm")
public class JvmController {
    @RequestMapping("bigObject")
    public void bigObject(){
        List<Integer> list=new ArrayList<>();
        while (true){
            list.addAll(test());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Integer> test(){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            list.add(i);
        }
        return list;
    }
}
