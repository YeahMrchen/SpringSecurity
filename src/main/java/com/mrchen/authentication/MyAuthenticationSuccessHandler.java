package com.mrchen.authentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mrchen.domain.Authority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/29 10:26
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static String USER_NAME = "userName";
    private static String AUTHORITY = "authority";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String userName = (String) authentication.getPrincipal();
        List<Authority> authorities = (List<Authority>) authentication.getAuthorities();
        HashMap<String, Object> map = new HashMap<>();
        map.put(USER_NAME, userName);
        map.put(AUTHORITY, authorities);
        responseJson(httpServletResponse, map);
    }

    public static void responseJson(HttpServletResponse response, Object o) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String jsonString = JSONObject.toJSONString(o);
            outputStream.write(jsonString.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
