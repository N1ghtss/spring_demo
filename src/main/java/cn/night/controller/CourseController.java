package cn.night.controller;

import cn.night.entity.Course;
import cn.night.service.CourseService;
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
    public Map<String, Object> query(Course course) {
        List<Course> courseList = courseService.query(course);
        Integer count = courseService.count(course);
        return MapControl.getInstance().success().page(courseList, count).getMap();
    }
}
