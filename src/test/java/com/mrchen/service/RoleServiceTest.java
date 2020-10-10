package com.mrchen.service;

import com.mrchen.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 14:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void getRolesByUserIdTest(){
        List<Role> roles = roleService.getRolesByUserId(000);
        for (Role role:roles){
            System.out.println(role);
        }
    }

    @Test
    public void getRoleNameByRoleIdTest(){
        String roleName = roleService.getRoleNameByRoleId(0);
        System.out.println(roleName);
    }

}
