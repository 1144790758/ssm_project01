package com.lhl.controller;

import com.github.pagehelper.PageInfo;
import com.lhl.domain.SysLog;
import com.lhl.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-11 19:27
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam("pageNum")Integer pageNum,@RequestParam("size")Integer size){
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogList=sysLogService.findAll(pageNum,size);
        PageInfo pageInfo=new PageInfo(sysLogList);
        mv.addObject("sysLogs",sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;

    }


}
