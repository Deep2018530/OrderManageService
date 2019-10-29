package com.order.webservice.service.product;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.order.webservice.common.converter.CommonConverter;
import com.order.webservice.domain.po.product.Product;
import com.order.webservice.domain.vo.product.ProductVo;
import com.order.webservice.mapper.product.ProductDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/29
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    /**
     * 根据数量获取最新创建的商品
     *
     * @param num
     * @return
     */
    @Override
    public List<ProductVo> getProductIndex(Integer num) {
        if (Objects.isNull(num)) num = Integer.valueOf(4);
        List<Product> products = productDao.selectListLimit(num);
        return CommonConverter.convertList(products, product -> product2Vo(product));
    }


    private ProductVo product2Vo(Product product) {
        if (product == null) return null;
        ProductVo ans = new ProductVo();
        BeanUtils.copyProperties(product, ans);
        return ans;
    }
}
