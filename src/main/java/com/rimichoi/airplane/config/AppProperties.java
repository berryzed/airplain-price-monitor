package com.rimichoi.airplane.config;

import com.rimichoi.airplane.dto.MarketType;
import com.rimichoi.airplane.dto.Market;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties("app")
public class AppProperties {

    @NestedConfigurationProperty
    private Telegram telegram;

    @NestedConfigurationProperty
    private Search search;

    @Validated
    @Getter
    @Setter
    static public class Telegram {
        private String chatId;
        private String token;
    }

    @Validated
    @Getter
    @Setter
    static public class Search {

        @NestedConfigurationProperty
        private Gmarket gmarket;

        @NestedConfigurationProperty
        private Wemakeprice wemakeprice;
    }

    @Validated
    @Getter
    @Setter
    static public class Gmarket implements Market {
        private MarketType type;
        private String searchParam;
        private String searchUrl;
        private String refererUrl;
        private String refererParam;
        private long targetPrice;

        @Override
        public String getButtonLinkUrl() {
            return refererUrl;
        }
    }

    @Validated
    @Getter
    @Setter
    static public class Wemakeprice implements Market {
        private MarketType type;
        private String searchParam;
        private String searchUrl;
        private String loadUrl;
        private String refererUrl;
        private String linkUrl;
        private String linkParam;
        private long targetPrice;

        @Override
        public String getButtonLinkUrl() {
            return linkUrl + linkParam;
        }
    }
}
