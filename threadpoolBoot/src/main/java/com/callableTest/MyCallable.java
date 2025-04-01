package com.callableTest;

import java.util.concurrent.Callable;

/**
 * @author ming.li
 * @date 2023/11/9 14:27
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "我是MyCallable";
    }
}
