package com.springboot.portfolio;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@SpringBootApplication
public class SpringbootPortfolioApplication extends SpringBootServletInitializer {
    
    private static final String PROPERTIES = "spring.config.location=classpath:/google.yml";    // google.yml은 별도로 관리하도록 설정
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootPortfolioApplication.class);   // 어플리케이션을 WAR 형태로 배포하기위해 아래와 같은 메인코드를 작성한다.
    }
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootPortfolioApplication.class).properties(PROPERTIES).run(args);
    }
}