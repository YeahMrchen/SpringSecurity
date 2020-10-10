package com.mrchen.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 19:28
 */
@Data
@Table(name = "user_role")
@Entity(name = "role")
public class Role{
    @Id
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "ROLE_NAME")
    private String roleName;
}
