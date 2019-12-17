package com.springboot_portfolio;

import com.springboot_portfolio.Handler.SessionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSessionListener;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@SpringBootApplication
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// DB연결을 하지않을 때 아래의 어노테이션을 적용한다.
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}
public class SpringbootPortfolioApplication extends SpringBootServletInitializer {
    
    // 어플리케이션을 WAR 형태로 배포하기위해 아래와 같은 메인코드를 작성한다.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootPortfolioApplication.class);
    }
    
    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionHandler();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPortfolioApplication.class, args);
    }
    
}