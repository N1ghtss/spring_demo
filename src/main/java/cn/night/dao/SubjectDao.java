package cn.night.dao;

import cn.night.entity.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectDao {
    Subject detail(Map<String, Object> map);

    List<Subject> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    int insert(Subject subject);

    int delete(Map<String, Object> map);

    List<Subject> like(Map<String, Object> map);

    int update(Map<String, Object> map);
}
