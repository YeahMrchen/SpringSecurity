package com.mrchen.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 15:48
 */
@Data
public class Authority  implements GrantedAuthority, Serializable {
    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
