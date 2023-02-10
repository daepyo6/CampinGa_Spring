package com.campinga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.campinga.service.BusinessService;

@Controller
public class BusinessController {
	
	@Autowired
	BusinessService bs;
	
}
