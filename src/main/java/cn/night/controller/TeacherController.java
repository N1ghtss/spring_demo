package cn.night.controller;

import cn.night.entity.Teacher;
import cn.night.service.TeacherService;
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
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("list")
    public String list() {
        return "teacher/list";
    }

    @GetMapping("add")
    public String add() {
        return "teacher/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Teacher teacher) {
        List<Teacher> teacherList = teacherService.query(teacher);
        Integer count = teacherService.count(teacher);
        return MapControl.getInstance().success().put("data", teacherList).put("count", count).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(Teacher teacher) {
        int result = teacherService.add(teacher);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
}
