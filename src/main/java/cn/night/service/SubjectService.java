package cn.night.service;

import cn.night.dao.SubjectDao;
import cn.night.entity.Subject;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    public List<Subject> query(Subject subject) {
        if (subject != null && subject.getPage() != null) {
            PageHelper.startPage(subject.getPage(), subject.getLimit());
        }
        return subjectDao.query(BeanMapUtils.beanToMap(subject));
    }

    public int count(Subject subject) {
        return subjectDao.count(BeanMapUtils.beanToMap(subject));
    }

    public int add(Subject subject) {
        return subjectDao.insert(subject);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = subjectDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
    }

    public List<Subject> like(Subject subject) {
        PageHelper.startPage(subject.getPage(), subject.getLimit());
        return subjectDao.like(BeanMapUtils.beanToMap(subject));
    }

    public Subject detail(Integer id) {
        return subjectDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Subject subject) {
        Map<String, Object> map = MapParameter.getInstance()
                .add(BeanMapUtils.beanToMapForUpdate(subject))
                .addId(subject.getId()).getMap();
        return subjectDao.update(map);
    }
}
