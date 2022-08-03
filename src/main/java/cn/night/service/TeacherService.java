package cn.night.service;

import cn.night.dao.TeacherDao;
import cn.night.dao.UserDao;
import cn.night.entity.Teacher;
import cn.night.entity.User;
import cn.night.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    // 登录
    public Teacher login(String userName, String password) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherName", userName)
                .add("teacherPwd", password)
                .getMap();
        return teacherDao.detail(map);
    }
}
