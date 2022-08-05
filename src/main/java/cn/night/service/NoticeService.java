package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.dao.NoticeDao;
import cn.night.entity.Clazz;
import cn.night.entity.Notice;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapParameter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    public List<Notice> query(Notice notice) {
        if (notice != null && notice.getPage() != null) {
            PageHelper.startPage(notice.getPage(), notice.getLimit());
        }
        return noticeDao.query(BeanMapUtils.beanToMap(notice));
    }

    public Integer count(Notice notice) {
        return noticeDao.count(BeanMapUtils.beanToMap(notice));
    }

    public int add(Notice notice) {
        return noticeDao.insert(notice);
    }

    public Notice detail(Integer id) {
        return noticeDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Notice notice) {
        Map<String, Object> map = MapParameter.getInstance().
                add(BeanMapUtils.beanToMapForUpdate(notice)).
                addId(notice.getId()).getMap();
        return noticeDao.update(map);
    }

    public int delete(String ids) {
        int count = 0;
        for (String str : ids.split(",")) {
            count = noticeDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return count;

    }
}
