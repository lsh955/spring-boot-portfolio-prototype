package com.springboot_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// DB연결을 하지않을 때 아래의 어노테이션을 적용한다.
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}
public class SpringbootPortfolioApplication{
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPortfolioApplication.class, args);
    }
    
}