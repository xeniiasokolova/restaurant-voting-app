package com.topjava.votesystem.service;

import com.topjava.votesystem.model.Restaurant;
import com.topjava.votesystem.model.User;
import com.topjava.votesystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save(User user) {
        log.info("save {}", user);
        repository.save(user);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(Long id) {
        return repository.findById(id).get();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(Long id) {
        log.info("delete {}", id);
        repository.deleteById(id);
    }
    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        log.info("create {}", user);
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, Long userId) {
        user.setId(userId);
        log.info("update {} with id={}", user, userId);
        repository.save(user);
    }

    public List<User> search(String keyword) {
        List<User> users = repository.search(keyword);
        log.info("search {} with keyword={}", users, keyword);
        return users;
    }

    public void checkForResetUserVote(User user) {
        if (user.isVoteToday()) {
            user.setVoted(false);
            user.setDateTimeVote(null);
            user.setRestaurant(null);
            log.info("reset user's vote {}", user);
            repository.save(user);
        }
    }

    public void addVote(User user, Restaurant restaurant) {
        if (user != null && restaurant != null) {
            user.setVoted(true);
            user.setDateTimeVote(LocalDateTime.now());
            user.setRestaurant(restaurant);
            log.info("add user's vote {} in restaurant {}", user, restaurant);
            repository.save(user);
        }
    }
}
