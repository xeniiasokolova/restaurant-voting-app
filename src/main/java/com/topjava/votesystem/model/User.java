package com.topjava.votesystem.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends AbstractRegisteredEntity {

    public static final int START_SEQ_USER = 100000;
    @Id
    @SequenceGenerator(name = "global_seq_users", sequenceName = "global_seq_users", allocationSize = 1, initialValue = START_SEQ_USER)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_users")
    private Long id;

   // private Integer roleId;

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

    public User(String name, String email, String password) {
        super(name, LocalDateTime.now());
        this.email = email;
        this.password = password;
        this.isVoted = false;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }*/

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
             //   ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered =" + registered +
                ", isVoted=" + isVoted +
                ", dateTimeVote=" + dateTimeVote +
                '}';
    }
}
