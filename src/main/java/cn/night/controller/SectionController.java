package cn.night.controller;

import cn.night.entity.*;
import cn.night.service.*;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("teacher_section")
    public String teacher_section() {
        return "section/teacher_section";
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
