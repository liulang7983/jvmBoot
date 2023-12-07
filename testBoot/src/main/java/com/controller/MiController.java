package com.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/12/6 15:06
 */
@RestController
public class MiController {
    @RequestMapping("hexai/fas/extern/getOcr")
    public JSONObject getOcr(String str){
        System.out.println(str);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("status",true);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }
}
