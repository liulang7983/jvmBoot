package com.service.impl;

import com.pool.ThreadService;
import com.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/8/4 14:13
 */
public class ThreadPoolServiceImpl implements ThreadPoolService {
    @Value("${threadPool.corePoolSize}")
    public Integer corePoolSize;
    @Value("${threadPool.maximumPoolSize}")
    public Integer maximumPoolSize;
    private static ThreadPoolExecutor threadPoolExecutor=null;
    @PostConstruct
    public void initialize(){
        threadPoolExecutor=new ThreadPoolExecutor(corePoolSize,maximumPoolSize,300, TimeUnit.SECONDS,new LinkedBlockingDeque<>(200));
    }
    @Override
    public void createList() {
        for (int i = 0; i < 200; i++) {
            ThreadService service = new ThreadService(i, "张三");
            threadPoolExecutor.submit(service);
        }
    }
}
