package com.order.webservice.controller.product;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.product.ProductVo;
import com.order.webservice.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by zhangdingping at 2019/10/29
 */
@RestController
@RequestMapping(value = "/product")
@Api(value = "商品维护接口")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping(value = {"/list/late/{page}/{size}", "/list/late"})
    @ApiOperation(value = "首页商品展示（根据数量）")
    public HttpResult<PageResponseVo<ProductVo>> getProduct(@ApiParam(value = "页数（第一页是1)") @PathVariable(value = "page", required = false) Integer page,
                                                            @ApiParam(value = "每页条数") @PathVariable(value = "size", required = false) Integer size) {

        return HttpResult.success(productService.getProductPage(page, size));

    }
}
