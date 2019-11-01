package com.order.webservice.service.order;

import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderVo;

public interface OrderService {

    PageResponseVo<OrderVo> query(Integer page, Integer size, OrderDto orderDto);

}
