package co.develhope.apiInterceptormiddleware02exercise.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping
    public String welcomeUser(){
        return "Welcome :)";
    }
}
