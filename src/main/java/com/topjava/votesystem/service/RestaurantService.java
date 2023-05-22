package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    @Autowired
    private final RestaurantRepository repository;
    private Boolean buttonClicked;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Restaurant restaurant) {
        repository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant, Long id) {
        restaurant.setId(id);
        log.info("update {} with id={}", restaurant, id);
        repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        log.info("delete {}", id);
        repository.deleteById(id);
    }

    public List<Restaurant> search(String keyword) {
        List<Restaurant> restaurants = repository.search(keyword);
        log.info("search {} with keyword={}", restaurants, keyword);
        return restaurants;
    }

    public Boolean isButtonClicked() {
        return buttonClicked;
    }

    public void setButtonClicked() {
        if (buttonClicked == null) {
            buttonClicked = true;
        }
    }

    public void checkForResetRestaurantVotes() {
        List<Restaurant> restaurantsToUpdate = getAll().stream()
                .filter(r -> r.getDateTimeLastVote() != null && r.getDateTimeLastVote().toLocalDate().isBefore(LocalDate.now()))
                .peek(r -> r.setCountVoices(0))
                .collect(Collectors.toList());
        log.info("reset votes from another days {}", restaurantsToUpdate);
        repository.saveAll(restaurantsToUpdate);
    }

    public void deleteVote(Restaurant restaurant) {
        if (restaurant != null && restaurant.getCountVoices() > 0) {
            restaurant.setCountVoices(restaurant.getCountVoices() - 1);
            log.info("delete vote from restaurant {}", restaurant);
            repository.save(restaurant);
        }
    }

    public void addVote(long id) {
        Restaurant restaurant = get(id);
        restaurant.setCountVoices(restaurant.getCountVoices() + 1);
        restaurant.setDateTimeLastVote(LocalDateTime.now());
        repository.save(restaurant);
    }

}
