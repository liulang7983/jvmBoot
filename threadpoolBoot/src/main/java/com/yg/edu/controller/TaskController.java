package com.yg.edu.controller;

import com.yg.edu.bean.User;
import com.yg.edu.task.DemoTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ming.li
 * @date 2023/7/12 9:57
 */
@RestController
@RequestMapping(("task"))
public class TaskController {

    private static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(200));

    @RequestMapping("demo1")
    public Long Demo1() throws ExecutionException, InterruptedException {
        List<User> list=new ArrayList<>();
        list.add(new User("张三",0));
        list.add(new User("李四",2));
        list.add(new User("王五",1));
        list.add(new User("赵六",2));
        list.add(new User("天气",2));
        list.add(new User("王八",0));
        List<FutureTask<String>> futureTasks=new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            DemoTask demoTask = new DemoTask(user.getName(), user.getType(), i);
            FutureTask<String> task = new FutureTask<>(demoTask);
            futureTasks.add(task);
            new Thread(task).start();
        }
        long middle = System.currentTimeMillis();
        long l1 = middle-start;
        System.out.println("中途耗时:"+l1);
        for (int i = 0; i < futureTasks.size(); i++) {
            //此时会等待线程执行完返回值再继续往下走
            String s = futureTasks.get(i).get();
            System.out.println(s);
        }
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println("总耗时："+l);
        return l;
    }

    @RequestMapping("demo2")
    public Long Demo2() throws ExecutionException, InterruptedException {
        List<User> list=new ArrayList<>();
        list.add(new User("张三",0));
        list.add(new User("李四",2));
        list.add(new User("王五",1));
        list.add(new User("赵六",2));
        list.add(new User("天气",2));
        list.add(new User("王八",0));
        list.add(new User("九龙",0));
        List<Future<String>> futureTasks=new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            DemoTask demoTask = new DemoTask(user.getName(), user.getType(), i);
            Future<String> submit = threadPoolExecutor.submit(demoTask);
            futureTasks.add(submit);
        }
        long middle = System.currentTimeMillis();
        long l1 = middle-start;
        System.out.println("中途耗时:"+l1);
        for (int i = 0; i < futureTasks.size(); i++) {
            //此时会等待线程执行完返回值再继续往下走，但是由于任务数大于线程池最大线程数，然后里面又有线程暂停，所以此时会慢一些
            String s = futureTasks.get(i).get();
            System.out.println(s);
        }
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println("总耗时："+l);
        return l;
    }
}
