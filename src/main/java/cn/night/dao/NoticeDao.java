package cn.night.dao;


import cn.night.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
    Notice detail(Map<String, Object> map);

    List<Notice> query(Map<String, Object> map);

    int count(Map<String, Object> map);

    int insert(Notice notice);
}
