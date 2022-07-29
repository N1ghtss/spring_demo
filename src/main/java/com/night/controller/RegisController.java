package com.night.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisController {
    @RequestMapping("/regist")
    public String showRegist() {
        return "regist";
    }

    @RequestMapping("/doReg")
    public String doReg(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        System.out.println(username + " " + password + " " + age + " " + phone + " " + email);
        return "index";
    }
}

