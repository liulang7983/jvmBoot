package com;

import com.bean.*;
import com.config.MyEnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author ming.li
 * @date 2023/9/21 16:14
 */
@SpringBootApplication
@Import({User.class})
@MyEnable
public class TestMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestMain.class, args);
        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean(Role.class));
        System.out.println(context.getBean(Invoice.class));
        System.out.println(context.getBean(InvoiceCheck.class));
        System.out.println(context.getBean(CheckInvoice.class));
        System.out.println(context.getBean(Task.class));
    }
}
