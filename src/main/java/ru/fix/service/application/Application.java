package ru.fix.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(value = "ru.fix.service")
@ServletComponentScan(value = "ru.fix.service.servlets")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
