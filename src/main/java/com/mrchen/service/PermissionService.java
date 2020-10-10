package com.mrchen.service;

import com.mrchen.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/28 10:22
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public List<Integer> getRolesByUrl(String url){
        if (StringUtils.isEmpty(url)){
            return null;
        }
        return permissionRepository.findRolesByUrl(url);
    }
}
