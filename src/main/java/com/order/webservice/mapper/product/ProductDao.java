package com.order.webservice.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.product.Product;
import com.order.webservice.domain.po.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by zhangdingping at 2019/10/29
 */
public interface ProductDao extends BaseMapper<Product> {

    @Select("SELECT * FROM product ORDER BY create_time DESC LIMIT #{num} ")
    List<Product> selectListLimit(@Param("num") Integer num);
}
