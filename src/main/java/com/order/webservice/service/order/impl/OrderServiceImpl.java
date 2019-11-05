package com.order.webservice.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.webservice.common.converter.CommonConverter;
import com.order.webservice.common.utils.OrderIdFactory;
import com.order.webservice.domain.dto.order.OrderDto;
import com.order.webservice.domain.enums.BillType;
import com.order.webservice.domain.enums.OrderStatus;
import com.order.webservice.domain.po.account.Account;
import com.order.webservice.domain.po.order.Order;
import com.order.webservice.domain.po.order.OrderDetail;
import com.order.webservice.domain.po.product.Product;
import com.order.webservice.domain.po.user.User;
import com.order.webservice.domain.vo.PageResponseVo;
import com.order.webservice.domain.vo.order.*;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.mapper.account.AccountDao;
import com.order.webservice.mapper.order.OrderDao;
import com.order.webservice.mapper.order.OrderDetailDao;
import com.order.webservice.mapper.product.ProductDao;
import com.order.webservice.mapper.user.UserDao;
import com.order.webservice.service.bill.BillService;
import com.order.webservice.service.order.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
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
    private OrderStatus orderStatus;

    @Autowired
    private OrderIdFactory orderIdFactory;

    @Autowired
    private BillService billService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PageResponseVo<OrderVo> query(Integer page, Integer size, OrderDto orderDto) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        if (orderDto.getId() != null) {
            queryWrapper.eq("id", orderDto.getId());
        }
        if (StringUtils.isNotEmpty(orderDto.getNickName()) && orderDto.getId() == null) {
            User userForId = userDao.selectOneByName(orderDto.getNickName());
            queryWrapper.eq("id", userForId.getId());
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
        if (StringUtils.isEmpty(orderDto.getNickName())) {
            for (int i = 0; i < (int) pages.getTotal(); i++) {
                User userForName = userDao.selectOneById(pages.getRecords().get(i).getUser_id());
                pages.getRecords().get(i).setNick_name(userForName.getNickName());
            }
        }

        PageResponseVo<OrderVo> ans = new PageResponseVo<>();
        ans.setNumber(pages.getCurrent());
        ans.setSize(pages.getSize());
        ans.setTotalElements(pages.getTotal());
        ans.setTotalPages(pages.getPages());
        ans.setContent(CommonConverter.convertList(pages.getRecords(), this::order2Vo));
        return ans;
    }

    public PageResponseVo<OrderVo> productQueryOrder(Integer page, Integer size, String productName) {
        if (StringUtils.isNotEmpty(productName)) {
            List<Order> order = orderDao.selectListById((page - 1) * size, size, productName);
            PageResponseVo<OrderVo> ans = new PageResponseVo<>();
            Long totalCount = order.get(0).getTotal_elements();
            ans.setNumber(page);
            ans.setSize(size);
            ans.setTotalElements(totalCount);
            ans.setTotalPages(totalCount % size == 0 ? totalCount / size : totalCount / size + 1);
            ans.setContent(CommonConverter.convertList(order, this::order2Vo));
            return ans;
        } else {
            return query(page, size, null);
        }

    }

    public List<String> state() {
        return orderStatus.getAllDescription();
    }

    public OrderRefundVo refundQueryOrder(Integer orderId) {
        return orderDao.selectOneById(orderId);
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
            accountDao.updateById(account);

            Long userIdLong = Long.parseLong(userId.toString());
            //生成账单
            billService.createBill(productId, balance, price, userIdLong, BillType.BUY);

            return createOrder(userIdLong, product);
        } else {
            throw new RuntimeException("余额不足！");
        }
    }


    /**
     * 修改订单状态
     *
     * @param orderStatus 订单状态
     * @return 是否修改成功
     */
    @Override
    public Boolean updateOrderStatus(BigInteger orderId, OrderStatus orderStatus) {
        Order order = orderDao.selectById(orderId);
        Objects.requireNonNull(order, "订单不存在！订单号:" + orderId);

        order.setStatus(orderStatus.getDescription());
        orderDao.updateById(order);
        return true;
    }

    /**
     * 审核通过
     *
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean passVerify(BigInteger orderId, Long userId) {
        //修改订单状态
        Order order = orderDao.selectById(orderId);
        Objects.requireNonNull(order, "订单不存在！订单号：" + orderId);

        order.setStatus(OrderStatus.PASS_FOR_REFUND.getDescription());
        orderDao.updateById(order);

        // 退款
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Account account = accountDao.selectOne(queryWrapper);
        Double amount = order.getAmount();
        Float balance = account.getBalance();
        Objects.requireNonNull(amount, "订单金额异常！amount is null");
        Objects.requireNonNull(balance, "账户余额异常！balance is null");
        account.setBalance((float) (balance + amount));
        Float totalConsumption = account.getTotalConsumption();
        Objects.requireNonNull(totalConsumption, "账户总消费记录异常！totalConsumption is null");
        account.setTotalConsumption((float) (totalConsumption - amount));
        accountDao.updateById(account);

        QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
        orderDetailQueryWrapper.eq("order_id", order.getId());
        OrderDetail orderDetail = orderDetailDao.selectOne(orderDetailQueryWrapper);
        billService.createBill(orderDetail.getProductId(), account.getBalance(), orderDetail.getAmount(), userId, BillType.REFUND);

        return true;
    }

    /**
     * 审核拒绝
     *
     * @param orderId
     * @param userId
     * @param rejectReason
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Boolean rejectVerify(BigInteger orderId, Long userId, String rejectReason) {
        // 修改订单状态
        Order order = orderDao.selectById(orderId);
        Objects.requireNonNull(order, "订单不存在！订单号：" + orderId);

        order.setStatus(OrderStatus.REFUSED_FOR_REFUND.getDescription());
        order.setVerifyRejectReason(rejectReason);
        return true;
    }

    /**
     * 订单顶部根据状态统计
     *
     * @return
     */
    @Override
    public OrderStatisticsVo getOrderStatistics() {

        OrderStatisticsVo ans = new OrderStatisticsVo();

        QueryWrapper<Order> verifyQueryWrapper = new QueryWrapper<>();
        verifyQueryWrapper.in("status", OrderStatus.PASS_FOR_REFUND.getDescription(), OrderStatus.REFUSED_FOR_REFUND.getDescription());
        ans.setHasVerify(orderDao.selectCount(verifyQueryWrapper));

        QueryWrapper<Order> notVerifyQueryWrapper = new QueryWrapper<>();
        notVerifyQueryWrapper.eq("status", OrderStatus.APPLY_FOR_REFUND.getDescription());
        ans.setNotVerify(orderDao.selectCount(notVerifyQueryWrapper));
        return ans;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public OrderNewVo createOrder(Long userId, Product product) {
        Order order = new Order();
        order.setAmount(Double.parseDouble(product.getPrice().toString()));
        order.setId(orderIdFactory.createId("order"));
        order.setStatus(OrderStatus.FINISHED.getDescription());
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
        User user = userDao.findById(userId);
        Objects.requireNonNull(user, UserErrorCode.USER_NOT_EXIST.getMessage());
        vo.setUserName(user.getNickName());

        OrderDetailNewVo orderDetailNewVo = new OrderDetailNewVo();
        BeanUtils.copyProperties(orderDetail, orderDetailNewVo);
        orderDetailNewVo.setProductName(product.getName());
        orderDetailNewVo.setDetail(product.getDetail());
        orderDetailNewVo.setMoreDetail(product.getMoreDetail());
        orderDetailNewVo.setPrice(product.getPrice());
        orderDetailNewVo.setCreateTime(orderDetail.getCreateTime());
        vo.setOrderDetailNewVoList(Arrays.asList(orderDetailNewVo));

        vo.setCreateTime(order.getCreate_time());
        return vo;
    }

    private OrderVo order2Vo(Order order) {
        if (order == null) return null;
        OrderVo ans = new OrderVo();
        BeanUtils.copyProperties(order, ans);
        return ans;
    }

}
