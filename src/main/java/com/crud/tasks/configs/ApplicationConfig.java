package com.crud.tasks.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationConfig {

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;


    @Value("${info.company.name}")
    private String appCompanyName;


    @Value("${info.company.goal}")
    private String appCompanyMotto;


    @Value("${info.company.email}")
    private String appCompanyMail;


    @Value("${info.company.phone}")
    private String appCompanyPhone;
}
