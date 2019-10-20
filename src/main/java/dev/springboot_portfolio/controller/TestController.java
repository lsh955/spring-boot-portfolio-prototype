package dev.springboot_portfolio.controller;

import com.sun.tools.javac.util.List;
import dev.springboot_portfolio.dto.Test;
import dev.springboot_portfolio.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    TestService testservice;

    @RequestMapping("/query")
    public @ResponseBody List<Test> query() throws Exception{
        return testservice.getAll();
    }
}
