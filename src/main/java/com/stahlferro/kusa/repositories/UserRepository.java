package com.stahlferro.kusa.repositories;


import com.stahlferro.kusa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByName(String name);
    public List<User> findByEmail(String email);
    @Query(value = "SELECT next_val FROM user_sequence", nativeQuery = true)
    long getNextId();
}
