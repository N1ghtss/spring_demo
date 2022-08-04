package cn.night.controller;

import cn.night.entity.Subject;
import cn.night.service.SubjectService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Object> query(@RequestBody Subject subject) {
        if (subject.getSubjectName() == "" && subject.getCollege() == "") {
            List<Subject> subjectList = subjectService.query(subject);
            Integer count = subjectService.count(subject);
//        return MapControl.getInstance().success().put("data", clazzList).put("count", count).getMap();
            return MapControl.getInstance().success().page(subjectList, count).getMap();
        }
        List<Subject> subjectList = subjectService.like(subject);
        Integer count = subjectService.count(subject);
        return MapControl.getInstance().success().put("data", subjectList).put("count", count).getMap();
    }


    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Subject subject) {
        int result = subjectService.add(subject);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    //    @PostMapping("dalete")
//    @ResponseBody
//    public Map<String, Object> delete(@RequestBody Subject subject) {
//        int result = subjectService.delete(subject);
//        if (result <= 0) {
//            return MapControl.getInstance().error().getMap();
//        }
//        return MapControl.getInstance().success().getMap();
//    }
    //删除数据
    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Subject subject) {
        int result = subjectService.delete(subject);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


}
