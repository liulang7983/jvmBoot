package com.completableFutureTest;

/**
 * @author ming.li
 * @date 2023/11/27 19:47
 */
public class UserInfoService {
    public UserInfo getUserInfo(Long userId) throws InterruptedException {
        Thread.sleep(300);//模拟调用耗时
        return new UserInfo("666", "捡田螺的小男孩", 27); //一般是查数据库，或者远程调用返回的
    }
}
