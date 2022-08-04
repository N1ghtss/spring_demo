package cn.night.controller;

import cn.night.entity.Clazz;
import cn.night.entity.Student;
import cn.night.entity.Subject;
import cn.night.service.ClazzService;
import cn.night.service.StudentService;
import cn.night.service.SubjectService;
import cn.night.utils.Entity;
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

    //查询所有
    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(Student student) {
        // 查询所有学生信息
        List<Student> list = studentService.query(student);
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
                    System.out.println(clazz);
                }
            });
        });
        //查询总记录条数
        Integer count = studentService.count(student);
        return MapControl.getInstance().success().page(list, count).getMap();
    }

}
