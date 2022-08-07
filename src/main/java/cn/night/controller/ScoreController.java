package cn.night.controller;

import cn.night.entity.Course;
import cn.night.entity.Score;
import cn.night.entity.Section;
import cn.night.entity.Student;
import cn.night.service.CourseService;
import cn.night.service.ScoreService;
import cn.night.service.SectionService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("score")
@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SectionService sectionService;

    @GetMapping("student_score")
    public String student_score() {
        return "score/student_score";
    }

    @PostMapping("query_student_score")
    @ResponseBody
    public Map<String, Object> query_student_score(HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        List<Course> courseList = courseService.query(null);
        List<Section> sectionList = sectionService.query(null);
        List<Score> scoreList = scoreService.queryByStu(new Score().setStuId(student.getId()));
        scoreList.forEach(score -> {
            score.setStudent(student);
            courseList.forEach(course -> {
                if (score.getCourseId() == course.getId()) {
                    score.setCourse(course);
                }
            });
            sectionList.forEach(section -> {
                if (score.getSectionId() == section.getId()) {
                    score.setSection(section);
                }
            });
        });
        return MapControl.getInstance().success().page(scoreList, null).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(String sectionIds, String courseIds, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        String[] sid = sectionIds.split(",");
        String[] cid = courseIds.split(",");
        //同时遍历课程和课时，将课程和课时的id放入score对象中
        for (int i = 0; i < sid.length; i++) {
            Score score = new Score();
            score.setStuId(student.getId());
            score.setSectionId(Integer.parseInt(sid[i]));
            score.setCourseId(Integer.parseInt(cid[i]));
            if (scoreService.detail(score) == null) {
                scoreService.create(score);
            }
        }
        return MapControl.getInstance().success().getMap();
    }

}
