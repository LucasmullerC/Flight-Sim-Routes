package io.github.flightsimroutes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    
    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }
}
