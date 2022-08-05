package cn.night.service;

import cn.night.dao.UserDao;
import cn.night.entity.User;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
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
        return userDao.detail(map);
    }

    public List<User> query(User user) {
        if (user != null && user.getPage() != null) {
            PageHelper.startPage(user.getPage(), user.getLimit());
        }
        return userDao.query(BeanMapUtils.beanToMap(user));
    }

    public int count(User user) {
        return userDao.count(BeanMapUtils.beanToMap(user));
    }

    public int add(User user) {
        return userDao.insert(user);
    }

    public List<User> queryByName(User user) {
        PageHelper.startPage(user.getPage(), user.getLimit());
        return userDao.queryByName(BeanMapUtils.beanToMap(user));
    }
}
