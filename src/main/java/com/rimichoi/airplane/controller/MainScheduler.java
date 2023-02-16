package com.rimichoi.airplane.controller;

import com.rimichoi.airplane.service.GmarketService;
import com.rimichoi.airplane.service.WemakepriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainScheduler {

    private final GmarketService gmarketService;
    private final WemakepriceService wemakepriceService;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 30)
    public void gmarketJob() {
        gmarketService.run();
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 30)
    public void wemakepriceJob() {
        wemakepriceService.run();
    }
}
