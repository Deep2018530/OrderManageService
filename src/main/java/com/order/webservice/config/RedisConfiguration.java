package com.order.webservice.config;

import org.springframework.beans.factory.annotation.Value;


/**
 * created by zhangdingping on 2019/10/27
 */

public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

   /* @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setPort(redisPort);
        return new LettuceConnectionFactory(configuration);
    }*/
}
