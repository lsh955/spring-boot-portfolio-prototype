package com.springboot_portfolio.service;

import com.springboot_portfolio.dao.TestDao;
import com.springboot_portfolio.dto.TestVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 *
 * 비즈니스 혹은 사용자 로직을 구현한 클래스. 비즈니스와 연관있는 로직을 표현.
 */
@Service
public class TestService {
    
    // TestMapper interface를 Autowired 걸고
    TestDao testMapper;
    
    // 그 안에 getAll()함수를 Call한다.
    public List<TestVo> getAll() throws Exception {
        return testMapper.getAll();
    }
    
}
