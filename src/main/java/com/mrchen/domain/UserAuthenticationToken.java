package com.mrchen.domain;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 18:57
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {
    private String userName;
    private String password;

    public UserAuthenticationToken(String userName,String password) {
        super(null);
        this.userName=userName;
        this.password=password;
    }
    public UserAuthenticationToken(String userName,String password,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userName=userName;
        this.password=password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }
}
