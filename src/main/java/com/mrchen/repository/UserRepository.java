package com.mrchen.repository;

import com.mrchen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:46
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

}
