package cn.night.service;

import cn.night.dao.UserDao;
import cn.night.entity.User;
import cn.night.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    // 登录
    public User login(String userName, String password) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("userName", userName)
                .add("userPwd", password)
                .getMap();
        return userDao.find(map);
    }
}
