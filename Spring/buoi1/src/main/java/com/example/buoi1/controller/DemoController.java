package com.example.buoi1.controller;


import com.example.buoi1.entity.Demo;
import com.example.buoi1.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demos")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @PostMapping
    public void create(@RequestBody Demo demo) {
        demoService.create(demo);
    }
}
