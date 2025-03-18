package com.dev.identity_service.repository;

import com.dev.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReponsitory extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);
}
