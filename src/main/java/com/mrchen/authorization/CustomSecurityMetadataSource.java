package com.mrchen.authorization;

import com.mrchen.repository.PermissionRepository;
import com.mrchen.service.PermissionService;
import com.mrchen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 17:34
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Integer> roleIds = permissionService.getRolesByUrl(requestUrl);
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
            return null;
        }
        List<String> roles = new ArrayList<>();
        for (Integer roleId:roleIds){
            String roleName = roleService.getRoleNameByRoleId(roleId);
            roles.add(roleName);
        }
        return SecurityConfig.createList(roles.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
