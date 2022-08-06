package cn.night.controller;

import cn.night.entity.Subject;
import cn.night.service.SubjectService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Subject subject = subjectService.detail(id);
        modelMap.addAttribute("subject", subject);
        return "subject/update";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(Subject subject) {
        int result = subjectService.update(subject);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("query")
    @ResponseBody

    public Map<String, Object> query(@RequestBody Subject subject) {
        List<Subject> subjectList;
        if (Objects.equals(subject, null)) {
            subjectList = subjectService.query(subject);
//        return MapControl.getInstance().success().put("data", clazzList).put("count", count).getMap();
        } else {
            subjectList = subjectService.like(subject);
        }
        Integer count = subjectService.count(subject);
        return MapControl.getInstance().success().page(subjectList, count).getMap();
    }


    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(Subject subject) {
        int result = subjectService.add(subject);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = subjectService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


}
