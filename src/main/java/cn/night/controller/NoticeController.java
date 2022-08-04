package cn.night.controller;

import cn.night.entity.Notice;
import cn.night.entity.Section;
import cn.night.service.NoticeService;
import cn.night.utils.BeanMapUtils;
import cn.night.utils.MapControl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
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
}
