package com.futureTest;

import com.bean.Model;
import com.bean.User;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/8/2 9:12
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        Model model = new Model().getModel();
        User user = new User().getUser();
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        Future<Model> modelFuture = threadPoolExecutor.submit(() -> new Model().getModel());
        Future<User> userFuture = threadPoolExecutor.submit(() -> new User().getUser());
        try {
            Model model = modelFuture.get();
            User user = userFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
}
