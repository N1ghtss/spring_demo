package cn.night.controller;

import cn.night.entity.Student;
import cn.night.entity.Teacher;
import cn.night.entity.User;
import cn.night.service.StudentService;
import cn.night.service.TeacherService;
import cn.night.service.UserService;
import cn.night.utils.MD5Utils;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String userName, String password, String captcha, String type, HttpSession session) {

        // 判断用户名，密码，用户类型，验证码是否为空
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(type) || StringUtils.isEmpty(captcha)) {
            return MapControl.getInstance().error("用户名或密码不能为空").getMap();
        }
        // 获取系统生成的验证码
        String captcha1 = (String) session.getAttribute("captcha");
        // 判断验证码是否正确
        if (!(captcha.toLowerCase().equals(captcha1.toLowerCase()))) {
            // 验证码错误
            return MapControl.getInstance().error("验证码错误").getMap();
        }
        // 判断用户类型
        if ("1".equals(type)) {
            User user = userService.login(userName, MD5Utils.getMD5(password));// 对密码进行加密处理，因为数据库中存储的是加密后的
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("type", 1);
                return MapControl.getInstance().success().add("data", user).getMap();
            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        if ("2".equals(type)) {
            Teacher user = teacherService.login(userName, MD5Utils.getMD5(password));
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("type", 2);
                return MapControl.getInstance().success().add("data", user).getMap();
            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        if ("3".equals(type)) {
            Student user = studentService.login(userName, MD5Utils.getMD5(password));
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("type", 3);
                return MapControl.getInstance().success().add("data", user).getMap();
            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }


        return MapControl.getInstance().getMap();

    }
}
