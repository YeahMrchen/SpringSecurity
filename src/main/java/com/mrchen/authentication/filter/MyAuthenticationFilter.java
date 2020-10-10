package com.mrchen.authentication.filter;

import com.mrchen.domain.MyUserDetails;
import com.mrchen.domain.UserAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 包装成所需要的Authentication
 *
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 11:27
 */
public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected MyAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MyAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    public MyAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * httpServletRequest取出username和password，封装成想要的类
     * 这里封装成UsernamePasswordAuthenticationToken
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        UserAuthenticationToken token = new UserAuthenticationToken(username, password);
        //details可自定义，这里用到框架带的WebAuthenticationDetails类
        //String remoteAddress/String sessionId
        //token.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUserName(username);
        myUserDetails.setPassword(password);
        token.setDetails(myUserDetails);
        //这里AuthenticationManager是哪一个？？？
        //logger.info("MyAuthenticationFilter");
        return this.getAuthenticationManager().authenticate(token);
    }
}
