package com.shoppingwebapp.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingwebapp.Component.AccessLogFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AccessLogFilter> registerFilter() {
        FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessLogFilter());
        registrationBean.addUrlPatterns("/**"); 
        return registrationBean;
    }
}