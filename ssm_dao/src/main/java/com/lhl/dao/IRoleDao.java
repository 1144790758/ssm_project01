package com.lhl.dao;

import com.lhl.domain.Permission;
import com.lhl.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-04 21:59
 */
public interface IRoleDao {

    //通过中间表查出userId对应的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ROLENAME",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = "com.lhl.dao.IPermissionDao.findById"))
    })
    List<Role> findById(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ROLENAME",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = "com.lhl.dao.IPermissionDao.findById"))
    })
    Role findByRoleId(String id);

    @Delete("delete from role where id=#{roleId}")
    void delete(String roleId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void delete_user_role(String roleId);

    @Delete("delete from role_permission where roleId=#{roleId}")
    void delete_role_permission(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findWithoutPermission(String roleId);

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionByRoleId(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
