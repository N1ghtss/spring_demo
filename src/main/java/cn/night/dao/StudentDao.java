package cn.night.dao;

import cn.night.entity.Student;
import cn.night.entity.Teacher;
import cn.night.entity.User;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    Student detail(Map<String, Object> map);

    List<Student> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    int insert(Student student);
}
