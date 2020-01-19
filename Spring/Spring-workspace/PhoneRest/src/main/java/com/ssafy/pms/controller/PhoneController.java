package com.ssafy.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhoneController {
	@GetMapping("/start")
	public String start() {
		return "start";
	}	
}
