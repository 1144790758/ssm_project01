package com.lhl.service;

import com.lhl.domain.Permission;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 20:08
 */
public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    Permission findByPermissionId(String id);

    void deleteById(String id);
}
