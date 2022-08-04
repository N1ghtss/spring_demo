package cn.night.dao;

import cn.night.entity.Teacher;
import cn.night.entity.User;

import java.util.List;
import java.util.Map;

public interface TeacherDao {
    // 明细查询
    Teacher detail(Map<String, Object> map);

    List<Teacher> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    int insert(Teacher teacher);
}
