package com.order.webservice.controller.order;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.dto.order.OrderVerifyDto;
import com.order.webservice.domain.enums.OrderStatus;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderNewVo;
import com.order.webservice.domain.vo.order.OrderRefundVo;
import com.order.webservice.domain.vo.order.OrderStatisticsVo;
import com.order.webservice.domain.vo.order.OrderVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
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

    @GetMapping("/toProduct/{page}/{size}")
    @ApiOperation(value = "商品名称模糊查询订单")
    public HttpResult<PageResponseVo<OrderVo>> productQueryOrder(@ApiParam(value = "第几页（第一页是1)")
                                                                 @PathVariable(value = "page") Integer page,
                                                                 @ApiParam(value = "每页条数")
                                                                 @PathVariable(value = "size") Integer size,
                                                                 @ApiParam(value = "商品名")
                                                                 @PathVariable(value = "productName") String productName) {
        return HttpResult.success(orderService.productQueryOrder(page, size, productName));
    }

    @GetMapping("/stateReady")
    @ApiOperation(value = "查询订单数据准备")
    public HttpResult<List<String>> state() {
        return HttpResult.success(orderService.state());
    }

    @GetMapping("/refund/query/{orderId}")
    @ApiOperation(value = "查询指定审核状态订单")
    public HttpResult<List<OrderRefundVo>> refundQueryOrder(@ApiParam(value = "审核状态订单号")
                                                      @PathVariable(value = "orderId") Integer orderId) {
        return HttpResult.success(orderService.refundQueryOrder(orderId));
    }

    @PostMapping("/{productId}")
    @ApiOperation(value = "购买/新增订单")
    public HttpResult<OrderNewVo> add(@ApiParam(value = "token") @RequestHeader(value = "token") String token,
                                      @ApiParam(value = "产品ID") @PathVariable(value = "productId") Long productId) {
        Object userId = redisTemplate.opsForValue().get(token);
        Objects.requireNonNull(userId, UserErrorCode.USER_NOT_EXIST.getMessage());
        return HttpResult.success(orderService.buyProduct(userId, productId));
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @return
     */
    @GetMapping("/refund/{orderId}")
    @ApiOperation(value = "申请退款")
    public HttpResult applyForRefund(@ApiParam(value = "订单号", required = true)
                                     @PathVariable(value = "orderId") BigInteger orderId) {
        return HttpResult.success(orderService.updateOrderStatus(orderId, OrderStatus.APPLY_FOR_REFUND));
    }

    @PostMapping("/refund/verify")
    @ApiOperation(value = "退款审核")
    public HttpResult refundVerify(@RequestBody OrderVerifyDto orderVerifyDto) {
        Boolean verifyStatus = orderVerifyDto.getVerifyStatus();
        Objects.requireNonNull(verifyStatus, "无法确定是否审核成功？");
        if (verifyStatus.equals(Boolean.TRUE)) {
            return HttpResult.success(orderService.passVerify(orderVerifyDto.getOrderId(), orderVerifyDto.getUserId()));
        } else {
            return HttpResult.success(orderService.rejectVerify(orderVerifyDto.getOrderId(), orderVerifyDto.getUserId(), orderVerifyDto.getRejectReason()));
        }
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "订单页面顶部统计")
    public HttpResult<OrderStatisticsVo> getOrderStatistics() {
        return HttpResult.success(orderService.getOrderStatistics());
    }

}
