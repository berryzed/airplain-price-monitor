package com.rimichoi.airplane.service;

import com.rimichoi.airplane.config.AppProperties;
import kong.unirest.ContentType;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GmarketService extends SearchPriceService<AppProperties.Gmarket> {

    private static final String PARAM_KEY_JSON_SEARCH = "json_search";
    private static final String PARAM_KEY_SORT_KEY = "sort_key";
    private static final String PARAM_KEY_FILTER_DATA = "filter_data";

    private final MessageService messageService;
    private final AppProperties appProperties;

    @Override
    protected long getLowestPrice() {
        String price = Unirest.post(getConfig().getSearchUrl())
                .header(HttpHeaders.REFERER, getConfig().getRefererUrl() + getConfig().getRefererParam())
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                .field(PARAM_KEY_JSON_SEARCH, getConfig().getSearchParam())
                .field(PARAM_KEY_SORT_KEY, "HNSASC^DSFASC")
                .field(PARAM_KEY_FILTER_DATA, "{}")
                .asJson()
                .getBody()
                .getObject()
                .getJSONArray("list")
                .getJSONObject(0)
                .getJSONArray("FARELIST")
                .getJSONObject(0)
                .getString("DCTSF");

        return Long.parseUnsignedLong(price.replaceAll("[^\\d]", ""));
    }

    @SuppressWarnings("unchecked")
    @Override
    public AppProperties.Gmarket getConfig() {
        return appProperties.getSearch().getGmarket();
    }

    @Override
    protected MessageService getMessageService() {
        return messageService;
    }
}
