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

import com.campinga.dto.MemberVO;
import com.campinga.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login( @ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			HttpServletRequest request, Model model) {
		
		String url = "member/login";
		if( result.getFieldError("mid") != null)
			model.addAttribute("message", result.getFieldError("mid").getDefaultMessage());
		else if( result.getFieldError("pwd") != null)
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mid", membervo.getMid());
			paramMap.put("ref_cursor", null);
			
			ms.getMemberCam( paramMap );
			
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		if( list.size() == 0) {
			model.addAttribute("message", "아이디가 없습니다");
			return "member/login";
		}
			HashMap<String, Object> mvo = list.get(0);
			if( !mvo.get("PWD").equals(membervo.getPwd()))
				model.addAttribute("message", "비밀번호가 맞지않습니다");
			else if( mvo.get("PWD").equals(membervo.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}
	
	// Member logout 기능
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/contract")
	public String contract() {
		return "member/contract";
	}
	
	
	
}
