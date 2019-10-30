package com.order.webservice.service.product;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.webservice.common.converter.CommonConverter;
import com.order.webservice.domain.po.product.Product;
import com.order.webservice.domain.vo.PageResponseVo;
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
     * 分页获取商品（默认排序最新商品)
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResponseVo<ProductVo> getProductPage(Integer page, Integer size) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        IPage<Product> pages = productDao.selectPage(new Page<>(page, size), queryWrapper);

        PageResponseVo<ProductVo> ans = new PageResponseVo<>();
        ans.setNumber(pages.getCurrent());
        ans.setSize(pages.getSize());
        ans.setTotalElements(pages.getTotal());
        ans.setTotalPages(pages.getPages());
        ans.setContent(CommonConverter.convertList(pages.getRecords(), this::product2Vo));

        return ans;
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @Override
    public List<ProductVo> getProduct() {
        List<Product> products = productDao.selectList(new QueryWrapper<Product>().orderByDesc("create_time"));
        return CommonConverter.convertList(products, this::product2Vo);
    }


    private ProductVo product2Vo(Product product) {
        if (product == null) return null;
        ProductVo ans = new ProductVo();
        BeanUtils.copyProperties(product, ans);
        return ans;
    }
}
