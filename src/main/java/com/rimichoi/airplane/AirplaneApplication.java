package com.rimichoi.airplane;

import com.rimichoi.airplane.config.AppProperties;
import kong.unirest.Unirest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties(value = {AppProperties.class})
@EnableScheduling
@SpringBootApplication
public class AirplaneApplication {

    public static void main(String[] args) {
        Unirest.config()
                .socketTimeout(20_000)
                .connectTimeout(20_000);

        SpringApplication.run(AirplaneApplication.class, args);
    }
}
