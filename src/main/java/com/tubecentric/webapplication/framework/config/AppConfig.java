package com.tubecentric.webapplication.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private YouTube youTube;
    private JWT jwt;

    @Data
    public static class YouTube {

        @NotBlank
        private String key;

        @NotBlank
        private String baseUrl;
    }

    @Data
    public static class JWT {

        @NotBlank
        private Integer expiresInDays;

        @NotBlank
        private String key;
    }
}
