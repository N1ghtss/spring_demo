package cn.night.controller;

import cn.night.entity.*;
import cn.night.service.*;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;


    // 跳转系统主页
    @GetMapping("index")
    public String login() {
        return "index";
    }

    // 跳转用户基本信息页面
    @GetMapping("info")
    public String info() {
        return "info";
    }

    @GetMapping("pwd")
    public String pwd() {
        return "pwd";
    }

    @PostMapping("pwd")
    @ResponseBody
    public Map<String, Object> pwdUpdate(String sourcePwd, String newPwd, String type, Integer id, HttpSession session) {
        switch (type) {
            case "1":
                User user = (User) session.getAttribute("user");
                String old = userService.detail(id).getUserPwd();
                if (old != sourcePwd) {
                    return MapControl.getInstance().error("原密码错误！").getMap();
                }
                

                break;
            case "2":
                Teacher teacher = (Teacher) session.getAttribute("user");
                System.out.println(teacher);
                break;
            case "3":
                Student student = (Student) session.getAttribute("user");
                System.out.println(student);
                break;
        }
        return MapControl.getInstance().success().getMap();
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
        // 2.班级学生数量
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Clazz clazz : clazzes) {
            Map<String, Object> map = new HashMap<>();
            // 设置班级名称
            map.put("name", clazz.getClazzName());
            // 统计学生数量
            int count = 0;
            for (Student student : students) {
                if (student.getClazzId() == clazz.getId()) {
                    count++;
                }
            }
            // 设置学生数量
            map.put("cnt", count);
            mapList.add(map);

        }
        modelMap.addAttribute("mapList", mapList);

        // 3.课程平均成绩
//        List<Map<String, Object>> map2 = new ArrayList<>();
//        List<Section> sectionList = sectionService.query(null);
//        List<Course> courseList = courseService.query(null);
//        List<Clazz> clazzList = clazzService.query(null);
//        List<Score> scoreList = scoreService.query(null);
//        sectionList.forEach(section -> {
//            Map<String, Object> map = new HashMap<>();
//            // 设置平均成绩
//            double sum = 0;
//            int cnt = 0;
//            for (Score score : scoreList) {
//                if (score.getScore() == null) {
//                    continue;
//                }
//                if (section.getId() == score.getSectionId()) {
//                    sum += score.getScore().doubleValue();
//                    cnt++;
//                }
//            }
//            if (cnt == 0) {
//                return;
//            }
//            map.put("avgScore", sum / cnt);
//            // 设置班级名称
//            clazzList.forEach(clazz -> {
//                if (section.getClazzId() == clazz.getId()) {
//                    map.put("clazzName", clazz.getClazzName());
//                }
//            });
//            // 设置课程名称
//            courseList.forEach(course -> {
//                if (section.getCourseId() == course.getId()) {
//                    map.put("courseName", course.getCourseName());
//                }
//            });
//
//            map2.add(map);
//        });

        List<HashMap> map2 = scoreService.queryAvgScoreBySection();


        modelMap.addAttribute("mapList2", map2);
        return "main";
    }
}
