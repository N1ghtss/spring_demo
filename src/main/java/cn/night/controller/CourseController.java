package cn.night.controller;

import cn.night.entity.Course;
import cn.night.service.CourseService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("list")
    public String list() {
        return "course/list";
    }

    @GetMapping("add")
    public String add() {
        return "course/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Course course) {
        List<Course> courseList = null;
        if (!Objects.equals(course.getCourseName(), null)) {
            courseList = courseService.like(course);
        } else {
            courseList = courseService.query(course);
        }
        Integer count = courseService.count(course);
        return MapControl.getInstance().success().page(courseList, count).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Course course) {
        int result = courseService.add(course);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
}
