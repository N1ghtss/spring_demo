package cn.night.controller;

import cn.night.entity.*;
import cn.night.service.*;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("section")
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("list")
    public String list() {
        return "section/list";
    }

    @GetMapping("student_section")
    public String student_section() {
        return "section/student_section";
    }

    @GetMapping("teacher_section")
    public String teacher_section() {
        return "section/teacher_section";
    }


    @GetMapping("teacher_student_score")
    public String teacher_student_score(Integer courseId, Integer sectionId, ModelMap modelMap) {
        List<HashMap> scoreList = scoreService.
                stuScore(new Score().setSectionid(sectionId).setCourseid(courseId));
        modelMap.addAttribute("list", scoreList);
        modelMap.addAttribute("courseId", courseId);
        modelMap.addAttribute("sectionId", sectionId);
        return "section/teacher_student_score";
    }

    @PostMapping("teacher_student_score")
    @ResponseBody
    public Map<String, Object> teacher_student_score(Integer sectionId, Integer courseId, String stuIds, String scores) {
        String[] stuId = stuIds.split(",");
        String[] score = scores.split(",");
        int result = 0;
//        for (int i = 0; i < stuId.length; i++) {
//            result =
//        }

//        if (result <= 0) {
//            return MapControl.getInstance().error().getMap();
//        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("query_student_section")
    @ResponseBody
    public Map<String, Object> query_student_section(HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        List<Section> sectionList = sectionService.query(new Section().setClazzId(student.getClazzId()));
        List<Clazz> clazzList = clazzService.query(null);
        List<Course> courseList = courseService.query(null);
        List<Teacher> teacherList = teacherService.query(null);
        sectionList.forEach(section -> {
            clazzList.forEach(clazz -> {
                if (section.getClazzId() == clazz.getId()) {
                    section.setClazz(clazz);
                }
            });
            courseList.forEach(course -> {
                if (section.getCourseId() == course.getId()) {
                    section.setCourse(course);
                }
            });
            teacherList.forEach(teacher -> {
                if (section.getTeacherId() == teacher.getId()) {
                    section.setTeacher(teacher);
                }
            });
        });
        return MapControl.getInstance().success().add("data", sectionList).getMap();
    }

    @PostMapping("query_teacher_section")
    @ResponseBody
    public Map<String, Object> query_teacher_section(HttpSession session, Section section) {
        //获取老师信息
        Teacher teacher = (Teacher) session.getAttribute("user");
        section.setTeacherId(teacher.getId());
        List<Section> sectionList = sectionService.query(section);
        List<Clazz> clazzes = clazzService.query(null);
        List<Course> courses = courseService.query(null);
        sectionList.forEach(section1 -> {
            clazzes.forEach(clazz -> {
                if (section1.getClazzId() == clazz.getId()) {
                    section1.setClazz(clazz);
                }

            });
            courses.forEach(course -> {
                if (course.getId() == section1.getCourseId()) {
                    section1.setCourse(course);
                }
            });
        });
        return MapControl.getInstance().success().put("data", sectionList).getMap();
    }


    @GetMapping("add")
    public String add(Integer clazzId, ModelMap modelMap) {
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        modelMap.addAttribute("clazzId", clazzId);
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("courses", courses);
        return "section/add";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, ModelMap modelMap) {
        Section section = sectionService.detail(id);
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        modelMap.addAttribute("section", section);
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("courses", courses);
        return "section/update";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Section section) {
        int result = sectionService.update(section);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = sectionService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();

    }


    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Section section) {
        List<Section> sectionList = sectionService.query(section);
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        sectionList.forEach(section1 -> {
            teachers.forEach(teacher -> {
                if (section1.getTeacherId() == teacher.getId()) {
                    section1.setTeacher(teacher);
                }
                courses.forEach(course -> {
                    if (course.getId() == section1.getCourseId()) {
                        section1.setCourse(course);
                    }
                });
            });
        });
        Integer count = sectionService.count(section);
        return MapControl.getInstance().success().page(sectionList, count).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Section section) {
        int result = sectionService.add(section);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("tree")
    @ResponseBody
    public List<Map> tree() {
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        List<Map> list = new ArrayList<>();
        subjects.forEach(subject -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", subject.getId());
            map.put("name", subject.getSubjectName());
            map.put("parentId", 0);
            List<Map<String, Object>> mapList = new ArrayList<>();
            clazzes.forEach(clazz -> {
                if (subject.getId() == clazz.getSubjectId()) {
                    Map<String, Object> children = new HashMap<>();
                    children.put("id", clazz.getId());
                    children.put("name", clazz.getClazzName());
                    children.put("parentId", subject.getId());
                    mapList.add(children);
                }
            });
            map.put("children", mapList);
            list.add(map);
        });
        return list;
    }
}
