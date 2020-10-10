package com.mrchen.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 17:28
 */
public class MySecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    private CustomSecurityMetadataSource customSecurityMetadataSource;
    public void setCustomSecurityMetadataSource(CustomSecurityMetadataSource customSecurityMetadataSource) {
        this.customSecurityMetadataSource = customSecurityMetadataSource;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return customSecurityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        invoke(filterInvocation);
    }

    @Override
    public void destroy() {

    }
    public void invoke(FilterInvocation fi) throws IOException {
        //重要，会调用 AccessDecisionManager 来验证当前已认证成功的用户是否有权限访问该资源
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //请求真正的 / 服务
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (ServletException e) {
            super.afterInvocation(token, null);
        }
    }
}
