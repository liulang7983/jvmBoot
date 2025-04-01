package com.callableTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ming.li
 * @date 2023/11/9 14:26
 */
public class Demo1 {
    public static void main(String[] args) {
        try {
            FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
            new Thread(futureTask).start();
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
