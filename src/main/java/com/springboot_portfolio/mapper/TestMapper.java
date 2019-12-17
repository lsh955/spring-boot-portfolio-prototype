package com.springboot_portfolio.mapper;

import com.springboot_portfolio.dao.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@Mapper
public interface TestMapper {
    
    // 메소드이름은 mapper xml의 select태그의 id값
    public List<TestVo> getAll() throws Exception;
    
}
