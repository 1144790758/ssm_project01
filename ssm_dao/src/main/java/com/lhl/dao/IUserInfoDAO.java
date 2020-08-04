package com.lhl.dao;

import com.lhl.domain.Role;
import com.lhl.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-04 21:48
 */
public interface IUserInfoDAO {

    //根据传入用户名查询出用户信息和角色信息
    @Select("select * from users where username=#{username}")
    @Results({
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "username",property = "username"),
        @Result(column = "email",property = "email"),
        @Result(column = "password",property = "password"),
        @Result(column = "phoneNum",property = "phoneNum"),
        @Result(column = "status",property = "status"),
        @Result(column = "id",property = "roles",javaType = List.class,
        many = @Many(select = "com.lhl.dao.IRoleDao.findById")
        ),
    }
    )
    UserInfo findByName(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,
                    many = @Many(select = "com.lhl.dao.IRoleDao.findById")
            ),
    })
    UserInfo findById(String id);

    //查询出用户可添加的角色
    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findUserByIdAndAllRole(String id);

    //在users_role表中插入记录
    //values(#{userId},#{roleId})这么写不对,
    //对于多个参数mybatis会取String userId的userId属性;String roleId的roleId属性
    //用@param注解指定名字
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addUser_Role(@Param("userId") String userId, @Param("roleId") String roleId);
}
