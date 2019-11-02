package com.order.webservice.service.order;

import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.enums.OrderStatus;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderNewVo;
import com.order.webservice.domain.vo.order.OrderVo;

import java.math.BigInteger;

public interface OrderService {

    PageResponseVo<OrderVo> query(Integer page, Integer size, OrderDto orderDto);


    /**
     * 购买商品→生成订单
     *
     * @param userId
     * @param productId
     * @return
     */
    OrderNewVo buyProduct(Object userId, Long productId);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param applyForRefund
     * @return
     */
    Boolean updateOrderStatus(BigInteger orderId, OrderStatus applyForRefund);
}
