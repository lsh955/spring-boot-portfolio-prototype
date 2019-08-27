package dev.springboot_portfolio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// DB연결을 하지않을 때 아래의 어노테이션을 적용한다.
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}
@MapperScan(basePackages = "dev.springboot_portfolio")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringbootPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPortfolioApplication.class, args);
    }

}
