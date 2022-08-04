package cn.night.controller;

import cn.night.entity.Course;
import cn.night.entity.Section;
import cn.night.entity.Teacher;
import cn.night.service.CourseService;
import cn.night.service.SectionService;
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
@RequestMapping("section")
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

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
}
