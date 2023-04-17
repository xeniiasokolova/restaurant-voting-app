package com.topjava.votesystem.controller;

import com.topjava.votesystem.model.Dish;
import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.service.DishService;
import com.topjava.votesystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);
    public static final String DISHES_LINK =  "/restaurants/{restaurantId}";
    public static final String RESTAURANTS_LINK = "/restaurants";
    @Autowired
    protected RestaurantService restaurantService;
    @Autowired
    private DishService dishService;


    @RequestMapping(RESTAURANTS_LINK)
    public ModelAndView homeRestaurants() {
        ModelAndView mav = new ModelAndView("index_restaurant");
        mav.addObject("restaurants", restaurantService.getAll());
        log.info("getAll()");
        return mav;
    }

    @RequestMapping("restaurants/new")
    public String newRestaurant(Map<String, Object> model) {
        Restaurant restaurant = new Restaurant();
        log.info("create {}", restaurant);
        model.put("restaurant", restaurant);
        return "new_restaurant";
    }

    @RequestMapping(value = "restaurants/save", method = RequestMethod.POST)
    public String saveRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        log.info("save {}", restaurant);
        restaurantService.save(restaurant);
        return "redirect:" + RESTAURANTS_LINK;
    }

    @RequestMapping(RESTAURANTS_LINK + "/delete")
    public String deleteRestaurant(@RequestParam Long id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
        return "redirect:" + RESTAURANTS_LINK;
    }

    @RequestMapping(RESTAURANTS_LINK + "/edit")
    public ModelAndView editRestaurant(@RequestParam Long id) {
        Restaurant restaurant = restaurantService.get(id);
        log.info("update {} with id={}", restaurant, id);
        ModelAndView mav = new ModelAndView("edit_restaurant");
        mav.addObject("restaurant", restaurant);
        return mav;
    }


    @RequestMapping(RESTAURANTS_LINK + "/search")
    public ModelAndView searchRestaurant(@RequestParam String keyword) {
        List<Restaurant> result = restaurantService.search(keyword);
        log.info("search {} with keyword={}", result, keyword);
        ModelAndView mav = new ModelAndView("search_restaurant");
        mav.addObject("result", result);
        return mav;
    }



    @RequestMapping(DISHES_LINK)
    public ModelAndView homeDishes(@PathVariable(value = "restaurantId") Long id) {
        ModelAndView mav = new ModelAndView("index_dish");
        mav.addObject("dishes", restaurantService.get(id).getDishes());
        mav.addObject("restaurant", restaurantService.get(id));
        log.info("getAll()");
        return mav;
    }

    @RequestMapping( DISHES_LINK + "/new")
    public String newDish(Map<String, Object> model, @PathVariable(value = "restaurantId") Long id) {
        Dish dish = new Dish();
        dish.setRestaurant(restaurantService.get(id));
        log.info("create {}", dish);
        model.put("dish", dish);
        return "new_dish";
    }

    @RequestMapping(value =  DISHES_LINK + "/save", method = RequestMethod.POST)
    public String saveDish(@ModelAttribute("dish") Dish dish, @PathVariable(value = "restaurantId") Long id) {
        dish.setRestaurant(restaurantService.get(id));
        log.info("save {}", dish);
        dishService.save(dish);
        return "redirect:" + RESTAURANTS_LINK + "/" + id.toString();
    }

    @RequestMapping(DISHES_LINK +"/delete")
    public String deleteDish(@RequestParam Long id, @PathVariable(value = "restaurantId") Long restaurantId) {
        log.info("delete {}", id);
        dishService.delete(id);
        return "redirect:" + RESTAURANTS_LINK + "/" + restaurantId.toString();
    }

    @RequestMapping(DISHES_LINK +"/edit")
    public ModelAndView editDish(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("edit_dish");
        Dish dish = dishService.get(id);
        log.info("update {} with id={}", dish, id);
        mav.addObject("dish", dish);
        return mav;
    }

    @RequestMapping(DISHES_LINK + "/search")
    public ModelAndView searchDish(@RequestParam String keyword) {
        List<Dish> result = dishService.search(keyword);
        log.info("search {} with keyword={}", result, keyword);
        ModelAndView mav = new ModelAndView("search_dish");
        mav.addObject("result", result);
        return mav;
    }
}
