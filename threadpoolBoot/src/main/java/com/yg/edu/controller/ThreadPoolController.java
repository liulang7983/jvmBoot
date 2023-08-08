package com.yg.edu.controller;

import com.yg.edu.pool.ThreadService;
import com.yg.edu.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/8/4 14:11
 */
@RestController
@RequestMapping("threadPool")
public class ThreadPoolController {

    @Autowired
    public ThreadPoolService threadPoolService;

    @RequestMapping("createList")
    public String createList(){
        threadPoolService.createList();
        return "成功";
    }
}
