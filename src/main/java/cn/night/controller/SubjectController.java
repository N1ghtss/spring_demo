package cn.night.controller;

import cn.night.entity.Subject;
import cn.night.service.SubjectService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("list")
    public String list() {
        return "subject/list";
    }

    @GetMapping("add")
    public String add() {
        return "subject/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Subject subject) {
        List<Subject> subjectList = subjectService.query(subject);
        Integer count = subjectService.count(subject);
//        return MapControl.getInstance().success().put("data", clazzList).put("count", count).getMap();
        return MapControl.getInstance().success().page(subjectList, count).getMap();

    }

}
