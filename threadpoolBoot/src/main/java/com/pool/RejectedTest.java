package com.pool;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.*;

/**
 * @author ming.li
 * @date 2023/3/20 11:25
 * 自定义拒绝策越，并且在决绝策略里可以把数据写入数据库或者redis
 */
public class RejectedTest {

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 100, TimeUnit.SECONDS, new LinkedBlockingDeque<>(200), new RejectedTest.RejectedCustom());

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ThreadService threadService = new ThreadService(i, Thread.currentThread().getName());
            //此时如果用submit，否则下面的拒绝策越里的r不是ThreadService对象，是一个调用了newTaskFor()方法封装后的对象
            //只能用execute
            threadPoolExecutor.execute(threadService);
        }
    }

    public static class RejectedCustom implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            String s = JSON.toJSONString(r);
            System.out.println("我要把:" + s + "写入到数据库,晚上定时调度再执行");
        }
    }
}
