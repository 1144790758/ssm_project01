package com.lhl.dao;

import com.lhl.domain.Member;
import com.lhl.domain.Orders;
import com.lhl.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @athor:lhl
 * @create:2020-02-01 17:52
 */
public interface IOrdersDao {


    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "product", column = "productid",one = @One(select="com.lhl.dao.IProductDao.findById")),
    }
    )
    List<Orders> findAll();


    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "product", column = "productid",one = @One(select="com.lhl.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.lhl.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lhl.dao.ITravellerDao.findById"))
    }
    )
    Orders findById(String id);
}
