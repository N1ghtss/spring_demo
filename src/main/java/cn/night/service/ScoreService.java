package cn.night.service;

import cn.night.dao.ScoreDao;
import cn.night.entity.Score;
import cn.night.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public List<HashMap> stuScore(Score score) {
        return scoreDao.stuScore(BeanMapUtils.beanToMap(score));
    }

    public List<Score> queryByStu(Score score) {
        return scoreDao.queryByStu(BeanMapUtils.beanToMap(score));
    }

    public Score detail(Score score) {
        return scoreDao.detail(BeanMapUtils.beanToMap(score));
    }

    public void create(Score score) {
        scoreDao.insert(score);
    }

    public List<HashMap> queryAvgScoreBySection() {
        return scoreDao.queryAvgScoreBySection(null);
    }
}
