package com.springboot.portfolio.connection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
public class DatabaseTest {
    
    private static final String TEST_SERVER_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String TEST_SERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/test_user_01?characterEncoding=UTF-8";
    private static final String TEST_SERVER_USER = "test_user_01";
    private static final String TEST_SERVER_PW = "dl945pa";
    
    private static final String MAIN_SERVER_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String MAIN_SERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/portfolio?characterEncoding=UTF-8";
    private static final String MAIN_SERVER_USER = "portfolio";
    private static final String MAIN_SERVER_PW = "tmdghks05)%";
    
    @Test
    public void TestServerConnection() throws Exception {
        System.out.println(TEST_SERVER_USER + " : 테스트서버 데이터베이스 연결중...");
        Class.forName(TEST_SERVER_DRIVER);
        try (Connection TestServer_Con = DriverManager.getConnection(TEST_SERVER_URL, TEST_SERVER_USER, TEST_SERVER_PW)) {
            System.out.println(TEST_SERVER_USER + " : 테스트서버 데이터베이스 연결성공(" + TestServer_Con + ")");
        } catch (Exception e) {
            System.err.println(TEST_SERVER_USER + " : 테스트서버 데이터베이스 연결실패");
        }
    }
    
    @Test
    public void MainServerConnection() throws Exception {
        System.out.println(MAIN_SERVER_USER + " : 메인서버 데이터베이스 연결중...");
        Class.forName(MAIN_SERVER_DRIVER);
        try (Connection MainServer_Con = DriverManager.getConnection(MAIN_SERVER_URL, MAIN_SERVER_USER, MAIN_SERVER_PW)) {
            System.out.println(MAIN_SERVER_USER + " : 메인서버 데이터베이스 연결성공(" + MainServer_Con + ")");
        } catch (Exception e) {
            System.err.println(MAIN_SERVER_USER + " : 메인서버 데이터베이스 연결실패");
        }
    }
    
}