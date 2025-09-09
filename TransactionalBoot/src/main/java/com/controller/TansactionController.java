package com.controller;

import com.bean.TansactionTest;
import com.service.TansactionService;
import com.service.TansactionService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2025/9/9 14:23
 * @Version 1.0
 */
@RestController
@RequestMapping("tansaction")
public class TansactionController {
    @Autowired
    private TansactionService tansactionService;
    @Autowired
    private TansactionService1 tansactionService1;
    //开启事务而且无异常，直接成功
    @RequestMapping("success")
    public String success(@RequestBody TansactionTest tansactionTest){
        return tansactionService.success(tansactionTest);
    }
    //开启事务而且有异常直接抛出，回滚
    @RequestMapping("fail")
    public String fail(@RequestBody TansactionTest tansactionTest){
        return tansactionService.fail(tansactionTest);
    }
    //未开始事务而且抛出异常，异常前已经执行的会提交
    @RequestMapping("failNoTansaction")
    public String failNoTansaction(@RequestBody TansactionTest tansactionTest){
        return tansactionService.failNoTansaction(tansactionTest);
    }
    //开启事务而且有异常,但是被tryCatch捕获，此时执行到的sql会提交
    @RequestMapping("failTryCatch")
    public String failTryCatch(@RequestBody TansactionTest tansactionTest){
        return tansactionService.failTryCatch(tansactionTest);
    }
    //此时开启了事务，也跑出了异常，但是修饰符非public,已经执行的会直接提交
    @RequestMapping("bedek")
    public String bedek(@RequestBody TansactionTest tansactionTest){
        return tansactionService1.bedek(tansactionTest);
    }
    //Spring 事务基于动态代理实现（JDK 代理或 CGLIB 代理），而动态代理需要生成目标类的子类并重写方法。
    // 如果方法被final或static修饰，子类无法重写该方法，代理机制失效，事务自然不会生效
    //现在就是可以打印日志，但是其他dao调用直接报错(java.lang.NullPointerException: null),
    // 意思就是我@Autowired的这个他调不到
    @RequestMapping("failFinal")
    public String failFinal(@RequestBody TansactionTest tansactionTest){
        return tansactionService1.failFinal(tansactionTest);
    }
}
