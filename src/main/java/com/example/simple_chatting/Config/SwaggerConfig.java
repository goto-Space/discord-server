package com.example.simple_chatting.config;

import com.example.simple_chatting.security.Authentication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "gotoSpace API 명세서",
        description = "gotoSpace API 명세서입니다.",
        version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    static {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(Authentication.class);
    }
}
