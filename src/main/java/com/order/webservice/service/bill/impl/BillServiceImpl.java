package com.order.webservice.service.bill.impl;

import com.order.webservice.common.utils.OrderIdFactory;
import com.order.webservice.domain.enums.BillType;
import com.order.webservice.domain.po.bill.Bill;
import com.order.webservice.domain.po.bill.BillDetail;
import com.order.webservice.domain.vo.BillVo.BillDetailVo;
import com.order.webservice.domain.vo.BillVo.BillVo;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.exception.bill.BillErrorCode;
import com.order.webservice.exception.bill.BillException;
import com.order.webservice.mapper.bill.BillDao;
import com.order.webservice.mapper.bill.BillDetailDao;
import com.order.webservice.service.bill.BillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    BillDao billDao;

    @Autowired
    private BillDetailDao billDetailDao;

    @Autowired
    private OrderIdFactory orderIdFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BillVo getBillInfo(Object userId, String date) {
        Objects.requireNonNull(userId, "userId为空，请重新登录！");
        Objects.requireNonNull(date, "日期不能为空");
        float sumIncome = 0;
        float sumExpend = 0;
        String startDate = date + "-01 00:00:00";
        String endDate = date + "-31 23:59:59";
        BillVo monthBill = new BillVo();
        List monthList = new ArrayList();
        //查询账单
        List<Bill> billList = billDao.getBillInfo(userId, startDate, endDate);

        if (Objects.isNull(billList)) {
            throw new BillException(BillErrorCode.BILL_NOT_EXIST);
        } else {
            //遍历账单结果集
            for (int i = 0; i < billList.size(); i++) {
                Bill bill = billList.get(i);
                BigInteger id = bill.getId();
                //计算每月账单收入和支出
                if (bill.getIncome() == null) {
                    sumIncome = sumIncome + 0;
                } else {
                    sumIncome = sumIncome + bill.getIncome();
                }
                if (bill.getExpend() == null) {
                    sumExpend = sumExpend + 0;
                } else {
                    sumExpend = sumExpend + bill.getExpend();
                }
                BillVo billVo = parseToBillVo(bill);

                //查询账单明细
                List<BillDetail> billDetailList = billDao.getBillDetail(id, startDate, endDate);
                //遍历账单明细结果集
                List<BillDetailVo> list = new ArrayList<BillDetailVo>();
                if (billDetailList.size() > 0) {
                    for (int j = 0; j < billDetailList.size(); j++) {
                        BillDetail billDetail = billDetailList.get(j);
                        BillDetailVo billDetailVo = parseToBillDetailVo(billDetail);
                        list.add(billDetailVo);
                        billVo.setBillDetail(list);
                    }
                } else {
                    billVo.setBillDetail(null);
                }
                monthList.add(billVo);
            }
        }
        monthBill.setIncome(sumIncome);
        monthBill.setExpend(sumExpend);
        monthBill.setBillDetail(monthList);

        return monthBill;
    }

    private BillDetailVo parseToBillDetailVo(BillDetail billDetail) {
        Objects.requireNonNull(billDetail);
        BillDetailVo billDetailVo = new BillDetailVo();
        BeanUtils.copyProperties(billDetail, billDetailVo);
        return billDetailVo;
    }

    private BillVo parseToBillVo(Bill bill) {
        Objects.requireNonNull(bill);
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(bill, billVo);
        return billVo;

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void createBill(Long productId, Float balance, Float price, BigInteger userIdLong, BillType billType) {
        Bill bill = new Bill();
        switch (billType) {
            case BUY:
                bill.setExpend(price);
                break;
            case REFUND:
            case RECHARGE:
                bill.setIncome(price);
                break;
        }
        bill.setUserId(userIdLong);
        bill.setId(orderIdFactory.createId("bill"));
        billDao.insert(bill);

        BillDetail billDetail = new BillDetail();
        billDetail.setId(orderIdFactory.createId("billDetail"));
        billDetail.setBillId(bill.getId());
        billDetail.setBillType(billType.getDescription());
        billDetail.setProductId(productId);
        billDetail.setPrice(price);
        billDetail.setBalance(balance);
        billDetailDao.insert(billDetail);
    }
}
