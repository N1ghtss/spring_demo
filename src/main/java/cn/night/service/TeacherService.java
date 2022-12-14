package cn.night.service;

import cn.night.dao.TeacherDao;
import cn.night.dao.UserDao;
import cn.night.entity.Teacher;
import cn.night.entity.User;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MD5Utils;
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

    public int count(Teacher teacher) {
        return teacherDao.count(BeanMapUtils.beanToMap(teacher));
    }

    public int add(Teacher teacher) {
        teacher.setTeacherPwd(MD5Utils.getMD5(teacher.getTeacherPwd()));
        return teacherDao.insert(teacher);
    }

    public List<Teacher> like(Teacher teacher) {
        PageHelper.startPage(teacher.getPage(), teacher.getLimit());
        return teacherDao.like(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher detail(Integer id) {
        return teacherDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Teacher teacher) {
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(teacher)).
                addId(teacher.getId()).getMap();
        return teacherDao.update(map);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = teacherDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return count;
    }
}
