package cn.night.service;

import cn.night.dao.ScoreDao;
import cn.night.entity.Score;
import cn.night.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    public List<Score> query(Score score) {
        return scoreDao.query(BeanMapUtils.beanToMap(score));
    }

    public List<Score> join(Score score) {
        return scoreDao.join(BeanMapUtils.beanToMap(score));
    }
}
