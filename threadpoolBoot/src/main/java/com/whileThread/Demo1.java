package com.whileThread;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/11/9 11:45
 */
public class Demo1 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(3,6,300L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(200));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Future<String> future = executor.submit(() -> {
                return new DemoService().getStr();
            });
                futureList.add(future);
        }
        for(Future<String> f:futureList){
            while (true) {
                //CPU高速轮询：每个future都并发轮循，判断完成状态然后获取结果，
                // 这一行，是本实现方案的精髓所在。即有10个future在高速轮询，完成一个future的获取结果，就关闭一个轮询

                //f.isDone()如果此任务已完成，则返回true。完成可能是由于正常终止、异常或取消——在所有这些情况下，此方法将返回true
                if (f.isDone() && !f.isCancelled()) {
                    //获取future成功完成状态，
                    // 如果想要限制每个任务的超时时间，取消本行的状态判断+future.get(1000*1, TimeUnit.MILLISECONDS)+catch超时异常使用即可。
                    String s = f.get();//获取结果
                    if (s == null) {
                        break;
                    }
                    System.out.println(s);
                    break;
                } else {
                    Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                }
            }
        }
    }
}
