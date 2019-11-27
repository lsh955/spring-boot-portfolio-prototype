package com.springboot_portfolio.mapper;

import com.springboot_portfolio.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 */
@Mapper
public interface TestMapper {
    
    // 메소드이름은 Test-mapper.xml 파일의 select태그 id
    public List<TestVo> getAll() throws Exception;
    
}
