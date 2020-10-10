package com.mrchen.authentication;

import com.mrchen.domain.Authority;
import com.mrchen.domain.Role;
import com.mrchen.domain.User;
import com.mrchen.domain.UserAuthenticationToken;
import com.mrchen.service.MyUserDetailsService;
import com.mrchen.service.RoleService;
import com.mrchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:10
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = userService.getUserByUserName(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //HashSet<Authority> roles = new HashSet<>();
        List<Authority> roles = (List<Authority>) userDetails.getAuthorities();
        if (passwordEncoder.matches(password, user.getPassWord())) {
            UserAuthenticationToken token = new UserAuthenticationToken(username, password,roles);
            token.setDetails(userDetails);
            token.setAuthenticated(true);
            return token;
        }else {
            throw new BadCredentialsException("密码错误!!!");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserAuthenticationToken.class);
    }
}
