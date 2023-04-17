package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Dish;
import com.topjava.votesystem.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    DishRepository repository;

    public void save(Dish dish) {
        repository.save(dish);
    }

    public List<Dish> getAll() {
        return (List<Dish>) repository.findAll();
    }

    public Dish get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Dish> search(String keyword) {
        return repository.search(keyword);
    }
}
