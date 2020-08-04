package com.lhl.dao;

import com.lhl.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-06 18:47
 */
public interface IPermissionDao {

    //通过一张中间表查询角色对应的所有权限
    @Select("select * from permission where id in(select PERMISSIONID from role_permission where ROLEID=#{id})")
    List<Permission> findById(String id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id =#{id}")
    Permission findByPermissionId(String id);

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id);

    @Delete("delete from role_permission where permissionId=#{permissonId}")
    void role_permission_deletById(String permissonId);
}
