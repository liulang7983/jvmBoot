package cn.tulingxueyuan.dao.impl;

import cn.tulingxueyuan.dao.UserDao;
import org.springframework.stereotype.Repository;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Repository
public class UserDaoImpl implements UserDao {

    public void getUser() {
        System.out.println("查询数据库");
    }
}
