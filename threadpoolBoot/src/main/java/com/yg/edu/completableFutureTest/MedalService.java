package com.yg.edu.completableFutureTest;

/**
 * @author ming.li
 * @date 2023/11/27 19:48
 */
public class MedalService {
    public MedalInfo getMedalInfo(long userId) throws InterruptedException {
        Thread.sleep(500); //模拟调用耗时
        return new MedalInfo("666", "守护勋章");
    }
}
