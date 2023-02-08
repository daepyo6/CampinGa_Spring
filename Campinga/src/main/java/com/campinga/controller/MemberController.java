package com.campinga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.campinga.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;

}
