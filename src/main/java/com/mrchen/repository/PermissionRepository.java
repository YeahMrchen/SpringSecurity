package com.mrchen.repository;

import com.mrchen.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/28 10:11
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    List<Permission> findByUrl(String url);

    @Query(value = "select p.ROLE_ID from permission as p where URL = ?1",nativeQuery = true)
    List<Integer> findRolesByUrl(String url);
}
