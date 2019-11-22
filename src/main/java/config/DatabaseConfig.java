package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configurable
@MapperScan(basePackages = "com.simplify.sample")   // 어떤 Mapperer들을 Scan할 것인가 정의...
@EnableTransactionManagement
public class DatabaseConfig {
    
    @Bean
    // @Bean sqlSessionFactory
    // DataSource 를 parameter로 받아, sqlSessionFactory를 생성하는 Bean.
    // 여기서 만들어진 기본정보, 설정값 등을 이용해서 SqlSessionTemplate를 만들게 되는 것.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        return sessionFactory.getObject();
    }
    
    @Bean
    // @Bean sqlSessionTemplate
    // 실제 DB접속에 이용되는 sqlSessionTemplate를 생성하여 Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws  Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
    
}
