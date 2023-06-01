package com.controller;

import com.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/5/31 17:58
 */
@RestController
@RequestMapping("json")
public class JsonController {

    @RequestMapping("/field")
    public String field(){
        User user = new User(1, "张三", "是憨憨");
        User user1 = new User(1, "李四", "是憨憨");
        return null;
    }
}
