package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
	// 회원 리스트 페이지
	@RequestMapping("/adminMemberList")
	public ModelAndView adminMemberList(
			@RequestParam(value="first", required=false) String first,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		HashMap<String, Object>loginAdmin
			= (HashMap<String, Object>)session.getAttribute("loginAdmin");
		
		if(loginAdmin==null) {
			mav.setViewName("admin/adminlogin");			
		} else {
			if(first!=null) {
				request.removeAttribute("page");
				session.removeAttribute("page");
			}
			HashMap<String, Object>paramMap = new HashMap<String,Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("request", request);
			as.adminMemberList(paramMap);
			ArrayList<HashMap<String, Object>>list
				=(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			mav.addObject("memberList", list);
			mav.setViewName("admin/member/memberList");			
		}
		return mav;
	}

}
