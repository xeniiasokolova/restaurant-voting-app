package com.topjava.votesystem.repository;

import com.topjava.votesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.name LIKE '%' || :keyword || '%'"
            + " OR u.email LIKE '%' || :keyword || '%'")
    public List<User> search(@Param("keyword") String keyword);
}
