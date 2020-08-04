package com.lhl.controller;

import com.github.pagehelper.PageInfo;
import com.lhl.domain.Product;
import com.lhl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @athor:lhl
 * @create:2020-01-30 18:30
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN") //只允许有admin授权的角色访问此方法
    @Secured("ROLE_ADMIN")  //spring security 本身就有的不需要导依赖,必须有ROLE_前缀
    public ModelAndView findAll(@RequestParam("pageNum")Integer pageNum,@RequestParam("size")Integer size){
        ModelAndView mv=new ModelAndView();
        List<Product> productList = productService.findAll(pageNum,size);

        PageInfo pageInfo=new PageInfo(productList);

        mv.addObject("productList",productList);
        mv.setViewName("product-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product p){
        productService.save(p);
        return "redirect:findAll.do";
    }
}
