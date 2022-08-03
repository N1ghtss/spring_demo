package cn.night.service;

import cn.night.dao.StudentDao;
import cn.night.entity.Student;
import cn.night.entity.Teacher;
import cn.night.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public Student login(String userName, String password) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuName", userName)
                .add("stuPwd", password)
                .getMap();
        return studentDao.detail(map);
    }
}
