package com.tubecentric.webapplication.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private JWT jwt;
    private YouTube youTube;

    @Data
    public static class JWT {

        private Cookie cookie;

        @NotBlank
        private Integer expiresInDays;

        @NotBlank
        private String key;

        @Data
        public static class Cookie {

            private boolean httpOnly;
            private boolean secure;
        }
    }

    @Data
    public static class YouTube {

        @NotBlank
        private String key;

        @NotBlank
        private String baseUrl;
    }
}
