package com.springboot_portfolio.dao;

import com.springboot_portfolio.dto.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 *
 * DAO와 Service는 그 역할이 분명히 다르다
 * DAO는 단일 데이터 접근/갱신만 처리한다.
 * Service는 여러 DAO를 호출하여 여러번의 데이터 접근/갱신을 하며 그렇게 읽은 데이터에 대한 비즈니스 로직을 수행하고,
 * 그것을 하나의(혹은 여러개의) 트랜잭션으로 묶는다.
 * 즉, Service가 트랜잭션 단위이다.
 * 위와 같이 DAO와 Service가 완전히 동일해지는 경우도 분명히 발생합니다. 하지만 그것은 해당 비즈니스 로직이 '단일 DB 접근'으로 끝나기 때문에 발생하는 것이다.
 * 만약 DAO의 메소드 하나에 다중 DB접근 로직이 들어갔고, 서비스는 단순히 그 DAO메소드를 호출하는 통로 역할만 한다면 DAO측 모듈화가 제대로 안된 접근 방식일 가능성이 높습니다(항상 그렇다는 뜻은 아니다.)
 */
@Mapper
public interface TestDao {
    
    // 메소드이름은 mapper xml의 select태그의 id값
    public List<TestVo> getAll() throws Exception;
    
}
