package com.clinicBackEnd.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/anonymous")
	@CrossOrigin(origins = "http://localhost:3000")
	public String helloGuest() {
		return "Hello guest";
	}
	
	@GetMapping("/admin/a")
	@CrossOrigin(origins = "http://localhost:3000")
	public String helloAdmin() {
		return "Hello admin";
	}
	
	@GetMapping("/index")
	@CrossOrigin(origins = "http://localhost:3000")
	public String helloUser() {
		return "Hello User";
	}
	
	@GetMapping("/logout")
	@CrossOrigin(origins = "http://localhost:3000")
	public String logout() {
		return "logout";
	}
	
	
}
