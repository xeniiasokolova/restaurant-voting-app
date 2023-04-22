package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.User;
import com.topjava.votesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.topjava.votesystem.util.ServletUtil;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("user", userService.get(ServletUtil.getId(request)));
        return "userForm";
    }

    @PostMapping("/save")
    public String updateOrCreate(HttpServletRequest request) {
        User user = new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
        if (request.getParameter("id").isEmpty()) {
            userService.create(user);
        } else {
            userService.update(user, Long.parseLong(request.getParameter("id")));
        }
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        userService.delete(ServletUtil.getId(request));
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model) {
        if (ServletUtil.getKeyword(request).equals("")) return "redirect:/users";
        model.addAttribute("users", userService.search(ServletUtil.getKeyword(request)));
        return "users";
    }
}
