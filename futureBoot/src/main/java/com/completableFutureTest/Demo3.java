package com.completableFutureTest;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/8/2 10:18
 * @Version 1.0
 */
public class Demo3 {

    /**
     * applyToEither / acceptEither / runAfterEither 都表示：
     * 将两个CompletableFuture组合起来，只要其中一个执行完了,就会执行某个任务。
     * 区别在于：
     * applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
     * acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
     * runAfterEither：不会把执行结果当做方法入参，且没有返回值
     */
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    //thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
    @Test
    public void test1()throws Exception{
        //第一个异步任务，休眠2秒，保证它执行晚点
        CompletableFuture<String> first = CompletableFuture.supplyAsync(()->{
            try{

                Thread.sleep(2000L);
                System.out.println("执行完第一个异步任务");}
            catch (Exception e){
                return "第一个任务异常";
            }
            return "第一个异步任务";
        });
        CompletableFuture<Void> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> {
                            try{
                                Thread.sleep(1000L);
                              }catch (Exception e){
                            }
                            System.out.println("执行完第二个任务");
                            return "第二个任务";}
                        , threadPoolExecutor)
                //第三个任务
                .acceptEitherAsync(first, (s)->{
                    try {
                        System.out.println(first.get());
                        System.out.println(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }, threadPoolExecutor);
        System.out.println(future.get());
        threadPoolExecutor.shutdown();
    }
}
