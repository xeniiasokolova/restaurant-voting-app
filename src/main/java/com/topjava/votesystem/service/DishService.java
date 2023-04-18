package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Dish;
import com.topjava.votesystem.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public void save(Dish dish) {
        repository.save(dish);
    }

    public List<Dish> getAll() {
        return (List<Dish>) repository.findAll();
    }

    public Dish get(Long id) {
        return repository.findById(id).get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Dish> search(String keyword) {
        return repository.search(keyword);
    }
}
