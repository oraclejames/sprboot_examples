package com.amazon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/home")
	public String home() {
		return "<h1>Welcome to Amazon</h1>   "
				+ "<a href='../'>Go to Home Page </a>";
	}
	
	@RequestMapping("/about")
	public String about() {
	//	return "Welcome to Amazon about page";
		return "<h1>Welcome to Amazon1</h1>"
        + "<a href='../'>Go to Home Page </a>";
	}
}
