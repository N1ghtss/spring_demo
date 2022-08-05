package cn.night.controller;

import cn.night.entity.Notice;
import cn.night.entity.Section;
import cn.night.service.NoticeService;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapControl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("list")
    public String list() {
        return "notice/list";
    }

    @GetMapping("add")
    public String add() {
        return "notice/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Notice notice) {
        List<Notice> noticeList = noticeService.query(notice);
        Integer count = noticeService.count(notice);
        return MapControl.getInstance().success().page(noticeList, count).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(Notice notice) {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        notice.setDate(date);
        int result = noticeService.add(notice);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
}
