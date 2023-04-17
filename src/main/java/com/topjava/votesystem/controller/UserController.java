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
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        log.info("getAll()");
        return mav;
    }

    @RequestMapping("/new")
    public String create(Map<String, Object> model) {
        User user = new User();
        log.info("create {}", user);
        model.put("user", user);
        return "userForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user) {
        log.info("save {}", user);
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        log.info("delete {}", id);
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView update(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("userForm");
        User user = userService.get(id);
        log.info("update {} with id={}", user, id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<User> users = userService.search(keyword);
        log.info("search {} with keyword={}", users, keyword);
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
