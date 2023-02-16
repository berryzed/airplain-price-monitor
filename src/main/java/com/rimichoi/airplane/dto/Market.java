package com.rimichoi.airplane.dto;

public interface Market {

    MarketType getType();

    long getTargetPrice();

    void setTargetPrice(long targetPrice);

    String getButtonLinkUrl();
}
