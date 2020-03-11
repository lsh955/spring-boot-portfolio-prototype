package com.springboot.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
public class DatabaseConnectionTest {
    
    private static final String TESTSERVER_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String TESTSERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/test_user_01?characterEncoding=UTF-8";
    private static final String TESTSERVER_USER = "test_user_01";
    private static final String TESTSERVER_PW = "dl945pa";
    
    private static final String MAINSERVER_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String MAINSERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/portfolio?characterEncoding=UTF-8";
    private static final String MAINSERVER_USER = "portfolio";
    private static final String MAINSERVER_PW = "tmdghks05)%";
    
    @Test
    public void TestServerConnection() throws Exception {
        System.out.println(TESTSERVER_USER + " : 테스트서버 데이터베이스 연결중...");
        Class.forName(TESTSERVER_DRIVER);
        try (Connection TestServer_Con = DriverManager.getConnection(TESTSERVER_URL, TESTSERVER_USER, TESTSERVER_PW)) {
            System.out.println(TESTSERVER_USER + " : 테스트서버 데이터베이스 연결성공("+TestServer_Con+")");
        } catch (Exception e) {
            System.err.println(TESTSERVER_USER + " : 테스트서버 데이터베이스 연결실패");
        }
    }
    
    @Test
    public void MainServerConnection() throws Exception {
        System.out.println(MAINSERVER_USER + " : 메인서버 데이터베이스 연결중...");
        Class.forName(MAINSERVER_DRIVER);
        try (Connection MainServer_Con = DriverManager.getConnection(MAINSERVER_URL, MAINSERVER_USER, MAINSERVER_PW)) {
            System.out.println(MAINSERVER_USER + " : 메인서버 데이터베이스 연결성공("+MainServer_Con+")");
        } catch (Exception e) {
            System.err.println(MAINSERVER_USER + " : 메인서버 데이터베이스 연결실패");
        }
    }
    
}