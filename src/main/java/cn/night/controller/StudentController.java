package cn.night.controller;

import cn.night.entity.Clazz;
import cn.night.entity.Student;
import cn.night.entity.Subject;
import cn.night.entity.Teacher;
import cn.night.service.ClazzService;
import cn.night.service.StudentService;
import cn.night.service.SubjectService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    @GetMapping("list")
    public String list() {
        return "student/list";
    }

    // 老师-查询学生
    @GetMapping("teacher_student")
    public String teacher_student(ModelMap modelMap, HttpSession session) {
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        Teacher teacher = (Teacher) session.getAttribute("user");
        modelMap.addAttribute("teacher", teacher);
        modelMap.addAttribute("clazzes", clazzes);
        modelMap.addAttribute("subjects", subjects);
        return "student/teacher_student";
    }

    @PostMapping("teacher_student")
    @ResponseBody
    public Map<String, Object> tea_stu(Integer clazzId, Integer subjectId, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Student> students = studentService.queryStudentByTeacher(teacher.getId(), clazzId, subjectId);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        AtomicInteger count = new AtomicInteger();
        students.forEach(entity -> {
            subjects.forEach(subject -> {
                // 判断学生中的subjectId和专业表的id是否一致
                if (entity.getSubjectId() == subject.getId()) {
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                // 判断学生表中的clazzId和班级表的id是否一致
                if (entity.getClazzId() == clazz.getId()) {
                    entity.setClazz(clazz);
                }
            });
            count.getAndIncrement();
        });
        return MapControl.getInstance().success().add("data", students).add("count", count).getMap();

    }

    @GetMapping("add")
    public String add(ModelMap modelMap) {
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects", subjects);
        return "student/add";
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = studentService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        // 查询出要修改的学生信息
        Student student = studentService.detail(id);
        // 查询所有的专业
        List<Subject> subjects = subjectService.query(null);
//        List<Clazz> clazzes = clazzService.query(null);
        // 将查询出来的数据储存到request域，实现表单回写
        modelMap.addAttribute("student", student);
        modelMap.addAttribute("subjects", subjects);
//        modelMap.addAttribute("clazzes", clazzes);
        return "student/update";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Student student) {
        int result = studentService.update(student);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Student student) {
        // 设置学生的状态
        student.setStatus(Student.StatusType.type_1);
        int result = studentService.add(student);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    //查询所有
    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Student student) {
        List<Student> list;
        if (!Objects.equals(student, "")) {
            list = studentService.like(student);
        } else {
            // 查询所有学生信息
            list = studentService.query(student);
        }
        // 查询所有专业
        List<Subject> subjects = subjectService.query(null);
        // 查询所有班级
        List<Clazz> clazzes = clazzService.query(null);

        // 设置关联
        list.forEach(entity -> {
            subjects.forEach(subject -> {
                // 判断学生中的subjectId和专业表的id是否一致
                if (entity.getSubjectId() == subject.getId()) {
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                // 判断学生表中的clazzId和班级表的id是否一致
                if (entity.getClazzId() == clazz.getId()) {
                    entity.setClazz(clazz);
                }
            });
        });
        //查询总记录条数
        Integer count = studentService.count(student);
        return MapControl.getInstance().success().page(list, count).getMap();
    }


}

