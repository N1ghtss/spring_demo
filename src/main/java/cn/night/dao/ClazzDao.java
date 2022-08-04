package cn.night.dao;

import cn.night.entity.Clazz;

import java.util.List;
import java.util.Map;

public interface ClazzDao {
    Clazz detail(Map<String, Object> map);

    List<Clazz> query(Map<String, Object> map);

    int count(Map<String, Object> map);

}
