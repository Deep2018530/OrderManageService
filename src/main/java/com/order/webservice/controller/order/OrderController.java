package com.order.webservice.controller.order;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderNewVo;
import com.order.webservice.domain.vo.order.OrderVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/order")
@Api(value = "订单查询API接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/{page}/{size}")
    @ApiOperation(value = "管理员订单查询")
    public HttpResult<PageResponseVo<OrderVo>> query(@ApiParam(value = "第几页（第一页是1)")
                                                     @PathVariable(value = "page") Integer page,
                                                     @ApiParam(value = "每页条数")
                                                     @PathVariable(value = "size") Integer size,
                                                     @ApiParam(value = "订单查询对象") @RequestBody OrderDto orderDto) {
        return HttpResult.success(orderService.query(page, size, orderDto));
    }

    @PostMapping("/{productId}")
    @ApiOperation(value = "购买/新增订单")
    public HttpResult<OrderNewVo> add(@ApiParam(value = "token") @RequestHeader(value = "token") String token,
                                      @ApiParam(value = "产品ID") @PathVariable(value = "productId") Long productId) {

        Object userId = redisTemplate.opsForValue().get(token);
        Objects.requireNonNull(userId, UserErrorCode.USER_NOT_EXIST.getMessage());

        return HttpResult.success(orderService.buyProduct(userId, productId));
    }
}