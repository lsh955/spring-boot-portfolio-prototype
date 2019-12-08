package com.springboot_portfolio.controller;

import com.springboot_portfolio.domain.UserPrincipal;
import com.springboot_portfolio.service.UserService;
import com.springboot_portfolio.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "login"})
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult.rejectValue("loginId", "error.loginId", "There is already a user registered with the loginId provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        System.out.println(userPrincipal.toString());
        modelAndView.addObject("userName", "Welcome " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("exception")
    public ModelAndView getUserPermissionExceptionPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("access-denied");
        return mv;
    }

}
