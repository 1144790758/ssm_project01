package com.lhl.service;

import com.lhl.domain.Orders;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-01 18:11
 */
public interface IOrdersService {

    /**
     * 为分页
     * @return
     */
    List<Orders> findAll();

    /**
     *
     * @param num 查询起始页码
     * @param size 每次分页条数
     * @return
     */
    List<Orders> findAll(int num,int size);

    Orders findById(String id);
}
