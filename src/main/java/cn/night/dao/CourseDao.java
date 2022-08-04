package cn.night.dao;

import cn.night.entity.Clazz;
import cn.night.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    Course detail(Map<String, Object> map);

    List<Course> query(Map<String, Object> map);

    int count(Map<String, Object> map);
}
