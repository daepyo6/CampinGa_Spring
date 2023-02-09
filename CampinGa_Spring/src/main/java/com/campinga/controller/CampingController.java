package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

}
