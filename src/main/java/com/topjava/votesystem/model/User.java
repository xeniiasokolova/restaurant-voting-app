package com.topjava.votesystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "users")
public class User extends AbstractRegisteredEntity {

    public static final int START_SEQ_USER = 100000;
    @Id
    @SequenceGenerator(name = "global_seq_users", sequenceName = "global_seq_users", allocationSize = 1, initialValue = START_SEQ_USER)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_users")
    private Long id;


    @Email
    @NotBlank
    @Size(max = 128)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "voted", nullable = false, columnDefinition = "bool default false")
    private boolean isVoted = false;

    @Column(name = "datetime_vote", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTimeVote;

  //  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    public User(String name, String email, String password) {
        super(name, LocalDateTime.now());
        this.email = email;
        this.password = password;
        this.isVoted = false;
    }

    public User(Long id, String name, String email, String password, Collection<Role> roles) {
        super(name, LocalDateTime.now());
        this.id = id;
        this.email = email;
        this.password = password;
        this.isVoted = false;
        setRoles(roles);
    }

    public User(Long id, String name, String email, String password, Role... roles) {
        super(name, LocalDateTime.now());
        this.id = id;
        this.email = email;
        this.password = password;
        this.isVoted = false;
        List.of(roles);
    }


    public User() {

    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
        if (voted) {
            setDateTimeVote(LocalDateTime.now());
        }
    }

    public LocalDateTime getDateTimeVote() {
        return dateTimeVote;
    }

    public void setDateTimeVote(LocalDateTime dateTimeVote) {
        this.dateTimeVote = dateTimeVote;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public boolean isNew() {
        return this.id == null;
    }

    public boolean isVoteToday() {
        if (getDateTimeVote() == null) return true;
        if (LocalDateTime.now().toLocalDate().equals(getDateTimeVote().toLocalDate()) &&
                LocalDateTime.now().toLocalTime().isAfter(LocalTime.of(20, 0))) return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered =" + registered +
                ", isVoted=" + isVoted +
                ", dateTimeVote=" + dateTimeVote +
                '}';
    }
}
