package com.bootdo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        return executor;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {


                //设置允许跨域的路径
                registry.addMapping("/**")
                        //设置允许跨域请求的域名
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowCredentials(true)//是否允许证书 不再默认开启
                        //设置允许的方法
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(36000);//跨域允许时间

            }
        };
    }
}