package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	
	
	
	

}
