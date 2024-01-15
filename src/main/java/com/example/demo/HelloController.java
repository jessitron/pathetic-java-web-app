package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.trace.Span;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
     Span span = Span.current();
     span.setAttribute("jess.was.here",true);
		return "Greetings from Spring Boot!";
	}

}
