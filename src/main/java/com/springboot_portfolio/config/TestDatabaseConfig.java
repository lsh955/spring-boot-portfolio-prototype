package com.springboot_portfolio.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@Configuration
@MapperScan(basePackages = "com.springboot_portfolio")   // 어떤 Mapperer들을 Scan할 것인가 정의...
@EnableTransactionManagement
public class TestDatabaseConfig {
    
    @Bean
    // @Bean sqlSessionFactory
    // DataSource 를 parameter로 받아, sqlSessionFactory를 생성하는 Bean.
    // 여기서 만들어진 기본정보, 설정값 등을 이용해서 SqlSessionTemplate를 만들게 되는 것.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        // .setMapperLocations()
        // 실제 쿼리문이 존재하는 xml파일들의 위치를 지정.(여기에서는 mybatis/mapper 폴더 하위에 모든 xml을 명시)
        // 해당 mapper xml들에 오류가 있는지 문법 체크까지 모두 한 뒤에 Factory가 생성되게 되는 것이므로,
        // 중간중간 오류가 발생하지는 않는지 꼭 확인을 해야한다.
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }
    
    @Bean
    // @Bean sqlSessionTemplate
    // 실제 DB접속에 이용되는 sqlSessionTemplate를 생성하여 Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
    
}
