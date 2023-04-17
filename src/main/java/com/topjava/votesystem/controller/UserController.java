package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.User;
import com.topjava.votesystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView home() {
        List<User> users = userService.getAll();
        ModelAndView mav = new ModelAndView("index_user");
        mav.addObject("users", users);
        log.info("getAll()");
        return mav;
    }

    @RequestMapping("/new")
    public String newUser(Map<String, Object> model) {
        User user = new User();
        log.info("create {}", user);
        model.put("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        log.info("save {}", user);
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        log.info("delete {}", id);
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.get(id);
        log.info("update {} with id={}", user, id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<User> result = userService.search(keyword);
        log.info("search {} with keyword={}", result, keyword);
        ModelAndView mav = new ModelAndView("search_user");
        mav.addObject("result", result);
        return mav;
    }
}
