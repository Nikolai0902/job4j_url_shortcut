package ru.job4j.url.shortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Главный класс приложения с методом main
 */
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("Go to http://localhost:8085/");
		System.out.println("Go to http://localhost:8085/v2/api-docs/");
		System.out.println("Go to http://localhost:8085/v3/api-docs/");
		System.out.println("Go to http://localhost:8085/swagger-ui/index.html");
	}

	/**
	 * Метод, создающий и возвращающий бин кодировщика паролей
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
