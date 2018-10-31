package com.andy.cloud.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class ConfigClientApplication {

    @Value("${name}")
    private String name;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }


    @GetMapping(value = "get")
    public String getName() {

        return name;
    }
}
