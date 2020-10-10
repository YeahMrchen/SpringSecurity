package com.mrchen.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:28
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String passWord;
}
