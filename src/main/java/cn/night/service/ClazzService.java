package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.entity.Clazz;
import cn.night.entity.Teacher;
import cn.night.utils.BeanMapUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;

    public List<Clazz> query(Clazz clazz) {
        if (clazz != null && clazz.getPage() != null) {
            PageHelper.startPage(clazz.getPage(), clazz.getLimit());
        }
        return clazzDao.query(BeanMapUtils.beanToMap(clazz));
    }

    public Integer count(Clazz clazz) {
        return clazzDao.count(BeanMapUtils.beanToMap(clazz));
    }
}
