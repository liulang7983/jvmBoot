package com.controller;

/**
 * @Author ming.li
 * @Date 2025/5/22 9:57
 * @Version 1.0
 */

import com.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HeapDumpController {

    private List<User> list = new ArrayList<>();

    @GetMapping("/heap")
    public String heapOOM() {
        while (true) {
            list.add(new User(1L,"zhangsan","zhangsan"));
        }
    }
}
