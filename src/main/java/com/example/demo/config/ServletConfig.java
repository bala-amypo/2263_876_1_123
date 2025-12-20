package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<HelloServlet> helloServlet() {
        return new ServletRegistrationBean<>(new HelloServlet(), "/hello-servlet");
    }
}
