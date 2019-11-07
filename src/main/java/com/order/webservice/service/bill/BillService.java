package com.order.webservice.service.bill;

import com.order.webservice.domain.enums.BillType;
import com.order.webservice.domain.vo.BillVo.BillVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface BillService {
    BillVo getBillInfo(Object userId, String date);


    /**
     * 生成账单信息（包括账单明细)
     *
     * @param productId
     * @param balance
     * @param price
     * @param userId
     * @param billType
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void createBill(Long productId, Float balance, Float price, Long userId, BillType billType);
}
