package com.order.webservice.service.product;

import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.product.ProductVo;

import java.util.List;

/**
 * created by zhangdingping at 2019/10/29
 */
public interface ProductService {

    /**
     * 分页获取商品（默认排序最新商品)
     *
     * @param page
     * @param size
     * @return
     */
    PageResponseVo<ProductVo> getProductPage(Integer page, Integer size);

    /**
     * 查询所有商品
     * @return
     */
    List<ProductVo> getProduct();
}
