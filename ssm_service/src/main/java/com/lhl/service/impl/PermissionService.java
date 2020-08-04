package com.lhl.service.impl;

import com.lhl.dao.IPermissionDao;
import com.lhl.domain.Permission;
import com.lhl.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 20:10
 */
@Service
public class PermissionService implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findByPermissionId(String id) {
        return permissionDao.findByPermissionId(id);
    }

    @Override
    public void deleteById(String id) {
        permissionDao.role_permission_deletById(id);
        permissionDao.deleteById(id);
    }
}
