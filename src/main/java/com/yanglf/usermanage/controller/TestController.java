package com.yanglf.usermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping
@Controller
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("/firstPage");
    }

}
