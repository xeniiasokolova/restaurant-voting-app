package com.topjava.votesystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractRegisteredEntity {
    public static final int START_SEQ_RESTAURANT = 100000;
    @Id
    @SequenceGenerator(name = "global_seq_restaurants", sequenceName = "global_seq_restaurants", allocationSize = 1, initialValue = START_SEQ_RESTAURANT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_restaurants")
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant(String name) {
        super(name, LocalDateTime.now());
        this.dishes = new ArrayList<>();
    }

    public Restaurant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                ", name='" + name +'}';
    }
}
