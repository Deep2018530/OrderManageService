package com.order.webservice.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.po.user.User;
import org.apache.ibatis.annotations.Param;

public interface OrderDao extends BaseMapper<Order> {
    User selectOneById(@Param("id") Long id);

    User selectOneByName(@Param("nick_Name") String nick_Name);
}
