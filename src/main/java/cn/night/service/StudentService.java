package cn.night.service;

import cn.night.dao.StudentDao;
import cn.night.entity.Student;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MD5Utils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Student> query(Student student) {
        if (student != null && student.getPage() != null) {
            PageHelper.startPage(student.getPage(), student.getLimit());
        }
        return studentDao.query(BeanMapUtils.beanToMap(student));
    }

    public int count(Student student) {
        return studentDao.count(BeanMapUtils.beanToMap(student));
    }

    public int add(Student student) {
        student.setStuPwd(MD5Utils.getMD5(student.getStuPwd()));
        return studentDao.insert(student);
    }

    public List<Student> like(Student student) {
        PageHelper.startPage(student.getPage(), student.getLimit());
        return studentDao.like(BeanMapUtils.beanToMap(student));
    }

    public Student detail(Integer id) {
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Student student) {
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(student)).
                addId(student.getId()).getMap();
        return studentDao.update(map);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = studentDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return count;
    }
}
