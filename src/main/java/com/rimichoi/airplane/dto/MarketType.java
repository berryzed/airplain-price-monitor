package com.rimichoi.airplane.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MarketType {

    GMARKET("지마켓"),
    WEMAKEPRICE("위메프");

    private final String name;
}
