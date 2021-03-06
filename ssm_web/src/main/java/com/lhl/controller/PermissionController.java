package com.lhl.controller;

import com.lhl.domain.Permission;
import com.lhl.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-07 20:04
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList= permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findByPermissionId.do")
    public ModelAndView findByPermissionId(String id){
        Permission permission= permissionService.findByPermissionId(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("deleteById.do")
    public String deleteById(String id){
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}
