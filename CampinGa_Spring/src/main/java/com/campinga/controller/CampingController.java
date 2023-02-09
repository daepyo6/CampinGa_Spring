package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.service.CampingService;

@Controller
public class CampingController {

	@Autowired
	CampingService cs;

	@RequestMapping("/")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		// paramMap.put("ref_cursor3", null);

		cs.getNewRecoCamping(paramMap);

		ArrayList<HashMap<String, Object>> list1 = 
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> list2 = 
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		// ArrayList<HashMap<String , Object>> list3
		// = (ArrayList<HashMap<String , Object>>) paramMap.get("ref_cursor3");
		
		for (HashMap<String, Object> param : list1) {
			System.out.println(param.get("IMAGE"));
		}
		
		mav.addObject("newCampList", list1);
		mav.addObject("recoCampList", list2);
		// mav.addObject("bannerList", list3);

		mav.setViewName("main");
		return mav;
	}

}
