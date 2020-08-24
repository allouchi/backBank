package com.banque;


import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WsServiceBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsServiceBootApplication.class, args);
	}	
	
//	@Bean
//	public Function<String, String> upperCase() {
//		return t -> t.toUpperCase();
//	}
	
}

class TestUpperCase implements Function<String, String> {

	@Override
	public String apply(String t) {
		return t.toUpperCase();
	}
	
}
