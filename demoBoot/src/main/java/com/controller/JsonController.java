package com.controller;

import com.bean.User;
import com.model.json.HexOCRResult;
import com.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
