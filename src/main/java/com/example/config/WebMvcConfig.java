package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.controller", "com.example.service", "com.example.repository","com.example.test.controller",})
@Import(DatabaseConfiguration.class)
public class WebMvcConfig {

}
