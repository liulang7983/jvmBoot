package com.controller;

import com.properties.InvoiceProperties;
import com.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ming.li
 * @date 2023/9/21 16:49
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    public  InvoiceProperties invoiceProperties;

    @Value("${invoice.age}")
    private Integer age;

    @Autowired
    private TestService testService;

    @RequestMapping("getInvoice")
    public String getInvoice(){
        String s = invoiceProperties.getName() + invoiceProperties.getImageMv();
        logger.info("获取的路径:"+s);
        return s;
    }

    @RequestMapping("getAge")
    public Integer getAge(){
        return age;
    }

    @RequestMapping("getArray")
    public List<String> getArray(){
        return testService.getArray();
    }
    @RequestMapping("refresh")
    public String refresh(){
        return testService.refresh();
    }

}
