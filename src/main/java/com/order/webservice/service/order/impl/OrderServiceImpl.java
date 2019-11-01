package com.order.webservice.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.webservice.common.converter.CommonConverter;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderVo;
import com.order.webservice.mapper.order.OrderDao;
import com.order.webservice.service.order.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public PageResponseVo<OrderVo> getOrderPage(Integer page, Integer size) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        IPage<Order> pages = orderDao.selectPage(new Page<>(page, size), queryWrapper);

        PageResponseVo<OrderVo> ans = new PageResponseVo<>();
        ans.setNumber(pages.getCurrent());
        ans.setSize(pages.getSize());
        ans.setTotalElements(pages.getTotal());
        ans.setTotalPages(pages.getPages());
        ans.setContent(CommonConverter.convertList(pages.getRecords(), this::order2Vo));

        return ans;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PageResponseVo<OrderVo> query(Integer page, Integer size, OrderDto orderDto) {
        
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (!orderDto.getId().equals("") && orderDto.getId() != null) {
            queryWrapper.eq("id", orderDto.getId());
        }
        if (!orderDto.getUserId().equals("") && orderDto.getUserId() != null) {
            queryWrapper.eq("user_id", orderDto.getUserId());
        }
        if (!orderDto.getStatus().equals("") && orderDto.getStatus() != null) {
            queryWrapper.eq("status", orderDto.getStatus());
        }
        if (!orderDto.getAmountDown().equals("") && orderDto.getAmountDown() != null) {
            queryWrapper.ge("amount", orderDto.getAmountDown());
        }
        if (!orderDto.getAmountUp().equals("") && orderDto.getAmountUp() != null) {
            queryWrapper.le("amount", orderDto.getAmountUp());
        }
        if (!orderDto.getCreateTimeStart().equals("") && orderDto.getCreateTimeStart() != null) {
            queryWrapper.ge("create_time", orderDto.getCreateTimeStart());
        }
        if (!orderDto.getCreateTimeEnd().equals("") && orderDto.getCreateTimeEnd() != null) {
            queryWrapper.le("create_time", orderDto.getCreateTimeEnd());
        }
        queryWrapper.orderByDesc("create_time");
        IPage<Order> pages = orderDao.selectPage(new Page<>(page, size), queryWrapper);

        PageResponseVo<OrderVo> ans = new PageResponseVo<>();
        ans.setNumber(pages.getCurrent());
        ans.setSize(pages.getSize());
        ans.setTotalElements(pages.getTotal());
        ans.setTotalPages(pages.getPages());
        ans.setContent(CommonConverter.convertList(pages.getRecords(), this::order2Vo));
        return ans;
    }

    private OrderVo order2Vo(Order order) {
        if (order == null) return null;
        OrderVo ans = new OrderVo();
        BeanUtils.copyProperties(order, ans);
        return ans;
    }

}
