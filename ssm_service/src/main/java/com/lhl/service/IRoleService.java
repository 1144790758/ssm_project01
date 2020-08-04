package com.lhl.service;

import com.lhl.domain.Permission;
import com.lhl.domain.Role;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 19:43
 */
public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findByRoleId(String id);

    void delete(String id);

    void addPermissionByRoleId(String roleId,String[] permissionIds);

    List<Permission> findWithoutPermission(String roleId);
}
