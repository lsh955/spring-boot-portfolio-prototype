package com.springboot.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 설정 클래스 모음. (Web Security, Controller Advice 등)
 */
@Configuration  // @Configuration : Spring Boot를 사용하면서 필요한 설정
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * 스프링부트에서 리소스 핸들러 설정
     * addResourceHandlers는 리소스 등록 및 핸들러를 관리하는 객체인
     * ResourceHandlerRegistry를 통해 리소스 위치와 이 리소스와 매칭될 url을 등록한다.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/static/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/resources/static/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
    
}
