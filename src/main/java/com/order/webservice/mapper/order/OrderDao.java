package com.order.webservice.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.po.order.OrderDetail;
import com.order.webservice.domain.vo.order.OrderRefundVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface OrderDao extends BaseMapper<Order> {
    //根据product_name从order_detail返回OrderDetail对象集合
    @Select("select * from order_detail where product_id = (select id from product where `name`= #{productName}) limit #{pageSize},#{size}")
    List<OrderDetail> selectListByProductName(@Param("pageSize") Integer pageSize, @Param("size") Integer size, @Param("productName") String productName);

    //根据product_name从order_detail返回行数
    @Select("select count(1) as total_count from order_detail where product_id = (select id from product where `name`= #{productName})")
    Long selectCountByProductName(@Param("productName") String productName);

    //根据order_id从order返回Order对象集合
    @Select("select * from `order` where id = #{orderId}")
    List<Order> selectListByOrderId(@Param("orderId") BigInteger orderId);


    Long selectOne(@Param("name") String name);

    @Select("select a.id, a.user_id, a.amount, a.create_time, a.pay_time, a.`status`, a.last_time, a.verify_reject_reason " +
            ", b.order_id, b.product_id, b.num, b.amount as unit_price " +
            ", c.`name`, c.detail, c.more_detail " +
            ", d.nick_name, d.mobile " +
            "from `order` a, order_detail b, product c, `user` d where " +
            "a.id = b.order_id " +
            "and b.product_id = c.id " +
            "and a.user_id = d.id " +
            "and a.id = {id}")
    List<OrderRefundVo> selectOneById(@Param("id") Integer orderId);

}
