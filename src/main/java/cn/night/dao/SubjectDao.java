package cn.night.dao;

import cn.night.entity.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectDao {
    Subject detail(Map<String, Object> map);

    List<Subject> query(Map<String, Object> map);
}
