package com.lhl.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhl.dao.IProductDao;
import com.lhl.domain.Product;
import com.lhl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-01-30 17:38
 */
@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
