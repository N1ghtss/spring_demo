package cn.night.controller;

import cn.night.entity.Teacher;
import cn.night.service.TeacherService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher", teacher);
        return "teacher/update";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Teacher teacher) {
        int result = teacherService.update(teacher);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = teacherService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Teacher teacher) {
        List<Teacher> teacherList;
        if (!Objects.equals(teacher.getName(), "")) {
            teacherList = teacherService.like(teacher);
        } else {
            teacherList = teacherService.query(teacher);
        }
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
