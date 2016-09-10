package com.snow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mvcController {

    @RequestMapping("/hello")
    public String hello(){        
        return "login";
    }
}
