package com.order.webservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * created by zhangdingping at 2019/10/29
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.order.webservice.mapper")
public class MyBatisPlusConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最后页的操作，true调回到首页，false继续请求，默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认500条， -1 不受限制
        // paginationInterceptor.setLimit(500);

        return paginationInterceptor;
    }
}
