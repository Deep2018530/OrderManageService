package com.order.webservice.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.order.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao extends BaseMapper<Order> {
    @Select("select * , (select count(1) from order where id = (select order_id from order_detail where product_id = (select id from product where name= #{name, jdbcType=VARCHAR}))) as total_elements from order where id = (select order_id from order_detail where product_id = (select id from product where name= #{name, jdbcType=VARCHAR})) limit #{pageSize},#{size}")
    List<Order> selectListById(@Param("pageSize") Integer pageSize, @Param("size") Integer size, @Param("name") String name);
}
