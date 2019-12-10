package com.springboot_portfolio.service;

import com.springboot_portfolio.mapper.TestMapper;
import com.springboot_portfolio.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@Service
public class TestService {
    
    // TestMapper interface를 Autowired 걸고
    TestMapper testMapper;
    
    // 그 안에 getAll()함수를 Call한다.
    public List<TestVo> getAll() throws Exception {
        return testMapper.getAll();
    }
    
}
