package com.mrchen.repository;

import com.mrchen.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:46
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByUserId(Integer userId);

    @Query(value = "select u.ROLE_NAME from user_role as u where ROLE_ID = ?1",nativeQuery = true)
    String findRoleNameByRoleId(Integer roleId);
}
