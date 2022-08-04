package cn.night.service;

import cn.night.dao.SubjectDao;
import cn.night.entity.Subject;
import cn.night.utils.BeanMapUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
