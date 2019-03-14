package com.example.improvedcfdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ImprovedCfDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImprovedCfDemoApplication.class, args);
	}

	@RestController
	class GreetingsController{

		@GetMapping("/")
		public String hello(){
			return "Salut from Green!";
		}
	}
}
