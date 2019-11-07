package com.order.webservice.mapper.bill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.bill.Bill;
import com.order.webservice.domain.po.bill.BillDetail;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface BillDao extends BaseMapper<Bill> {

    List<Bill> getBillInfo(Object userId, String date);

    List<BillDetail> getBillDetail(BigInteger id, String date);
}
