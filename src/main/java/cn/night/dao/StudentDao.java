package cn.night.dao;

import cn.night.entity.Student;
import cn.night.entity.Teacher;

import java.util.Map;

public interface StudentDao {
    Student detail(Map<String, Object> map);
}
