package com.lhl.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhl.dao.IUserInfoDAO;
import com.lhl.domain.Role;
import com.lhl.domain.UserInfo;
import com.lhl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-05 19:36
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Autowired
    IUserInfoDAO userInfoDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserInfo> findAll(Integer num,Integer size) {
        PageHelper.startPage(num,size);
        return userInfoDAO.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDAO.findById(id);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return userInfoDAO.findUserByIdAndAllRole(id);
    }

    @Override
    public void addUser_Role(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userInfoDAO.addUser_Role(userId,roleId);
        }
    }

    /**
     * 具体的验证逻辑
     * @param username 浏览器传过来的username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findByName(username);

        if (userInfo!=null){
            List<SimpleGrantedAuthority> authorities =new ArrayList<>();
            for (Role role : userInfo.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            UserDetails userDetails=new User(username,userInfo.getPassword(),authorities);
            return userDetails;
        }

        return null;
    }
}
