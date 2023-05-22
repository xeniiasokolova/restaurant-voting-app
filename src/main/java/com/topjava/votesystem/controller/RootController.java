package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.User;
import com.topjava.votesystem.service.RestaurantService;
import com.topjava.votesystem.service.UserService;
import com.topjava.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/login")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        SecurityUtil.setAuthUserRole(userService.get(userId).getRoles());
        userService.checkForResetUserVote(userService.get(userId));
        restaurantService.checkForResetRestaurantVotes();

        log.info("setUser {}", userId);
        return "redirect:restaurants";
    }

    @RequestMapping("/restaurants")
    public String myPage(Model model) {
        User user = userService.get(SecurityUtil.authUserId());
        model.addAttribute("isButtonActive", user.isVoteToday());
        model.addAttribute("restaurants",
                restaurantService.getAll());
        return "restaurants";
    }

    @GetMapping("restaurants/{id}/menu")
    public String getMenu(@PathVariable("id") Long restaurantId, Model model) {
        model.addAttribute("dishes", restaurantService.get(restaurantId).getDishes());
        return "menu";
    }
}
