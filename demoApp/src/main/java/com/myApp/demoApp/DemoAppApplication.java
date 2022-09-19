package com.myApp.demoApp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myApp.demoApp.entity.User;
import com.myApp.demoApp.repository.UserRepository;

@SpringBootApplication
public class DemoAppApplication {
	@Autowired
	private UserRepository user;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(new User(101, "User", "password", "user@gmail.com"),
				new User(102, "user1", "pwd1", "user1@gmail.com"), 
				new User(103, "user2", "pwd2", "user2@gmail.com"),
				new User(104, "user3", "pwd3", "user3@gmail.com")
		).collect(Collectors.toList());
		
		user.saveAll(users);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOriginPatterns("*").allowCredentials(true);
			}
		};
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
	}

}
