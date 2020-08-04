package com.lhl.controller;

import com.lhl.domain.Permission;
import com.lhl.domain.Role;
import com.lhl.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 19:41
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    IRoleService iRoleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList = iRoleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        iRoleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("role-show");
        Role role=iRoleService.findByRoleId(id);
        mv.addObject("role",role);
        return mv;
    }

    @RequestMapping("findByIdAndDeleteRole.do")
    public String findByIdAndDeleteRole(String id){
        iRoleService.delete(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/addPermissionByRoleId.do")
    public ModelAndView addPermissionByRoleId(@RequestParam("id") String roleId){
        ModelAndView mv=new ModelAndView();
        Role role = iRoleService.findByRoleId(roleId);
        //查出没有的权限
        List<Permission> permissionList=iRoleService.findWithoutPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,@RequestParam("ids")String[] permissionId){
        iRoleService.addPermissionByRoleId(roleId,permissionId);
        return "redirect:addPermissionByRoleId.do"+"?id="+roleId;
    }

}
