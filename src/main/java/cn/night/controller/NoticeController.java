package cn.night.controller;

import cn.night.entity.Notice;
import cn.night.entity.User;
import cn.night.service.NoticeService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Notice notice) {
        int result = noticeService.update(notice);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("look/{id}")
    public String look(@PathVariable("id") Integer id, ModelMap modelMap) {
        Notice notice = noticeService.detail(id);
        modelMap.addAttribute("notice", notice);
        return "notice/look";
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = noticeService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, ModelMap modelMap) {
        Notice notice = noticeService.detail(id);
        modelMap.addAttribute("notice", notice);
        return "notice/update";
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
    public Map<String, Object> create(Notice notice, HttpSession session) {
        User user = (User) session.getAttribute("user");
        notice.setAuth(user.getId());
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
