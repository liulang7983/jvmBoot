package com.controller;

import com.bean.ApiResult;
import com.bean.User1;
import com.model.json.HexOCRResult;
import com.service.JsonService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/5/31 17:58
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public ApiResult getUser(){

        return userService.getUser();
    }


}
