package com.crud.tasks.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConfig {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.mail}")
    private String adminMail;
}
