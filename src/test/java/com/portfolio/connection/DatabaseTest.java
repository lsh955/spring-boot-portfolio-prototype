package com.portfolio.connection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

// @RunWith는 제이유닛 내장 실행기(runner) 대신 SpringJunit4ClassRunner.class 클래스를 참조하여 테스트를 실행(Junit의 BlockJunit4ClassRunner를 커스터마이징한 클래스로, 스프링 테스트 컨택스트 프레임워크의 모든 기능을 제공)
@SpringBootTest                 // 스프링 부트 기반의 테스트 클래스에 붙이는 어노테이션(속성을 추가해서 애플리케이션에 따라 설정을 다르게 할 수 있다)
public class DatabaseTest {

	private static final String TEST_SERVER_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String TEST_SERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/test_user_01?characterEncoding=UTF-8";
	private static final String TEST_SERVER_USER = "test_user_01";
	private static final String TEST_SERVER_PW = "dl945pa";

	private static final String MAIN_SERVER_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String MAIN_SERVER_URL = "jdbc:mariadb://codedot.co.kr:3306/portfolio?characterEncoding=UTF-8";
	private static final String MAIN_SERVER_USER = "portfolio";
	private static final String MAIN_SERVER_PW = "tmdghks05)%";

	/**
	 * 테스트계정 데이터베이스
	 */
	@Test
	public void TestServerConnection() throws Exception {
		System.out.println(TEST_SERVER_USER + " : 테스트계정 데이터베이스 연결중...");
		Class.forName(TEST_SERVER_DRIVER);
		try (Connection TestServer_Con = DriverManager.getConnection(TEST_SERVER_URL, TEST_SERVER_USER, TEST_SERVER_PW)) {
			System.out.println(TEST_SERVER_USER + " : 테스트계정 데이터베이스 연결성공(" + TestServer_Con + ")");
		} catch (Exception e) {
			System.err.println(TEST_SERVER_USER + " : 테스트계정 데이터베이스 연결실패");
		}
	}

	/**
	 * 메인계정 데이터베이스
	 */
	@Test
	public void MainServerConnection() throws Exception {
		System.out.println(MAIN_SERVER_USER + " : 메인계정 데이터베이스 연결중...");
		Class.forName(MAIN_SERVER_DRIVER);
		try (Connection MainServer_Con = DriverManager.getConnection(MAIN_SERVER_URL, MAIN_SERVER_USER, MAIN_SERVER_PW)) {
			System.out.println(MAIN_SERVER_USER + " : 메인계정 데이터베이스 연결성공(" + MainServer_Con + ")");
		} catch (Exception e) {
			System.err.println(MAIN_SERVER_USER + " : 메인계정 데이터베이스 연결실패");
		}
	}

}