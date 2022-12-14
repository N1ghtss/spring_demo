package cn.night.controller;

import cn.night.entity.Clazz;
import cn.night.entity.Subject;
import cn.night.service.ClazzService;
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
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("list")
    public String list() {
        return "clazz/list";
    }

    @GetMapping("add")
    public String add(ModelMap modelMap) {
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects", subjects);
        return "clazz/add";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Clazz clazz = clazzService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects", subjects);
        modelMap.addAttribute("clazz", clazz);
        return "clazz/update";

    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Clazz clazz) {
        int result = clazzService.update(clazz);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = clazzService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Clazz clazz) {
        List<Clazz> clazzList;
        if (!Objects.equals(clazz, null)) {
            clazzList = clazzService.like(clazz);
        } else {
            //todo optimize query

            clazzList = clazzService.query(clazz);
        }
        List<Subject> subjects = subjectService.query(null);
        clazzList.forEach(clazz1 -> {
            subjects.forEach(subject -> {
                if (clazz1.getSubjectId() == subject.getId()) {
                    clazz1.setSubject(subject);
                }
            });
        });
        Integer count = clazzService.count(clazz);
//        return MapControl.getInstance().success().put("data", clazzList).put("count", count).getMap();
        return MapControl.getInstance().success().page(clazzList, count).getMap();
    }


    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Clazz clazz) {
        int result = clazzService.add(clazz);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
}
