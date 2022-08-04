package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.dao.SectionDao;
import cn.night.entity.Clazz;
import cn.night.entity.Section;
import cn.night.utils.BeanMapUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
