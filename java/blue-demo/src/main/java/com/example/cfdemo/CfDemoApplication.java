package com.example.cfdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CfDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfDemoApplication.class, args);
	}

  @RestController
	class SalutationsController{
        @GetMapping("/")
        public String hello(){
            return "Hello from Blue!";
        }
    }
}