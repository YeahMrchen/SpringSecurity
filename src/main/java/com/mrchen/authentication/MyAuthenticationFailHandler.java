package com.mrchen.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/29 11:07
 */
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String message = e.getMessage();
        response.getWriter().print("登陆失败!!!    "+message);
    }
}
