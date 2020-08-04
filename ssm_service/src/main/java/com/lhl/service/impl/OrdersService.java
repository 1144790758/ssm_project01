package com.lhl.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhl.dao.IOrdersDao;
import com.lhl.domain.Orders;
import com.lhl.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-01 18:11
 */
@Service
@Transactional
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findAll(int num, int size) {
        PageHelper.startPage(num,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
