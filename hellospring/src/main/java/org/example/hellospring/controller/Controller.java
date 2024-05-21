package org.example.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String hello(){
        return "hello!";
    }

    @GetMapping("/hi")
    public String hi(){
        return "hii!";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye!";
    }
}
