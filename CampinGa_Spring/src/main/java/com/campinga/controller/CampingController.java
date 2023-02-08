package com.campinga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campinga.service.CampingService;

@Controller
public class CampingController {
	
	@Autowired
	CampingService cs;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}

}
