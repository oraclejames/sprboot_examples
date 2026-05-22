package com.amazon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

	@RequestMapping("/hello")
	String homePage() {
		return "Welcome to Amazon";
	}
}
