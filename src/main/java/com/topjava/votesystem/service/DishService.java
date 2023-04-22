package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Dish;
import com.topjava.votesystem.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private static final Logger log = LoggerFactory.getLogger(DishService.class);

    @Autowired
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish){
        log.info("create {}", dish);
        return repository.save(dish);

    }
    public void save(Dish dish) {
        log.info("save {}", dish);
        repository.save(dish);
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public Dish get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        log.info("delete {}", id);
        repository.deleteById(id);
    }

    public void update(Dish dish, Long dishId) {
        dish.setId(dishId);
        log.info("update {} with id={}", dish, dishId);
        repository.save(dish);
    }

    public List<Dish> search(long restaurantId, String keyword) {
        List<Dish> dishes = repository.search(restaurantId, keyword);
        log.info("search {} with keyword={} in restaurant with id {}", dishes, keyword, restaurantId);
        return dishes;
    }
}
