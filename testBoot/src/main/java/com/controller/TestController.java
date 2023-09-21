package com.controller;

import com.properties.InvoiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/9/21 16:49
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    public  InvoiceProperties invoiceProperties;
    @RequestMapping("getInvoice")
    public String getInvoice(){
        return invoiceProperties.getName();
    }
}
