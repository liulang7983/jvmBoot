package cn.tulingxueyuan.service.impl;

import cn.tulingxueyuan.beans.Role;
import cn.tulingxueyuan.dao.UserDao;
import cn.tulingxueyuan.service.BaseService;
import cn.tulingxueyuan.service.RoleService;
import cn.tulingxueyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class RoleServiceImpl  implements RoleService {
    @Autowired
    UserDao userDao;

    public void getBean() {
        System.out.println("RoleServiceImpl");
        userDao.getUser();
    }
}
