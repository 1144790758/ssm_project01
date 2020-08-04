package com.lhl.controller;

import com.github.pagehelper.PageInfo;
import com.lhl.domain.Orders;
import com.lhl.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-01 18:13
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    IOrdersService ordersService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll(){
//        ModelAndView mv=new ModelAndView();
//        List<Orders> ordersList = ordersService.findAll();
//        mv.setViewName("orders-list");
//        mv.addObject("ordersList",ordersList);
//        return mv;
//    }

    /**
     * 进行了分页显示
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page",required = true)Integer page,@RequestParam(value = "pageSize",required = true)Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,pageSize);

        PageInfo pageInfo=new PageInfo(ordersList);

        mv.addObject("orderList",ordersList);
        mv.setViewName("orders-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id")String id){
        ModelAndView mv=new ModelAndView();
        Orders order=ordersService.findById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }

}
