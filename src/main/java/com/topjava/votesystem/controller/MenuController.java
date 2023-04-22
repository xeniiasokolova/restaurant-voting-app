package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.Dish;
import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.service.DishService;
import com.topjava.votesystem.service.RestaurantService;
import com.topjava.votesystem.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/restaurants/{id}/menu")
public class MenuController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/create")
    public String create(@PathVariable("id") Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        model.addAttribute("dish", new Dish(restaurant));
        return "dishForm";
    }


    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("dish", dishService.get(ServletUtil.getId(request)));
        return "dishForm";
    }

    @PostMapping(value = "/save")
    public String updateOrDelete(HttpServletRequest request, @PathVariable("id") Long restaurantId) {
        Dish dish = new Dish(restaurantService.get(restaurantId), request.getParameter("name"), request.getParameter("description"), Float.parseFloat(request.getParameter("price")));
        if (request.getParameter("id").isEmpty()) {
            dishService.create(dish);
        } else {
            dishService.update(dish, Long.parseLong(request.getParameter("id")));
        }
        return "redirect:/restaurants/" + restaurantId + "/menu";
    }


    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @PathVariable("id") Long restaurantId) {
        dishService.delete(ServletUtil.getId(request));
        return "redirect:/restaurants/" + restaurantId + "/menu";
    }

    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model, @PathVariable("id") Long restaurantId) {
        if (ServletUtil.getKeyword(request).equals("")) return "redirect:/restaurants/" + restaurantId + "/menu";
        model.addAttribute("dishes", dishService.search(restaurantId, ServletUtil.getKeyword(request)));
        return "menu";
    }
}
