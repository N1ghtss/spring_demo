package cn.night.controller;

import cn.night.entity.User;
import cn.night.service.UserService;
import cn.night.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        User user = userService.detail(id);
        modelMap.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = userService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody User user) {
        int result = userService.update(user);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody User user) {
        List<User> users;
        if (Objects.equals(user, null)) {
            users = userService.query(user);
        } else {
            users = userService.queryByName(user);
        }
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
