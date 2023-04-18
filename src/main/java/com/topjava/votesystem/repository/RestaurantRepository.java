package com.topjava.votesystem.repository;

import com.topjava.votesystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT r FROM Restaurant r WHERE r.name LIKE '%' || :keyword || '%'")
    public List<Restaurant> search(@Param("keyword") String keyword);
}
