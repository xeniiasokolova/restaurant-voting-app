package com.topjava.votesystem.repository;

import com.topjava.votesystem.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    @Query(value = "SELECT d FROM Dish d WHERE d.name LIKE '%' || :keyword || '%'"
            + " OR d.description LIKE '%' || :keyword || '%'")
    public List<Dish> search(@Param("keyword") String keyword);
}
