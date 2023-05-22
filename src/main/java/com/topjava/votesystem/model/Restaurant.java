package com.topjava.votesystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Column(name = "voices")
    private int countVoices = 0;

    @Column(name = "datetime_last_vote", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTimeLastVote;

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

    public boolean isNew() {
        return this.id == null;
    }

    public int getCountVoices() {
        return countVoices;
    }

    public void setCountVoices(int countVoices) {
        this.countVoices = countVoices;
    }

    public LocalDateTime getDateTimeLastVote() {
        return dateTimeLastVote;
    }

    public void setDateTimeLastVote(LocalDateTime dateTimeLastVote) {
        this.dateTimeLastVote = dateTimeLastVote;
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                "name='" + name +'}';
    }
}
