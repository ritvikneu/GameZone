package com.gamezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan({"com.gamezone.controller","com.gamezone.pojo","com.gamezone.validator","com.gamezone.view"})
public class GameZone1Application extends SpringBootServletInitializer implements WebMvcConfigurer{

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/GameZone");
		SpringApplication.run(GameZone1Application.class, args);
	}

	public void addViewController(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
