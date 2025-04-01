package com.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/11/27 19:53
 */
public class FutureTest1 {
    public static void main(String[] args) throws Exception{

        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId =666L;
        long startTime = System.currentTimeMillis();

        //调用用户服务获取用户基本信息
        CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return userInfoService.getUserInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        Thread.sleep(300); //模拟主线程其它操作耗时

        CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return medalService.getMedalInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        UserInfo userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);//获取个人信息结果
        MedalInfo medalInfo = completableMedalInfoFuture.get();//获取勋章信息结果
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
