package com.rimichoi.airplane.service;

import com.rimichoi.airplane.dto.Market;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SearchPriceService<T extends Market> {

    public String run() {
        logging("조회 시작");

        long price = getLowestPrice();
        String currentPrice = String.format("%,d", price);
        if (price < getConfig().getTargetPrice()) {
            logging("최저가 발견 ! : " + currentPrice);
            sendLowPrice(currentPrice);
        } else {
            logging("최저가 발견 실패 ㅠㅠ 현재 가격 : " + currentPrice);
        }

        logging("조회 끝");
        return currentPrice;
    }

    public void sendLowPrice(String price) {
        String msg = "[" + getConfig().getType().getName() + "] 최저가 발견 ! : " + price
                + "\n<a href=\"" + getConfig().getButtonLinkUrl() + "\" >보러가기</a>";
        if (getMessageService().sendMessage(msg)) {
            logging("발송 성공 !");
        } else {
            logging("발송 실패 ㅠ");
        }
    }

    abstract protected long getLowestPrice();

    abstract public <T extends Market> T getConfig();
    abstract protected MessageService getMessageService();

    protected void logging(String msg) {
        log.info("[{}] {}", getConfig().getType().getName(), msg);
    }
}
