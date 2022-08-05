package cn.night.controller;

import cn.night.entity.*;
import cn.night.service.*;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("list")
    public String list() {
        return "section/list";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Section section) {
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
