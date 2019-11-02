package com.order.webservice.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.webservice.common.converter.CommonConverter;
import com.order.webservice.common.utils.OrderIdFactory;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.enums.OrderStatus;
import com.order.webservice.domain.po.account.Account;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.po.order.OrderDetail;
import com.order.webservice.domain.po.product.Product;
import com.order.webservice.domain.po.user.User;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.OrderDetailNewVo;
import com.order.webservice.domain.vo.order.OrderNewVo;
import com.order.webservice.domain.vo.order.OrderVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.mapper.account.AccountDao;
import com.order.webservice.mapper.order.OrderDao;
import com.order.webservice.mapper.order.OrderDetailDao;
import com.order.webservice.mapper.product.ProductDao;
import com.order.webservice.mapper.user.UserDao;
import com.order.webservice.service.order.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderIdFactory orderIdFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PageResponseVo<OrderVo> query(Integer page, Integer size, OrderDto orderDto) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        if (orderDto.getId() != null) {
            queryWrapper.eq("id", orderDto.getId());
        }
        if (orderDto.getUserId() != null) {
            queryWrapper.eq("user_id", orderDto.getUserId());
        }
        if (StringUtils.isNotEmpty(orderDto.getStatus())) {
            queryWrapper.eq("status", orderDto.getStatus());
        }
        if (orderDto.getAmountDown() != null) {
            queryWrapper.ge("amount", orderDto.getAmountDown());
        }
        if (orderDto.getAmountUp() != null) {
            queryWrapper.le("amount", orderDto.getAmountUp());
        }
        if (orderDto.getCreateTimeStart() != null) {
            queryWrapper.ge("create_time", orderDto.getCreateTimeStart());
        }
        if (orderDto.getCreateTimeEnd() != null) {
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

    /**
     * 购买商品→生成订单
     *
     * @param userId
     * @param productId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrderNewVo buyProduct(Object userId, Long productId) {

        Objects.requireNonNull(userId, "userId is null");
        Objects.requireNonNull(productId, "productId is null");

        QueryWrapper<Account> aqw = new QueryWrapper<>();
        aqw.eq("user_id", Long.parseLong(userId.toString()));
        Account account = accountDao.selectOne(aqw);

        Product product = productDao.selectById(productId);
        Objects.requireNonNull(product, "产品不存在或被禁用！");

        Float balance = account.getBalance();
        Float price = product.getPrice();
        Objects.requireNonNull(balance, "不能获取到余额！");
        Objects.requireNonNull(price, "不能获取到商品价格!");

        if (balance.compareTo(price) >= 0) {
            account.setBalance(balance - price);
            account.setTotalConsumption(price);

            createOrder(Long.parseLong(userId.toString()), product);
        } else {
            throw new RuntimeException("余额不足！");
        }

        //余额足够，生成订单 order order_detail

        //扣除余额，更新消费金额 account

        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public OrderNewVo createOrder(Long userId, Product product) {
        Order order = new Order();
        order.setAmount(Double.parseDouble(product.getPrice().toString()));
        order.setId(orderIdFactory.createOrderId());
        order.setStatus(OrderStatus.NOT_PAY.getDescription());
        order.setUser_id(userId);
        orderDao.insert(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setAmount(product.getPrice());
        orderDetail.setNum(Integer.valueOf(1));
        orderDetail.setProductId(product.getId());
        orderDetailDao.insert(orderDetail);

        OrderNewVo vo = new OrderNewVo();
        vo.setAmount(product.getPrice());
        vo.setId(order.getId());
        vo.setStatus(order.getStatus());
        vo.setUserId(userId);
        User user = userDao.selectById(userId);
        Objects.requireNonNull(user, UserErrorCode.USER_NOT_EXIST.getMessage());
        vo.setUserName(user.getNickName());

        OrderDetailNewVo orderDetailNewVo = new OrderDetailNewVo();
        BeanUtils.copyProperties(orderDetail, orderDetailNewVo);
        orderDetailNewVo.setProductName(product.getName());
        vo.setOrderDetailNewVoList(Arrays.asList(orderDetailNewVo));
        return vo;
    }

    private OrderVo order2Vo(Order order) {
        if (order == null) return null;
        OrderVo ans = new OrderVo();
        BeanUtils.copyProperties(order, ans);
        return ans;
    }

}
