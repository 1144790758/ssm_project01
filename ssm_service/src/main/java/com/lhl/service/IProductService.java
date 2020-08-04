package com.lhl.service;

import com.lhl.domain.Product;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-01-30 17:24
 */
public interface IProductService {

    List<Product> findAll(Integer pageNum,Integer size);

    void save(Product product);
}
