package com.tubecentric.webapplication.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private AdWords adWords;

    @Data
    public static class AdWords {

        @NotBlank
        private String clientId;

        @NotBlank
        private String clientSecret;

        @NotBlank
        private String developerToken;

        @NotBlank
        private String clientCustomerId;

        @NotBlank
        private String refreshToken;
    }
}
