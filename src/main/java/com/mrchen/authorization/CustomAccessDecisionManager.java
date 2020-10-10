package com.mrchen.authorization;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 17:35
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes==null){
            return;
        }
        for (ConfigAttribute c : configAttributes) {
            String roleName = c.getAttribute();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority g:authorities){
                String authority = g.getAuthority();
                if (authority.equals(roleName)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("无权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
