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
 * @date 2020/9/28 10:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionServiceTest {
    @Autowired
    private PermissionService permissionService;
    @Test
    public void getRolesByUserIdTest(){
        List<Integer> roles = permissionService.getRolesByUrl("/login/r1");
        for (Integer role:roles){
            System.out.println(role);
        }
    }
}
