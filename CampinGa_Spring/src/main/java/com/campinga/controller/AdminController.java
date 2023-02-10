package com.campinga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.campinga.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;

}
