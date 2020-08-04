package com.lhl.dao;

import com.lhl.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-03 17:05
 */
public interface ITravellerDao {

    //本来游客表与订单表没有直接关系,通过一张中间表关联起来
    //先从中间表中根据订单id查询对应的游客id,然后通过此结果在游客表中查询游客信息

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findById(String orderId);
}
