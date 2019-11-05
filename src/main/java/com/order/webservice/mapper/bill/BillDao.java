package com.order.webservice.mapper.bill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.bill.Bill;
import com.order.webservice.domain.po.bill.BillDetail;

import java.time.LocalDateTime;

public interface BillDao extends BaseMapper<Bill> {

    BillDetail getBillDetailInfo(Long userId, String month);
}
