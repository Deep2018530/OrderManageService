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
     *  服务重启就失效
     */
    private AtomicInteger incr = new AtomicInteger(1);

    public BigInteger createOrderId() {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        String s = now.toString();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                sb.append(c);
            }
        }

        int increment = incr.getAndIncrement();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(increment);
        int size = 8 - stringBuilder.length();
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
