package com.lhl.service;

import com.lhl.domain.Role;
import com.lhl.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-05 19:35
 */
public interface IUserInfoService extends UserDetailsService {


    List<UserInfo> findAll(Integer num,Integer size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    void addUser_Role(String userId, String[] roleIds);
}
