package com.springboot.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
public class SpringbootPortfolioApplicationTests {
    
    private static final String DRIVER = "org.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://codedot.co.kr:3306/test_user_01?characterEncoding=UTF-8";
    private static final String USER = "test_user_01";
    private static final String PW = "dl945pa";
    
    @Test
    public void contextLoads() throws Exception {
        System.out.println(USER + " : 테스트 데이터베이스 연결중...");
        Class.forName(DRIVER);
        try (Connection getcon = DriverManager.getConnection(URL, USER, PW)) {
            System.out.println(USER + " : 테스트 데이터베이스 연결성공");
        } catch (Exception e) {
            System.err.println(USER + " : 테스트 데이터베이스 연결실패");
        }
    }
    
}