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

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "member/login";
	}

	// 멤버 로그인 기능
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			HttpServletRequest request, Model model) {

		String url = "member/login";
		if (result.getFieldError("mid") != null)
			model.addAttribute("message", result.getFieldError("mid").getDefaultMessage());
		else if (result.getFieldError("pwd") != null)
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mid", membervo.getMid());
			paramMap.put("ref_cursor", null);

			ms.getMemberCam(paramMap);

			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

			if (list.size() == 0) {
				model.addAttribute("message", "아이디가 없습니다");
				return "member/login";
			}
			HashMap<String, Object> mvo = list.get(0);
			if (!mvo.get("PWD").equals(membervo.getPwd()))
				model.addAttribute("message", "비밀번호가 맞지않습니다");
			else if (mvo.get("PWD").equals(membervo.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}

	// 멤버 로그아웃 기능
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	@RequestMapping(value = "/contract")
	public String contract() {
		return "member/contract";
	}

	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
	public String join_form(@RequestParam("user") String choose) {
		String url = "";
		if (choose.equals("1")) {
			url = "member/joinForm";
		} else {
			url = "business/joinForm";
		}
		return url;
	}

	// 멤버 아이디 체크
	@RequestMapping("/idCheckForm")
	public String idCheckForm(@RequestParam("mid") String mid, Model model, HttpServletRequest request) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mid", mid);
		paramMap.put("ref_cursor", null);
		ms.getMemberCam(paramMap);

		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if (list.size() == 0) {
			model.addAttribute("result", -1);
		} else {
			model.addAttribute("result", 1);
		}
		model.addAttribute("mid", mid);

		return "member/idCheck";
	}

	// 멤버 회원가입 기능
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			@RequestParam(value = "reid", required = false) String reid,
			@RequestParam(value = "pwdCheck", required = false) String pwdCheck) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinForm");
		mav.addObject("reid", reid);
		if (result.getFieldError("mid") != null)
			mav.addObject("message", result.getFieldError("mid").getDefaultMessage());
		else if (result.getFieldError("pwd") != null)
			mav.addObject("message", result.getFieldError("pwd").getDefaultMessage());
		else if (result.getFieldError("name") != null)
			mav.addObject("message", result.getFieldError("name").getDefaultMessage());
		else if (result.getFieldError("email") != null)
			mav.addObject("message", result.getFieldError("email").getDefaultMessage());
		else if (result.getFieldError("mphone") != null)
			mav.addObject("message", result.getFieldError("mphone").getDefaultMessage());

		else if (!membervo.getMid().equals(reid))
			mav.addObject("message", "아이디 중복체크가 되지 않았습니다");
		else if (!membervo.getPwd().equals(pwdCheck))
			mav.addObject("message", "비밀번호 확인이 일치하지 않습니다");
		else {
			ms.insertMemberCam(membervo);

			mav.addObject("message", "회원가입이 완료되었습니다. 로그인하세요");
			mav.setViewName("member/login");
		}
		return mav;
	}

}
