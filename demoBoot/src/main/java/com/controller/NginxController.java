package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2025/9/16 10:13
 * @Version 1.0
 */
@RestController
@RequestMapping("nginx")
public class NginxController {
    //解析有多个sheet的execl
    @RequestMapping("/test")
    public String test() {
        System.out.println("调用成功");
        return "成功";
    }
}
