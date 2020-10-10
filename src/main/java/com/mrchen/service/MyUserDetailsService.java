package com.mrchen.service;

import com.mrchen.domain.Authority;
import com.mrchen.domain.MyUserDetails;
import com.mrchen.domain.Role;
import com.mrchen.domain.User;
import com.mrchen.repository.RoleRepository;
import com.mrchen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 8:50
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user==null){
            return null;
        }
        List<Role> roles = roleService.getRolesByUserId(user.getUserId());
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUserName(user.getUserName());
        myUserDetails.setPassword(user.getPassWord());
        List<Authority> authorities = new ArrayList<>();
        for (Role role:roles){
            Authority authority = new Authority();
            authority.setRole(role.getRoleName());
            authorities.add(authority);
        }
        myUserDetails.setRoles(authorities);

        return myUserDetails;
    }
}
