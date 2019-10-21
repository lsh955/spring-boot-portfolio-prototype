package dev.springboot_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@MapperScan(basePackages = "dev.springboot_portfolio")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
// DB연결을 하지않을 때 아래의 어노테이션을 적용한다.
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}
public class SpringbootPortfolioApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
        return application.sources(SpringbootPortfolioApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPortfolioApplication.class, args);
        System.out.println("시작");
    }

}
