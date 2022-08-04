package cn.night.service;

import cn.night.dao.CourseDao;
import cn.night.entity.Clazz;
import cn.night.entity.Course;
import cn.night.utils.BeanMapUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public List<Course> query(Course course) {
        if (course != null && course.getPage() != null) {
            PageHelper.startPage(course.getPage(), course.getLimit());
        }
        return courseDao.query(BeanMapUtils.beanToMap(course));
    }

    public int count(Course course) {
        return courseDao.count(BeanMapUtils.beanToMap(course));
    }

    public int add(Course course) {
        return courseDao.insert(course);
    }
}
