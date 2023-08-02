package com.wmpn.config;

import com.wmpn.filter.LoginCheckFilter;
import com.wmpn.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class ServerletConfig {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TokenUtils tokenUtils;

    @Bean
    public FilterRegistrationBean  filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LoginCheckFilter loginCheckFilter =new LoginCheckFilter();
        loginCheckFilter.setRedisTemplate(redisTemplate);
        loginCheckFilter.setTokenUtils(tokenUtils);
        filterRegistrationBean.setFilter(loginCheckFilter);

        filterRegistrationBean.addUrlPatterns("/*");
        return  filterRegistrationBean;
    }
}
