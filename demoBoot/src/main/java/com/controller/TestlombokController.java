package com.controller;

import com.bean.Testlombok;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/8/11 10:58
 */
@RestController
public class TestlombokController {

    @RequestMapping("testlombok")
    public String testlombok(){
        Testlombok testlombok=new Testlombok();
        testlombok.setCount(1);
        return "成功";
    }
}
