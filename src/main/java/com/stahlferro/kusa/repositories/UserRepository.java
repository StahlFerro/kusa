package com.stahlferro.kusa.repositories;


import com.stahlferro.kusa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByName(String name);
    public List<User> findByClearanceLevel(int clearanceLevel);
}
