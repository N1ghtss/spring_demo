package cn.night.service;

import cn.night.dao.CourseDao;
import cn.night.entity.Clazz;
import cn.night.entity.Course;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<Course> like(Course course) {
        return courseDao.like(BeanMapUtils.beanToMap(course));
    }

    public Course detail(Integer id) {
        return courseDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Course course) {
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(course)).
                addId(course.getId()).getMap();
        return courseDao.update(map);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = courseDao.delete(MapParameter.getInstance().
                    addId(Integer.parseInt(str)).getMap());
        }
        return count;
    }
}
