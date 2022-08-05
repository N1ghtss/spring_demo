package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.dao.NoticeDao;
import cn.night.entity.Clazz;
import cn.night.entity.Notice;
import cn.night.entity.Teacher;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public int add(Clazz clazz) {
        return clazzDao.insert(clazz);
    }

    public List<Clazz> like(Clazz clazz) {
        if (!Objects.equals(clazz.getClazzName(), "")) {
            PageHelper.startPage(clazz.getPage(), clazz.getLimit());
        }
        return clazzDao.like(BeanMapUtils.beanToMap(clazz));
    }

    public Clazz detail(Integer id) {
        return clazzDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Clazz clazz) {
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(clazz)).
                addId(clazz.getId()).getMap();
        return clazzDao.update(map);

    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = clazzDao.delete(MapParameter.getInstance().
                    addId(Integer.parseInt(str)).getMap());
        }
        return count;
    }
}
