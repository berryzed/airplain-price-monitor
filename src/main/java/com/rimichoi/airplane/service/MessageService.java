package com.rimichoi.airplane.service;

import com.rimichoi.airplane.config.AppProperties;
import com.rimichoi.airplane.dto.BotMessage;
import kong.unirest.ContentType;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private static final String URL_TELEGRAM = "https://api.telegram.org/bot{token}/sendMessage";
    private static final String PARAM_KEY_TOKEN = "token";

    private final AppProperties appProperties;

    private final JsonObjectMapper objectMapper = new JsonObjectMapper();

    public boolean sendMessage(String message) {
        return Unirest.post(URL_TELEGRAM)
                .routeParam(PARAM_KEY_TOKEN, appProperties.getTelegram().getToken())
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .body(objectMapper.writeValue(new BotMessage(appProperties.getTelegram().getChatId(), message)))
                .asEmpty()
                .isSuccess();
    }
}
