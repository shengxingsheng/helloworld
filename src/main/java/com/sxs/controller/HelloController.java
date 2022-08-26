package com.sxs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxs
 * @create 2022-08-26 15:57
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        System.out.println("hello world4....");
        return "ok4";
    }
}
