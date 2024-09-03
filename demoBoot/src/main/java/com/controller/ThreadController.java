package com.controller;

import com.bean.ApiResult;
import com.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2024/9/2 10:15
 * @Version 1.0
 */
@RestController
@RequestMapping("thread")
public class ThreadController {
    @Autowired
    public ThreadService threadService;

    @RequestMapping("getThread")
    public ApiResult getThread(){
       return threadService.getThread();
    }

}
