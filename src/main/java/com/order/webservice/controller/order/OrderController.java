package com.order.webservice.controller.order;


import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderVo;
import com.order.webservice.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/order")
@Api(value = "订单查询API接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order{page}/{size}")
    @ApiOperation(value = "管理员订单展示")
    public HttpResult<PageResponseVo<OrderVo>> getOrderPage(@ApiParam(value = "第几页（第一页是1)")
                                                            @PathVariable(value = "page", required = false) Integer page,
                                                            @ApiParam(value = "每页条数")
                                                            @PathVariable(value = "size", required = false) Integer size) {
        return HttpResult.success(orderService.getOrderPage(page, size));
    }

    @GetMapping("/order/query{page}/{size}")
    @ApiOperation(value = "管理员订单查询")
    public HttpResult<PageResponseVo<OrderVo>> query(@ApiParam(value = "第几页（第一页是1)")
                                                     @PathVariable(value = "page", required = false) Integer page,
                                                     @ApiParam(value = "每页条数")
                                                     @PathVariable(value = "size", required = false) Integer size,
                                                     @ApiParam(value = "订单查询对象") @RequestBody OrderDto orderDto) {
        return HttpResult.success(orderService.query(page, size, orderDto));
    }

}
