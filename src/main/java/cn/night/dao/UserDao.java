package cn.night.dao;

import cn.night.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    // 明细查询（只返回一条数据）
    User detail(Map<String, Object> map);

    // 查询所有
    List<User> query(Map<String, Object> map);

    int count(Map<String, Object> map);
}
