package cn.night.dao;


import cn.night.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreDao {
    Score detail(Map<String, Object> map);

    List<Score> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    void insert(Score score);

    int update(Map<String, Object> map);

    int delete(Map<String, Object> map);

    List<Score> join(Map<String, Object> map);

    List<Score> queryByStu(Map<String, Object> beanToMap);
}
