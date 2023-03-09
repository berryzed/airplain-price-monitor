package com.rimichoi.airplane.service;

import com.rimichoi.airplane.config.AppProperties;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
@Service
public class WemakepriceService extends SearchPriceService<AppProperties.Wemakeprice> {

    private final AppProperties appProperties;
    private final MessageService messageService;

    @Override
    public long getLowestPrice() {
        long price = Integer.MAX_VALUE;

        try {
            String searchId = generateSearchId();
            price = loadPriceObject(searchId, price);
            price -= 150_000;
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            log.error("조회 실패 ! ", e);
        }

        return price;
    }

    private long loadPriceObject(String searchId, long price) throws ExecutionException, InterruptedException, TimeoutException {
        TimeUnit.SECONDS.sleep(1);
        JSONObject jsonObject = Unirest.get(getConfig().getLoadUrl())
                .queryString("searchId", searchId)
                .asJson()
                .getBody()
                .getObject();

        if (!jsonObject.has("prices")) {
            return loadPriceObject(searchId, price);
        }

        if (!jsonObject.getJSONObject("prices").has("minPrice")) {
            return loadPriceObject(searchId, price);
        }

        long minPrice = jsonObject.getJSONObject("prices").getLong("minPrice");
        if (minPrice == 0) {
            return loadPriceObject(searchId, price);
        }

        if (!jsonObject.getBoolean("complete")) {
            return loadPriceObject(searchId, price);
        }

        return Math.min(price, minPrice);
    }

    private String generateSearchId() throws ExecutionException, InterruptedException, TimeoutException {
        return Unirest.get(getConfig().getSearchUrl() + getConfig().getSearchParam())
                .header(HttpHeaders.REFERER, getConfig().getRefererUrl())
                .asJson()
                .getBody()
                .getObject()
                .getString("searchId");
    }

    @SuppressWarnings("unchecked")
    @Override
    public AppProperties.Wemakeprice getConfig() {
        return appProperties.getSearch().getWemakeprice();
    }

    @Override
    protected MessageService getMessageService() {
        return messageService;
    }
}
