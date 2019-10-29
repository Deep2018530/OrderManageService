package com.order.webservice.service.product;

import com.order.webservice.domain.vo.product.ProductVo;

import java.util.List;

/**
 * created by zhangdingping at 2019/10/29
 */
public interface ProductService {

    List<ProductVo> getProductIndex(Integer num);
}
