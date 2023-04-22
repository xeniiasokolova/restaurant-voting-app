package com.topjava.votesystem.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

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


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    public User(String name, String email, String password) {
        super(name, LocalDateTime.now());
        this.email = email;
        this.password = password;
        this.isVoted = false;
    }

    public User(String name, String email, String password, Collection<Role> roles) {
        super(name, LocalDateTime.now());
        this.email = email;
        this.password = password;
        this.isVoted = false;
        setRoles(roles);
    }

    public User() {

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
