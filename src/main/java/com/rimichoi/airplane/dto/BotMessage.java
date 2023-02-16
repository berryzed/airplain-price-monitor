package com.rimichoi.airplane.dto;

public class BotMessage {

    private String chat_id;
    private String text;
    private final boolean disable_web_page_preview = true;
    private final String parse_mode = "HTML";

    public BotMessage(String chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }
}
