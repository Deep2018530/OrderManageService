package com.order.webservice.common.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by zhangdingping on 2019/11/2
 */

@Component
public class OrderIdFactory {

    /**
     * 服务重启就失效
     */
    private AtomicInteger incrOrder = new AtomicInteger(1);

    private AtomicInteger incrBill = new AtomicInteger(1);

    private AtomicInteger incrBillDetail = new AtomicInteger(1);


    public BigInteger createId(String type) {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        String s = now.toString();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                sb.append(c);
            }
        }
        int increment = 0;
        switch (type) {
            case "order":
                increment = incrOrder.getAndIncrement();
                break;
            case "bill":
                increment = incrBill.getAndIncrement();
                break;
            case "billDetail":
                increment = incrBillDetail.getAndIncrement();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(increment);
        int size = 6 - stringBuilder.length();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('0');
        }

        sb.append(stringBuilder.reverse());
        for (int i = 0; i < 5; i++) {
            sb.append(new Random().nextInt(9));
        }

        BigInteger bigInteger = new BigInteger(sb.toString());
        return bigInteger;
    }


}
