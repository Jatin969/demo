package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class GaeStandardExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaeStandardExampleApplication.class, args);
	}

    @RestController
    class HelloWorldController {
        @GetMapping("/")
        public String hello() {
            return "hello world!";
        }
    }

}
