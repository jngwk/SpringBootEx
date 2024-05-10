package com.conan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class TestContoller {
    @GetMapping("/test")
    public String test() {
        log.info("test method called...........");
        return "Hello, World";
    }

}
