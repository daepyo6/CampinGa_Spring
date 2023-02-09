package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.dto.CampingVO;
import com.campinga.dto.MemberVO;
import com.campinga.dto.Paging;
import com.campinga.service.CampingService;

@Controller
public class CampingController {
	
	@Autowired
	CampingService cs;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/category")
	public ModelAndView categoryList(@RequestParam("kind") int kind){
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor", null);
		if(kind == 1) {
			cs.categoryAll(paramMap);	
		} else {			
			String [] kindList = {"", "오토캠핑", "캠핑", "카라반", "캠프닉"};
			String category = kindList[kind-1]; 
			paramMap.put("category", category);					
			cs.categoryList(paramMap);	
		}		
		
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
		
		mav.addObject("campList", list);
		mav.setViewName("camping/campingList");
		return mav;
	}
	
	@RequestMapping("/CampNameSearch")
	public ModelAndView CampNameSearch(@RequestParam("key") String key) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("key", key);
		cs.campNameSerch(paramMap);
		
		ArrayList<HashMap<String, Object>> list
		= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
	
		mav.addObject("campList", list);
		mav.addObject("key", (String)paramMap.get("key"));
		mav.setViewName("camping/campingList");
		return mav;
	}
	
	@RequestMapping("/campDetail")
	public ModelAndView campDetail(@RequestParam("bseq") int bseq,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bseq", bseq);
		mav.addObject("bseq", bseq);		

		paramMap.put("request", request);
		
		// 캠핑장 정보 조회
		paramMap.put("ref_cursor", null);		
		cs.campDetailByBseq(paramMap);		
		ArrayList<HashMap<String, Object>> list
		= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
		mav.addObject("campMain", list.get(0));	
		
		// 로그인한 경우 즐겨찾기 조회
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>)request.getSession().getAttribute("loginUser");
		if(loginUser!=null) {
			paramMap.put("myFav", null);
			paramMap.put("mid", loginUser.get("MID"));
			cs.getFav(paramMap);
			if(paramMap.get("myFav")!=null) {
				System.out.println("myFav : "+paramMap.get("myFav"));
				mav.addObject("chk_fav", "Y");
			}
		}
		
		// 캠핑장 객실 조회
		paramMap.put("ref_cursor2", null);
		cs.selectCampingList(paramMap);
		
		ArrayList<HashMap<String, Object>> list2
		= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor2");
		
		mav.addObject("campingList", list2);
		
		
		// 캠핑장 QnA 조회
		paramMap.put("ref_cursor3", null);	
		cs.selectQnaByBseq(paramMap);
		
		ArrayList<HashMap<String, Object>> list3
		= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor3");
		
		if(request.getParameter("qseq")!=null) {
			int qseq = Integer.parseInt(request.getParameter("qseq"));
			mav.addObject("updateQseq", qseq);
		}
		mav.addObject("paging1", (Paging)paramMap.get("paging1"));
		mav.addObject("qnalist", list3);
		
		
		// 캠핑장 review 조회
		paramMap.put("ref_cursor4", null);		
		cs.selectReviewByBseq(paramMap);
		
		ArrayList<HashMap<String, Object>> list4
		= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor4");
		
		if(request.getParameter("rseq")!=null) {
			int rseq = Integer.parseInt(request.getParameter("rseq"));
			mav.addObject("updateRseq", rseq);
		}
		
		mav.addObject("paging2", (Paging)paramMap.get("paging2"));
		mav.addObject("reviewList", list4);
		
			
		mav.setViewName("camping/campDetail");
		return mav;
	}
	
	
	

}
