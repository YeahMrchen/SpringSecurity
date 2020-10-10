package com.mrchen.service;

import com.mrchen.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testInsert(){
//        User user = new User();
//        user.setUserId(000);
//        user.setUserName("chenwenxing");
//        user.setPassWord("123456");
//        userService.insertUser(user);
        User user = new User();
        user.setUserId(001);
        user.setUserName("chenwenxing1");
        user.setPassWord("123456");
        userService.insertUser(user);
    }

    @Test
    public void testGetUser(){
        User chenwenxing = userService.getUserByUserName("chenwenxing");
        boolean matches = passwordEncoder.matches("123456", chenwenxing.getPassWord());
        System.out.println(matches);
    }
}
