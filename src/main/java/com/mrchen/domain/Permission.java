package com.mrchen.domain;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/28 9:51
 */
@Data
@Entity
@Table(name = "permission")
public class Permission{
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "URL")
    private String url;
}
