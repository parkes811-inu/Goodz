package com.springproject.goodz.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import groovy.util.logging.Slf4j;


@Slf4j
@Controller
public class MainController {

 
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page) {
        return "/" + page;
    }


    // @GetMapping("/{domain}/{page}")
    // public String page(@PathVariable("domain") String domain
    //                   ,@PathVariable("page") String page  ) {
    //     return domain + "/" + page;
    // }



    
    
    
}
