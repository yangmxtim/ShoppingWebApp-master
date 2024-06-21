package com.shoppingwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShoppingWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingWebAppApplication.class, args);
    }
}
