package com.mrchen.service;

import com.mrchen.domain.Role;
import com.mrchen.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/27 13:58
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRolesByUserId(Integer userId){
        if (userId==null){
            return null;
        }
        return roleRepository.findByUserId(userId);
    }

    public String getRoleNameByRoleId(Integer roleId){
        if (roleId==null){
            return null;
        }
        return roleRepository.findRoleNameByRoleId(roleId);
    }
}
