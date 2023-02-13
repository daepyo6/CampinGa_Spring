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
import com.campinga.dto.MemberVO;
import com.campinga.dto.Paging;
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
	
	//사업자 마이페이지 이동
	@RequestMapping("/businessmanMypage")  
	public ModelAndView businessmanMypage( HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman 
			= (HashMap<String, Object>)session.getAttribute("loginBusinessman");
		if( loginBusinessman == null ) {
			mav.setViewName("member/login");
		}else {
			mav.setViewName("business/mypage/mypage");
		
		}
		return mav;
		}
	
	//사업자 정보수정 이동
	@RequestMapping(value="/businessmanEditForm" )
	public String businessmanEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman 
			= (HashMap<String, Object>)session.getAttribute("loginBusinessman");
		BusinessVO dto = new BusinessVO();
		dto.setBid( (String)loginBusinessman.get("BID") );
		dto.setName( (String)loginBusinessman.get("NAME") );
		dto.setPhone( (String)loginBusinessman.get("PHONE") );
		dto.setEmail( (String)loginBusinessman.get("EMAIL") );
		
		
		model.addAttribute("dto" , dto);
		return "business/mypage/updateInfo";
	}
	//사업자 정보 수정
	@RequestMapping(value = "/updateBusinessInfo", method=RequestMethod.POST)
	public ModelAndView updateBusinessInfo( 
			@ModelAttribute("dto") @Valid BusinessVO businessvo, BindingResult result,
		HttpServletRequest request	) {
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("business/mypage/updateInfo");
		if( result.getFieldError("name") != null )
			mav.addObject("message", result.getFieldError("name").getDefaultMessage() );
		else if( result.getFieldError("phone") != null )
			mav.addObject("message", result.getFieldError("phone").getDefaultMessage() );
		else if( result.getFieldError("email") != null )
			mav.addObject("message", result.getFieldError("email").getDefaultMessage() );
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("BID", businessvo.getBid() );
			paramMap.put("NAME", businessvo.getName() );
			paramMap.put("PHONE", businessvo.getPhone() );
			paramMap.put("EMAIL", businessvo.getEmail() );
			
			bs.updateBusiness( paramMap );
			HttpSession session = request.getSession();
			session.setAttribute("loginBusinessman", paramMap);
			mav.setViewName("redirect:/businessmanMypage");
		}		
		return mav;
	}
	//사업자 마이페이지 회원탈퇴
	
	@RequestMapping("deleteBusinessman")
	public String deleteBusinessman( HttpSession session, Model model) {
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>)session.getAttribute("loginBusinessman");
		if( loginBusinessman == null ) {
			return "member/login";
		}else {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bid", loginBusinessman.get("BID"));
		bs.deleteBusiness( paramMap);
		session.removeAttribute("loginBusinessman");
	}	
		return "main";
	}
	
	//사업자 예약 확인 
	@RequestMapping(value="/businessmanRestList")
	public ModelAndView businessmanRestList(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if( session.getAttribute("loginBusinessman")==null) 
			mav.setViewName("member/login");
		else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put( "ref_cursor", null );
			bs.getBusinessRestList( paramMap );
			
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("paging" , (Paging)paramMap.get("paging"));
			mav.addObject("key", (String)paramMap.get("key"));
			mav.addObject("reList", list);
			mav.setViewName("business/reservation/businessmanRestList");
		}
		return mav;
	}
	
	
	
	
}
