package com.night.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* @Controller作用: 表示当前类属于Controller层
* 同时标识将当前类的对象的创建交给spring容器管理
* */

@Controller
public class HelloController {
    @RequestMapping("/hello") //用于配置当前方法的访问路径，不能省略且不能重复！！
    public String testHello() {
        System.out.println("hello springmvc");
        return "test";
    }
}
