package com.dev.identity_service.repository;

import com.dev.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String username);
}
