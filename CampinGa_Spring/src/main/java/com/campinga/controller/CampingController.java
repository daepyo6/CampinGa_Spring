package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	// 메인 페이지
	@RequestMapping("/")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		// paramMap.put("ref_cursor3", null);

		cs.getNewRecoCamping(paramMap);

		ArrayList<HashMap<String, Object>> newCampList = 
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> recoCampList = 
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		// ArrayList<HashMap<String , Object>> list3
		// = (ArrayList<HashMap<String , Object>>) paramMap.get("ref_cursor3");
		
		mav.addObject("newCampList", newCampList);
		mav.addObject("recoCampList", recoCampList);
		// mav.addObject("bannerList", list3);

		mav.setViewName("main");
		return mav;
	}
	
	// 메인 페이지 검색기능
	@RequestMapping("/mainSearch")
	public ModelAndView mainSearch(HttpServletRequest request, Model model,
			@RequestParam("address1")String address1,
			@RequestParam("address2")String address2) {
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("address1", address1);
		paramMap.put("address2", address2);
		paramMap.put("ref_cursor", null);
		cs.getSearchResult( paramMap );
		
		ArrayList<HashMap<String, Object>> list 
		= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		mav.addObject( "campList", list);		
		mav.setViewName("camping/campingList");
		return mav;
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
			paramMap.put("myFav", "");
			paramMap.put("mid", loginUser.get("MID"));
			cs.getFav(paramMap);
			String myFav = (String)paramMap.get("myFav");
			if(myFav.equals("y")) {
				mav.addObject("chk_fav", "Y");
			} else {
				mav.addObject("chk_fav", "N");
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
	
	@RequestMapping("/reserveForm")
	public ModelAndView reserveForm(HttpServletRequest request,
			@RequestParam("cseq") int cseq) {
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> loginUser
		= (HashMap<String, Object>)request.getSession().getAttribute("loginUser");
		if(loginUser==null) {
			mav.addObject("message", "예약하려면 로그인해주세요.");
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			cs.selectCampOne(paramMap);
			ArrayList<HashMap<String, Object>>list
				= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			mav.addObject("campVO", list.get(0));
			mav.setViewName("camping/reserve/reserve");
		}		
		return mav;
	}
	
	

}
