package cn.night.dao;

import cn.night.entity.Teacher;

import java.util.Map;

public interface TeacherDao {
    // 明细查询
    Teacher detail(Map<String, Object> map);
}
