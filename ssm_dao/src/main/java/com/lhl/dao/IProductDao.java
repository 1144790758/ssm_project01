package com.lhl.dao;

import com.lhl.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-01-30 17:22
 */


public interface IProductDao {

    @Select("select * from product where id = #{id}")
    Product findById(String id);

    @Select(value = {"select * from product"})
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
