package com.andy.cloud.turibeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
public class TurbineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineServiceApplication.class, args);
    }

//    @EnableWebSecurity
//    public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//            super.configure(http);
//        }
//    }
}
