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
import com.campinga.dto.Paging;
import com.campinga.service.BusinessService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


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
	
	
	@RequestMapping("/campingRoomList")
	public ModelAndView campingRoomList( HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object>loginBusinessman 
			= (HashMap<String, Object>)session.getAttribute("loginBusinessman");
		
		if(loginBusinessman==null) {
			mav.setViewName("member/login");			
		} else {
			HashMap<String, Object>paramMap = new HashMap<>();
			paramMap.put("ref_cursor", null);
			paramMap.put("bseq", Integer.parseInt(loginBusinessman.get("BSEQ")+""));
			paramMap.put("request", request);
			bs.campingRoomList(paramMap);
			ArrayList<HashMap<String, Object>>list
				=(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			mav.addObject("campingList", list);
			mav.addObject("paging", (Paging)paramMap.get("paging"));
			mav.setViewName("business/room/campingRoomList");			
		}
		return mav;
	}
	
	@RequestMapping("deleteCampingRoom")
    public String deleteCampingRoom(@RequestParam("cseq") int cseq,
    		Model model , HttpServletRequest request) {
      
    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("cseq", cseq);
    	
        bs.deleteCampingRoom(paramMap);
        return "redirect:/campingRoomList?";
  	}
	
	@RequestMapping("insertCampingRoomForm")
	public String insertCampingRoomForm(Model model , HttpServletRequest request) {
		HttpSession session = request.getSession();

 		HashMap<String, Object>loginBusinessman
 			= (HashMap<String, Object>)session.getAttribute("loginBusinessman");
 		String url = "business/room/insertCampingRoom";
 		if(loginBusinessman==null) {
 			url = "member/login";			
 		} 
  		return url;
  	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup(Model model, HttpServletRequest request ){
		// 현재 메서드는 다른 메서드처럼 jsp 파일이름을 리턴해서 파일이름.jsp롤 이동하는 메서드가 아닙니다.
		// ajax에 의해서 호출된 지점으로 다시 되돌아가서 화면 이동없이 운영이 계속되어야 하기때문에 이동할때
		// 가져갈 데이터가 리턴됩니다
		String path = context.getRealPath("/images/campingImage");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			result.put("FILENAME", multi.getFilesystemName("c_image"));
		}catch (IOException e) { e.printStackTrace();
		}
		
		return result;  // result 는 목적지의 매개변수 data 객체로 전달됩니다.
	}
	
	@RequestMapping(value="insertCampingRoom", method=RequestMethod.POST)
	public String insertCampingRoom(Model model , HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object>loginBusinessman 
			= (HashMap<String, Object>)session.getAttribute("loginBusinessman");
		
		if(loginBusinessman==null) {
			mav.setViewName("member/login");
		} else {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bseq", Integer.parseInt(loginBusinessman.get("BSEQ")+""));
		paramMap.put("cname", loginBusinessman.get("CNAME"));
		paramMap.put("c_class", request.getParameter("c_class"));
		paramMap.put("c_content", request.getParameter("c_content"));
		paramMap.put("price", Integer.parseInt( request.getParameter("price")));
		paramMap.put("min_people", Integer.parseInt( request.getParameter("min_people")));
		paramMap.put("max_people", Integer.parseInt( request.getParameter("max_people")));
		paramMap.put("c_image", request.getParameter("c_image"));
		
		bs.insertCampingRoom(paramMap);
		}
		return "redirect:/campingRoomList";
		
	}
}
