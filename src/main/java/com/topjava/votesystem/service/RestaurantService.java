package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Restaurant restaurant) {
        repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return (List<Restaurant>) repository.findAll();
    }

    public Restaurant get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Restaurant> search(String keyword) {
        return repository.search(keyword);
    }
}
