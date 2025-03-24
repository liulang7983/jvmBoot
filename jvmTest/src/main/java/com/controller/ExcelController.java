package com.controller;

import com.Util.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/3/24 11:17
 * @Version 1.0
 */
@RestController
@RequestMapping("excel")
public class ExcelController {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,2,1000, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    @RequestMapping("getExecl")
    public String getExecl(){
        File file = new File("C:\\liming\\gitLiming\\jvmBoot1\\jvmTest\\src\\main\\resources\\file");
        File[] files = file.listFiles();
        CountDownLatch countDownLatch=new CountDownLatch(files.length);
        for (File file1:files){
            threadPoolExecutor.execute(()-> saveExecl(file1.getPath(),countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "成功";
    }
    public void saveExecl(String filePath, CountDownLatch countDownLatch){
        ExcelUtils.autoColWidth(filePath);
        countDownLatch.countDown();

    }
}
