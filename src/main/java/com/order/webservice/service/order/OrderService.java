package com.order.webservice.service.order;

import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.enums.OrderStatus;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderNewVo;
import com.order.webservice.domain.vo.order.OrderRefundVo;
import com.order.webservice.domain.vo.order.OrderStatisticsVo;

import java.math.BigInteger;
import java.util.List;

public interface OrderService {

    PageResponseVo<Object> query(Integer page, Integer size, OrderDto orderDto);

    PageResponseVo<Object> productQueryOrder(Integer page, Integer size, String productName);

    List<String> state();

    List<OrderRefundVo> refundQueryOrder(BigInteger orderId);

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

    /**
     * 审核通过
     *
     * @param orderId
     * @param userId
     * @return
     */
    Boolean passVerify(BigInteger orderId, Long userId);

    /**
     * 审核拒绝
     *
     * @param orderId
     * @param userId
     * @param rejectReason
     * @return
     */
    Boolean rejectVerify(BigInteger orderId, Long userId, String rejectReason);

    /**
     * 订单顶部统计（审核、未审核)
     *
     * @return
     */
    OrderStatisticsVo getOrderStatistics();

}
