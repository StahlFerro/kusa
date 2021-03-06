package io.stahlferro.kusa.repositories;


import io.stahlferro.kusa.models.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserBaseRepository extends JpaRepository<UserBase, UUID> {
    public List<UserBase> findByName(String name);
    public List<UserBase> findByEmail(String email);
    public UserBase findByLoginName(String loginName);
//    @Query(value = "SELECT next_val FROM user_sequence", nativeQuery = true)
//    UUID getNextId();
}
