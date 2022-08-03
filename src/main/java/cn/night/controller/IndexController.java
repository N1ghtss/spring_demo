package cn.night.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

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
}
