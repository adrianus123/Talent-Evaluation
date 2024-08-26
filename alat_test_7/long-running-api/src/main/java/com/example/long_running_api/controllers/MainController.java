package com.example.long_running_api.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.long_running_api.services.MainService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/task")
@Slf4j
public class MainController {

    @Autowired
    MainService service;

    @GetMapping()
    public Map<String, Object> task() throws InterruptedException {
        service.task1();
        service.task2();
        service.task3();
        return Collections.<String, Object>singletonMap("message", "success");
    }
}