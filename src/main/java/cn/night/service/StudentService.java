package cn.night.service;

import cn.night.dao.StudentDao;
import cn.night.entity.Student;
import cn.night.utils.BeanMapUtils;
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
}
