package com.night.controller;

import com.night.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisController {
    @RequestMapping("/regist")
    public String showRegist() {
        return "regist";
    }

    //    @RequestMapping("/doReg")
//    public String doReg(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String age = request.getParameter("age");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        System.out.println(username + " " + password + " " + age + " " + phone + " " + email);
//        return "index";
//    }
//
//    @RequestMapping("doReg")
//    public String doReg(String username, String password, Integer age, Integer phone, String email) {
//        System.out.println(username + " " + password + " " + age + " " + phone + " " + email);
//        return "index";
//    }
    @RequestMapping("/doReg")
    public String doReg(User user) {
        System.out.println(user);
        return "index";
    }
}

