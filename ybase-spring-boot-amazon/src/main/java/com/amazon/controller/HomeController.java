package com.amazon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to Amazon";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "Welcome to Amazon about page";
	}
}
