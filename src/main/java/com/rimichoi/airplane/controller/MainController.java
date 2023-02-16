package com.rimichoi.airplane.controller;

import com.rimichoi.airplane.dto.MarketType;
import com.rimichoi.airplane.service.GmarketService;
import com.rimichoi.airplane.service.SearchPriceService;
import com.rimichoi.airplane.service.WemakepriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final GmarketService gmarketService;
    private final WemakepriceService wemakepriceService;

    @GetMapping("/price")
    public String getPrice(MarketType marketType) {
        return getSearchPriceService(marketType).run();
    }

    @GetMapping("/targetPrice")
    public Long getTargetPrice(MarketType marketType) {
        log.info("[getTargetPrice] marketType : {}", marketType);
        return getSearchPriceService(marketType).getConfig().getTargetPrice();
    }

    @PostMapping("/targetPrice")
    public Long changeTargetPrice(MarketType marketType, Long targetPrice) {
        log.info("[changeTargetPrice] marketType : {}, targetPrice : {}", marketType, targetPrice);
        getSearchPriceService(marketType).getConfig().setTargetPrice(targetPrice);
        return targetPrice;
    }

    @SuppressWarnings("rawtypes")
    private SearchPriceService getSearchPriceService(MarketType marketType) {
        switch (marketType) {
            case GMARKET:
                return gmarketService;
            case WEMAKEPRICE:
                return wemakepriceService;
            default:
                throw new RuntimeException("unknown market type");
        }
    }
}
