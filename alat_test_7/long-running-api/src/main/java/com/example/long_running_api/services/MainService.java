package com.example.long_running_api.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MainService {

    @Async("asyncTaskExecutor")
    public void task1() throws InterruptedException {
        Thread.sleep(5000);
        log.info("task 1 completed");
    }

    @Async("asyncTaskExecutor")
    public void task2() throws InterruptedException {
        Thread.sleep(3000);
        log.info("task 2 completed");
    }

    @Async("asyncTaskExecutor")
    public void task3() throws InterruptedException {
        Thread.sleep(1000);
        log.info("task 3 completed");
    }
}
