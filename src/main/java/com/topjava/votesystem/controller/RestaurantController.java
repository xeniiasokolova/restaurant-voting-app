package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.service.RestaurantService;
import com.topjava.votesystem.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    protected RestaurantService restaurantService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", restaurantService.get(ServletUtil.getId(request)));
        return "restaurantForm";
    }

    @PostMapping(value = "/save")
    public String updateOrDelete(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("name"));
        if (request.getParameter("id").isEmpty()) {
            restaurantService.create(restaurant);
        } else {
            restaurantService.update(restaurant, Long.parseLong(request.getParameter("id")));
        }
        return "redirect:/restaurants";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        restaurantService.delete(ServletUtil.getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model) {
        if (ServletUtil.getKeyword(request).equals("")) return "redirect:/restaurants";
        model.addAttribute("restaurants", restaurantService.search(ServletUtil.getKeyword(request)));
        return "restaurants";
    }
}
