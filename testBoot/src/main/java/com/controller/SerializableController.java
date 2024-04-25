package com.controller;

import com.bean.Compare;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author ming.li
 * @Date 2024/4/25 10:17
 * @Version 1.0
 */
@RestController
@RequestMapping("serializable")
public class SerializableController {

    @RequestMapping("save")
    public String save(@RequestBody Compare compare)throws Exception{
        String path="1.txt";
        File file = new File(path);
        if (!file.exists()){
            Files.createFile(Paths.get(path));
        }
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(compare);
        return "成功";
    }

    @RequestMapping("get")
    public Compare get()throws Exception{
        String path="1.txt";
        File file = new File(path);
        if (!file.exists()){
            return null;
        }
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        Object o = stream.readObject();
        Compare compare=(Compare)o;
        return compare;
    }
}
