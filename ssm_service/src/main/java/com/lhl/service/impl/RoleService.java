package com.lhl.service.impl;

import com.lhl.dao.IRoleDao;
import com.lhl.domain.Permission;
import com.lhl.domain.Role;
import com.lhl.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 19:44
 */
@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findByRoleId(String id) {
        return roleDao.findByRoleId(id);
    }

    @Override
    public void delete(String RoleId) {
        //由于role 关联了多张表是其他表的外键所以要先删除关联表中的数据
        roleDao.delete_role_permission(RoleId);
        roleDao.delete_user_role(RoleId);
        roleDao.delete(RoleId);
    }

    @Override
    public void addPermissionByRoleId(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionByRoleId(roleId,permissionId);
        }

    }


    @Override
    public List<Permission> findWithoutPermission(String roleId) {
        return roleDao.findWithoutPermission(roleId);
    }

}
