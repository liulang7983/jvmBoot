package com.service.impl;

import com.bean.ApiResult;
import com.bean.User;
import com.model.json.HexOCRResult;
import com.service.JsonService;
import com.service.UserService;
import com.util.FileUtil;
import com.util.JsonUtil;
import org.springframework.stereotype.Service;

/**
 * @author ming.li
 * @date 2023/6/2 15:27
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ApiResult getUser() {
        User user=new User();
        saveUser(user);
        return new ApiResult(user);
    }

    public void saveUser(User user){
        user.setName("张三");
    }

}
