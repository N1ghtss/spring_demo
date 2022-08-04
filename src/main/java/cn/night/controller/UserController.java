package cn.night.controller;

import cn.night.entity.Student;
import cn.night.entity.User;
import cn.night.service.UserService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String list() {
        return "user/list";
    }

    @GetMapping("add")
    public String add() {
        return "user/add";
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(User user) {
        List<User> users = userService.query(user);
        Integer count = userService.count(user);
        return MapControl.getInstance().success().page(users, count).getMap();
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody User user) {
        int result = userService.add(user);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
}
