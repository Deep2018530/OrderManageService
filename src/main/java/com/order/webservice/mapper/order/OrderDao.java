package com.order.webservice.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.vo.order.OrderRefundVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao extends BaseMapper<Order> {
    @Select("select * , (select count(1) from order where id = (select order_id from order_detail where product_id = (select id from product where name= #{name, jdbcType=VARCHAR}))) as total_elements from order where id = (select order_id from order_detail where product_id = (select id from product where name= #{name, jdbcType=VARCHAR})) limit #{pageSize},#{size}")
    List<Order> selectListById(@Param("pageSize") Integer pageSize, @Param("size") Integer size, @Param("name") String name);

    @Select("select a.id, a.user_id, a.amount, a.create_time, a.pay_time, a.status, a.last_time, a.verify_reject_reason, b.order_id, b.product_id, b.num, b.amount as unit_price, c.name, c.detail, c.more_detail, d.nick_name, d.mobile from order a, order_detail b, product c, user d where a.id = b.order_id and b.product_id = c.id and a.user_id = d.id and a.id = #{id}")
    OrderRefundVo selectOneById(@Param("id") Integer orderId);

}
