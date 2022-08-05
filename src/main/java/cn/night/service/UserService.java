package cn.night.service;

import cn.night.dao.UserDao;
import cn.night.entity.User;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MD5Utils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        return userDao.insert(user);
    }

    public List<User> queryByName(User user) {
        PageHelper.startPage(user.getPage(), user.getLimit());
        return userDao.queryByName(BeanMapUtils.beanToMap(user));
    }

    public User detail(Integer id) {
        return userDao.detail(MapParameter.getInstance().
                addId(id).getMap());
    }

    public int update(User user) {
        // 判断密码是否改变
        String old = userDao.detail(MapParameter.getInstance().
                addId(user.getId()).getMap()).getUserPwd();
        if (!user.getUserPwd().equals(old)) {
            user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        }
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(user)).
                addId(user.getId()).getMap();
        return userDao.update(map);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = userDao.delete(MapParameter.getInstance().addId(str).getMap());
        }
        return count;
    }
}
