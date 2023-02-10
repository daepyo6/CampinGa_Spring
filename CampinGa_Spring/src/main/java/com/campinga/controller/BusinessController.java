package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.dto.BusinessVO;
import com.campinga.dto.QnaVO;
import com.campinga.service.BusinessService;

@Controller
public class BusinessController {
	
	@Autowired
	BusinessService bs;
	
	@RequestMapping(value = "loginBS", method = RequestMethod.POST)
	public String loginBusinessman(@ModelAttribute("dto") @Valid BusinessVO businessvo, BindingResult result,
			HttpServletRequest request, Model model) {

		System.out.println(businessvo.getBid() + " " + businessvo.getPwd());
		String url = "member/login";
		if (result.getFieldError("bid") != null)
			model.addAttribute("message", result.getFieldError("bid").getDefaultMessage());
		else if (result.getFieldError("pwd") != null)
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bid", businessvo.getBid());
			paramMap.put("ref_cursor", null);

			bs.getBusinessCam(paramMap);

			ArrayList<HashMap<String, Object>> list = 
					(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

			if (list.size() == 0) {
				model.addAttribute("message", "아이디가 없습니다");
				return "member/login";
			}
			HashMap<String, Object> bvo = list.get(0);
			if (!bvo.get("PWD").equals(businessvo.getPwd()))
				model.addAttribute("message", "비밀번호가 맞지않습니다");
			else if (bvo.get("PWD").equals(businessvo.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("loginBusinessman", bvo);
				url = "redirect:/";
			}
		}
		return url;
	}
	
	@RequestMapping(value = "businessmanMypage")
	public String business_mypage(HttpServletRequest request, Model model) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman = 
				(HashMap<String, Object>) session.getAttribute("loginBusinessman");
		System.out.println("1");
		if (loginBusinessman == null)
			mav.setViewName("member/login");
		else {
			model.addAttribute("loginBusinessman", loginBusinessman);	
		}
		return "business/mypage/mypage";
	}
	
	
	
	
	
}
