package com.campinga.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.dto.BusinessVO;
import com.campinga.dto.CampingVO;
import com.campinga.dto.Paging;
import com.campinga.dto.QnaVO;
import com.campinga.service.BusinessService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BusinessController {

	@Autowired
	BusinessService bs;
	private Object bseq;

	@RequestMapping(value = "loginBS", method = RequestMethod.POST)
	public String loginBusinessman(@ModelAttribute("dto") @Valid BusinessVO businessvo, BindingResult result,
			HttpServletRequest request, Model model) {

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

			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

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

	// Validation은 request로 받은 것을 dto 형식으로 바로 변환 multi는 인코딩거치는 과정이 있는데
	// Validation이 인코딩을 풀지 못하는 오류?
	// multi를 따로 실행해서 이름값만 전달해서 validation 하게 만들면 된다 는식으로 적었는데
	// 사업자 마이페이지 이동
	@RequestMapping("/businessmanMypage")
	public ModelAndView businessmanMypage(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>) session.getAttribute("loginBusinessman");
		if (loginBusinessman == null) {
			mav.setViewName("member/login");
		} else {
			mav.setViewName("business/mypage/mypage");
		}
		return mav;
	}

	// 사업자 정보수정 이동
	@RequestMapping(value = "/businessmanEditForm")
	public String businessmanEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>) session.getAttribute("loginBusinessman");
		BusinessVO dto = new BusinessVO();
		dto.setBid((String) loginBusinessman.get("BID"));
		dto.setName((String) loginBusinessman.get("NAME"));
		dto.setPhone((String) loginBusinessman.get("PHONE"));
		dto.setEmail((String) loginBusinessman.get("EMAIL"));
		model.addAttribute("dto", dto);
		return "business/mypage/updateInfo";
	}

	// 사업자 정보 수정
	@RequestMapping(value = "/updateBusinessInfo", method = RequestMethod.POST)
	public ModelAndView updateBusinessInfo(@ModelAttribute("dto") @Valid BusinessVO businessvo, BindingResult result,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("business/mypage/updateInfo");
		if (result.getFieldError("name") != null)
			mav.addObject("message", result.getFieldError("name").getDefaultMessage());
		else if (result.getFieldError("phone") != null)
			mav.addObject("message", result.getFieldError("phone").getDefaultMessage());
		else if (result.getFieldError("email") != null)
			mav.addObject("message", result.getFieldError("email").getDefaultMessage());
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("BID", businessvo.getBid());
			paramMap.put("NAME", businessvo.getName());
			paramMap.put("PHONE", businessvo.getPhone());
			paramMap.put("EMAIL", businessvo.getEmail());

			bs.updateBusiness(paramMap);
			HttpSession session = request.getSession();
			session.setAttribute("loginBusinessman", paramMap);
			mav.setViewName("redirect:/businessmanMypage");
		}
		return mav;
	}

	// 사업자 마이페이지 회원탈퇴
	@RequestMapping("deleteBusinessman")
	public String deleteBusinessman(HttpSession session, Model model) {
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>) session.getAttribute("loginBusinessman");
		if (loginBusinessman == null) {
			return "member/login";
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bid", loginBusinessman.get("BID"));
			bs.deleteBusiness(paramMap);
			session.removeAttribute("loginBusinessman");
		}
		return "main";
	}

	// 사업자 예약 확인
	@RequestMapping(value = "/businessmanRestList")
	public ModelAndView businessmanRestList(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>) session.getAttribute("loginBusinessman");
		if (loginBusinessman == null) {
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bseq", Integer.parseInt(loginBusinessman.get("BSEQ") + ""));
			paramMap.put("request", request);
			paramMap.put("ref_cursor", null);
			bs.getBusinessRestList(paramMap);

			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

			mav.addObject("paging", (Paging) paramMap.get("paging"));

			mav.addObject("reList", list);
			mav.setViewName("business/reservation/businessmanRestList");
		}
		return mav;
	}

	// 사업자 QnA 관리
	@RequestMapping("/businessmanQnaList")
	public ModelAndView businessmanQnaList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> bvo = (HashMap<String, Object>) session.getAttribute("loginBusinessman");
		if (bvo == null)
			mav.setViewName("member/login");
		else {

			if (request.getParameter("first") != null) {
				session.removeAttribute("page");
			}

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put("bseq", Integer.parseInt(bvo.get("BSEQ") + ""));
			paramMap.put("ref_cursor", null);
			bs.getBusinessQnaList(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("paging", (Paging) paramMap.get("paging"));
			mav.addObject("qnalist", list);
			mav.setViewName("business/qna/qnaList");
		}
		return mav;
	}

	// 사업자 qna 상세
	@RequestMapping("/businessmanQnaView")
	public ModelAndView businessmanQnaView(@RequestParam("qseq") int qseq, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		if (session.getAttribute("loginBusinessman") == null) {
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("qseq", qseq);
			bs.getQnaOne(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("qnaVO", list.get(0));
			mav.setViewName("business/qna/qnaForm");
		}
		return mav;
	}

	// 사업자 QnA 답변등록
	@RequestMapping(value = "/businessmanQnaRepSave", method = RequestMethod.POST)
	public String businessmanQnaRepSave(@ModelAttribute("qnaVO") QnaVO qnavo) {
		bs.SaveQnaRep(qnavo);
		return "redirect:/businessmanQnaView?qseq=" + qnavo.getQseq();
	}

	@RequestMapping("/campingRoomList")
	public ModelAndView campingRoomList(HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> loginBusinessman = (HashMap<String, Object>) session.getAttribute("loginBusinessman");

		if (loginBusinessman == null) {
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("ref_cursor", null);
			paramMap.put("bseq", Integer.parseInt(loginBusinessman.get("BSEQ") + ""));
			paramMap.put("request", request);
			bs.campingRoomList(paramMap);
			ArrayList<HashMap<String, Object>> list = 
					(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("campingList", list);
			mav.addObject("paging", (Paging) paramMap.get("paging"));
			mav.setViewName("business/room/campingRoomList");
		}
		return mav;
	}

	@RequestMapping("deleteCampingRoom")
	public String deleteCampingRoom(@RequestParam("cseq") int cseq, 
			Model model, HttpServletRequest request) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cseq", cseq);

		bs.deleteCampingRoom(paramMap);
		return "redirect:/campingRoomList";
	}

	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/images");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			result.put("STATUS", 1);
			result.put("FILENAME", multi.getFilesystemName("c_image") );
		} catch (IOException e) { e.printStackTrace();
		}
		
		return result;  // result 는 목적지의 매개변수 data 객체로 전달됩니다.
	}
	
	@RequestMapping("campingRoomUpdateForm")
	public ModelAndView campingRoomUpdateForm(@RequestParam("cseq") int cseq) {	
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cseq", cseq);
		paramMap.put("ref_cursor", null);
		
		bs.campingRoomOne(paramMap);
		
		ArrayList<HashMap<String, Object>> list = 
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		mav.addObject("campVO", list.get(0));
		mav.setViewName("business/room/updateCampingRoom");
		return mav;
	}
	
	@RequestMapping(value="/updateCampingRoom", method = RequestMethod.POST)
	public String campingRoomUpdate(HttpServletRequest request) {	
		String savePath = context.getRealPath("/images");
		int cseq = 0;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		try {
			MultipartRequest multi = new MultipartRequest(
					request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			paramMap.put("cseq",  Integer.parseInt( multi.getParameter("cseq") ) );
			cseq = Integer.parseInt( multi.getParameter("cseq") );
			paramMap.put( "c_class", multi.getParameter("c_class"));
			paramMap.put( "price" , Integer.parseInt(multi.getParameter("price")));
			paramMap.put( "min_people", Integer.parseInt( multi.getParameter("min_people")));
			paramMap.put( "max_people", Integer.parseInt( multi.getParameter("max_people")));
		    if( multi.getFilesystemName("c_image") == null)   
		    	paramMap.put("c_image", multi.getParameter("oldc_image")); // 수정하려는 이미지가  없을경우 이전 이미지로
		    else   
		    	paramMap.put("c_image", multi.getFilesystemName("c_image"));
		    bs.updateCampingRoom( paramMap );
		} catch (IOException e) {  e.printStackTrace();
		}
		return "redirect:/campingRoomList";
	}

}
