package com.order.webservice.service.bill;

import com.order.webservice.domain.vo.BillVo.BillVo;

import java.time.LocalDateTime;

public interface BillService {
    BillVo getBillDetailInfo(Long userId, String month);
}
