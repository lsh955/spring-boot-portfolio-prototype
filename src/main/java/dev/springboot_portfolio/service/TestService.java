package dev.springboot_portfolio.service;

import com.sun.tools.javac.util.List;
import dev.springboot_portfolio.dto.Test;
import dev.springboot_portfolio.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public List<Test> getAll() throws Exception{
        return testMapper.getAll();
    }

}
