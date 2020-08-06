package com.ordinary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public Principal home(Principal principal) {
        return principal;
    }

}
