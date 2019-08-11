package com.testtask.shoppinglistservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {




    @GetMapping("/")
    public String list() {
        return "index";
    }



}
