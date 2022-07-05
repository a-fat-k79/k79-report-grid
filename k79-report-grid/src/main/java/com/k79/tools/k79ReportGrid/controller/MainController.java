package com.k79.tools.k79ReportGrid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

}
