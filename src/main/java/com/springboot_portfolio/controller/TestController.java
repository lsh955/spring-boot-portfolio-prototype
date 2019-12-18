package com.springboot_portfolio.controller;

import com.springboot_portfolio.service.TestService;
import com.springboot_portfolio.dto.TestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 이승환
 * @since 2019-11-26
 *
 * 컨트롤러 클래스 모음.
 */
@Controller
public class TestController {
    
    // Service를 @Autowired로 걸고
    TestService testService;

    // Call한 결과를 Return 한다.
    @RequestMapping("/testquery")
    public @ResponseBody List<TestVo> query() throws Exception {
        return testService.getAll();
    }

}
