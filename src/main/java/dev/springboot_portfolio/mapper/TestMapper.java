package dev.springboot_portfolio.mapper;

import com.sun.tools.javac.util.List;
import dev.springboot_portfolio.dto.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    public List<Test> getAll() throws Exception;

}