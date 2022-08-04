package cn.night.dao;

import cn.night.entity.Course;
import cn.night.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionDao {
    Section detail(Map<String, Object> map);

    List<Section> query(Map<String, Object> map);

    int count(Map<String, Object> map);
}