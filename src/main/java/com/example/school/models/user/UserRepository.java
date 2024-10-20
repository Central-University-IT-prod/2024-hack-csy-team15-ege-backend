package com.example.school.models.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE " +
            "(:exp IS NULL OR u.exp = :exp) AND " +
            "(:level IS NULL OR u.level = :level)")
    List<User> findByExpAndLevel(@Param("exp") Integer exp, @Param("level") Integer level);
}
