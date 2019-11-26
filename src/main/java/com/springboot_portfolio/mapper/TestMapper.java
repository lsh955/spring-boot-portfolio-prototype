package com.springboot_portfolio.mapper;

import com.springboot_portfolio.vo.Test01;

import java.util.List;

public interface TestMapper {
    
    // 메소드이름은 Test-mapper.xml 파일의 select태그 id
    public List<Test01> getAll() throws Exception;
    
}
