package com.completableFutureTest;

import com.bean.Model;
import com.bean.User;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/8/2 9:40
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        CompletableFuture<Model> modelCompletableFuture = CompletableFuture.supplyAsync(() -> new Model().getModel());
        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> new User().getUser());
        try {
            Model model = modelCompletableFuture.get();
            User user = userCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        //runAsync的使用
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> System.out.println("run,关注公众号:捡田螺的小男孩"), threadPoolExecutor);
        //supplyAsync的使用
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supply,关注公众号:捡田螺的小男孩");
            return "捡田螺的小男孩"; }, threadPoolExecutor);
        //runAsync的future没有返回值，输出null
        System.out.println(runFuture.join());
        //supplyAsync的future，有返回值
        System.out.println(supplyFuture.join());
        threadPoolExecutor.shutdown(); // 线程池需要关闭
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }


    //CompletableFuture的thenRun/thenRunAsync方法，通俗点讲就是，做完第一个任务后，再做第二个任务。
    // 某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
    @Test
    public void test3()throws Exception{
        long start = System.currentTimeMillis();
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );
        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    //CompletableFuture的thenAccept/thenAcceptAsync方法表示，第一个任务执行完成后，
    // 执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的
    @Test
    public void test4()throws Exception{
        long start = System.currentTimeMillis();
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );
        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("关注了");
            }
            System.out.println("先考虑考虑");
        });

        System.out.println(thenAcceptFuture.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    //CompletableFuture的 thenApply/thenApplyAsync方法表示，第一个任务执行完成后，执行第二个回调方法任务，
    // 会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的
    @Test
    public void test5()throws Exception{
        long start = System.currentTimeMillis();
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );
        CompletableFuture<String> thenApplyFuture = orgFuture.thenApply((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                return "关注了";
            }
            return "先考虑考虑";
        });

        System.out.println(thenApplyFuture.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    //CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法
    @Test
    public void test6()throws Exception{
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    throw new RuntimeException();
                }
        );
        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            e.printStackTrace();
            return "你的程序异常啦";
        });
        System.out.println(exceptionFuture.get());
    }


    //CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；
    // 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果
    @Test
    public void test7()throws Exception{
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把 " + a + " 传过来");
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("666");
            }
            System.out.println("233333");
        });

        System.out.println(rstFuture.get());
    }

    //CompletableFuture的handle方法表示，某个任务执行完成后，执行回调方法，并且是有返回值的;
    // 并且handle方法返回的CompletableFuture的result是回调方法执行的结果
    @Test
    public void test8()throws Exception{
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "捡田螺的小男孩";
                }
        );
        CompletableFuture<String> rstFuture = orgFuture.handle((a, throwable) -> {
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("666");
                return "关注了";
            }
            System.out.println("233333");
            return null;
        });
        System.out.println(rstFuture.get());
    }
}
