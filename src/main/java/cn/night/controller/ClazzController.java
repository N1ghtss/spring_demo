package cn.night.controller;

import cn.night.entity.Clazz;
import cn.night.entity.Subject;
import cn.night.service.ClazzService;
import cn.night.service.SubjectService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
        modelMap.addAttribute("subject", subjects);
        return "clazz/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Clazz clazz) {
        List<Clazz> clazzList = clazzService.query(clazz);
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
}
