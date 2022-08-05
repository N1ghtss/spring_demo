package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.dao.SectionDao;
import cn.night.entity.Clazz;
import cn.night.entity.Section;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SectionService {
    @Autowired
    private SectionDao sectionDao;

    public List<Section> query(Section section) {
        if (section != null && section.getPage() != null) {
            PageHelper.startPage(section.getPage(), section.getLimit());
        }
        return sectionDao.query(BeanMapUtils.beanToMap(section));
    }

    public int count(Section section) {
        return sectionDao.count(BeanMapUtils.beanToMap(section));
    }

    public int add(Section section) {
        return sectionDao.insert(section);
    }

    public List<Section> queryById(Section section) {
        PageHelper.startPage(section.getPage(), section.getLimit());
        return sectionDao.query(BeanMapUtils.beanToMap(section));

    }

    public Section detail(Integer id) {
        Map<String, Object> map = MapParameter.getInstance().
                addId(id).getMap();
        return sectionDao.detail(map);
    }

    public int update(Section section) {
        return sectionDao.update(MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(section)).
                addId(section.getId()).getMap());
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = sectionDao.delete(MapParameter.getInstance().addId(str).getMap());
        }
        return count;
    }
}
