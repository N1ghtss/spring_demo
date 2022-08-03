package cn.night.service;

import cn.night.dao.TeacherDao;
import cn.night.dao.UserDao;
import cn.night.entity.Teacher;
import cn.night.entity.User;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Teacher> query(Teacher teacher) {
        if (teacher != null && teacher.getPage() != null) {
            PageHelper.startPage(teacher.getPage(), teacher.getLimit());
        }
        return teacherDao.query(BeanMapUtils.beanToMap(teacher));
    }
}
