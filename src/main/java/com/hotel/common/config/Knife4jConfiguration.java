package com.hotel.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    // 配置全局信息，这是酒店管理系统的API文档
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
               .info(new Info()
                        .title("酒店管理系统API文档")
                        .version("v1.0")
                        .description("这是一个酒店管理系统的API文档")
                        .termsOfService("https://example.com")
                        .license(new License().name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    @Bean
    public GroupedOpenApi loginAPI() {
        return GroupedOpenApi
                .builder()
                .group("所有接口")
                .pathsToMatch(
                        "/**"
                )
                .build();
    }

}