package com.order.webservice.service.bill.impl;

import com.order.webservice.domain.po.bill.BillDetail;
import com.order.webservice.domain.vo.BillVo.BillVo;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.exception.bill.BillErrorCode;
import com.order.webservice.exception.bill.BillException;
import com.order.webservice.mapper.bill.BillDao;
import com.order.webservice.service.bill.BillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    BillDao billDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BillVo getBillDetailInfo(Long userId, String month) {
        BillDetail billDetail = billDao.getBillDetailInfo(userId,month);
        if (Objects.isNull(billDetail)) {
            throw new BillException(BillErrorCode.BILL_NOT_EXIST);
        }
        BillVo billVo = parseToBillVo(billDetail);
        return billVo;
    }

    private BillVo parseToBillVo(BillDetail billDetail){
        Objects.requireNonNull(billDetail);
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(billDetail, billVo);
        return billVo;

    }
}
