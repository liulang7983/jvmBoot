package com.controller;

import com.bean.ApiResult;
import com.bean.User;
import com.bean.User1;
import com.model.json.HexOCRResult;
import com.service.JsonService;
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
@RequestMapping("json")
public class JsonController {

    @Autowired
    private JsonService jsonService;

    @RequestMapping("/youtu")
    public HexOCRResult youtu(){

        return jsonService.youtu();
    }
    @RequestMapping("/youtuHigh")
    public HexOCRResult youtuHigh(){

        return jsonService.youtuHigh();
    }

    @RequestMapping("/longToString")
    public ApiResult longToString(){
        User1 user = new User1();
        user.setId(1L);
        user.setName("张三");
        List<User1> list=new ArrayList<>();
        list.add(user);
        return new ApiResult(list);
    }

}
