package com.topjava.votesystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractRegisteredEntity {
    public static final int START_SEQ_DISH = 100000;

    @Id
    @SequenceGenerator(name = "global_seq_dishes", sequenceName = "global_seq_dishes", allocationSize = 1, initialValue = START_SEQ_DISH)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_dishes")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private Float price;


    public Dish(String name, String description, Float price) {
        super(name, LocalDateTime.now());
        this.description = description;
        this.price = price;
    }

    public Dish() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Dish {" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price.toString() +
                '}';
    }
}
