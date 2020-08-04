package com.lhl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhl.domain.Role;
import com.lhl.domain.UserInfo;
import com.lhl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-05 19:28
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(Integer pageNum,Integer size){

        ModelAndView mv=new ModelAndView();
        List<UserInfo> userInfoList=userInfoService.findAll(pageNum,size);
        PageInfo pageInfo=new PageInfo(userInfoList);
        mv.addObject("userList",userInfoList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String Save(UserInfo userInfo){
        userInfoService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userInfoService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;

    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        //根据id查询用户
        UserInfo userInfo = userInfoService.findById(id);
        //查询出该用可以添加的角色
        List<Role> roleList=userInfoService.findUserByIdAndAllRole(id);
        mv.setViewName("user-role-add");
        mv.addObject("roleList",roleList);
        mv.addObject("user",userInfo);
        return mv;
    }

    //在中间表上添加可以添加的角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId, @RequestParam("ids")String[] roleIds){
        userInfoService.addUser_Role(userId,roleIds);
        return "redirect:findUserByIdAndAllRole.do"+"?id="+userId;
    }

}
