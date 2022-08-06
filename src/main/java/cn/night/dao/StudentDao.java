package cn.night.dao;

import cn.night.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    Student detail(Map<String, Object> map);

    List<Student> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    int insert(Student student);

    List<Student> like(Map<String, Object> beanToMap);

    int update(Map<String, Object> map);

    int delete(Map<String, Object> map);
}
