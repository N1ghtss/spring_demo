package cn.night.service;

import cn.night.dao.ClazzDao;
import cn.night.dao.NoticeDao;
import cn.night.entity.Clazz;
import cn.night.entity.Notice;
import cn.night.utils.BeanMapUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
