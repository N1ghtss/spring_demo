package cn.night.controller;

import cn.night.entity.*;
import cn.night.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class IndexController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SectionService sectionService;

    // 跳转系统主页
    @GetMapping("/index")
    public String login() {
        return "index";
    }

    // 跳转用户基本信息页面
    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/pwd")
    public String pwd() {
        return "pwd";
    }

    // 跳转系统主页（数据展示/概览）

    @GetMapping("/main")
    public String main(ModelMap modelMap) {
        // 1.数据概览
        List<Teacher> teachers = teacherService.query(null);
        List<Student> students = studentService.query(null);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        List<Course> courses = courseService.query(null);
        List<Section> sections = sectionService.query(null);
        modelMap.addAttribute("sectionCnt", sections.size());
        modelMap.addAttribute("courseCnt", courses.size());
        modelMap.addAttribute("clazzCnt", clazzes.size());
        modelMap.addAttribute("teacherCnt", teachers.size());
        modelMap.addAttribute("studentCnt", students.size());
        modelMap.addAttribute("subjectCnt", subjects.size());
        return "main";
    }
}
