package com.campinga.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campinga.dto.Paging;
import com.campinga.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;	

	@RequestMapping(value = "/adminLoginForm")
	public String adminLoginForm() {
		return "admin/adminlogin";
	}
	
	// 관리자 로그인
	@RequestMapping(value="/adminLogin", method = RequestMethod.POST)
	public String adminLogin(HttpServletRequest request, Model model,
			@RequestParam("adminId") String adminId,
			@RequestParam("adminPwd") String adminPwd) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("adminId", adminId);
		as.getAdminList(paramMap); 	
		
		String url = "admin/adminlogin";
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
	    if( list.size() == 0 ) {	
	       model.addAttribute("message", "조회된 아이디가 없습니다");
		   return url;
	    }
	    HashMap<String, Object> resultMap = list.get(0);
	    if(resultMap.get("PWD")==null)
		    model.addAttribute("message", "관리자에게 문의하세요");
	    else if(adminPwd.equals((String)resultMap.get("PWD"))) {
		    HttpSession session = request.getSession();
		    session.setAttribute("loginAdmin", resultMap);
		    url = "redirect:/adminMemberList";
	    } else
		  model.addAttribute("message", "비밀번호가 틀렸습니다");
		return url;
	}	
		
	// 관리자 로그아웃
	@RequestMapping(value="/adminLogout")
	public String adminLogout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    session.removeAttribute("loginAdmin");
	    return "redirect:/";
	}
		
	// 회원 리스트 페이지
	@RequestMapping("/adminMemberList")
	public ModelAndView adminMemberList(
			@RequestParam(value="first", required=false) String first,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		HashMap<String, Object>loginAdmin
			= (HashMap<String, Object>)session.getAttribute("loginAdmin");
		
		if(loginAdmin==null) {
			mav.setViewName("admin/adminlogin");			
		} else {
			if(first!=null) {
				request.removeAttribute("page");
				session.removeAttribute("page");
				request.removeAttribute("key");
				session.removeAttribute("key");
			}
			HashMap<String, Object>paramMap = new HashMap<String,Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("request", request);
			as.adminMemberList(paramMap);
			ArrayList<HashMap<String, Object>>list
				=(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			mav.addObject("key", (String)paramMap.get("key"));
			mav.addObject("memberList", list);
			mav.addObject("paging", (Paging)paramMap.get("paging"));
			mav.setViewName("admin/member/memberList");			
		}
		return mav;
	}	
	
	// 캠핑장 관리 페이지
	@RequestMapping("/adminCampingList")
	public ModelAndView adminCampingList(
			@RequestParam(value="first", required=false) String first,
			HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		HashMap<String, Object>loginAdmin
			= (HashMap<String, Object>)session.getAttribute("loginAdmin");

		if(loginAdmin==null) {
			mav.setViewName("admin/adminlogin");			
		} else {
			if(first!=null) {
				request.removeAttribute("page");
				session.removeAttribute("page");
				request.removeAttribute("key");
				session.removeAttribute("key");
			}
			HashMap<String, Object>paramMap = new HashMap<String,Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("request", request);
			as.adminCampingList(paramMap);
			ArrayList<HashMap<String, Object>>list
				=(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			mav.addObject("key", (String)paramMap.get("key"));
			mav.addObject("campingList", list);
			mav.addObject("paging", (Paging)paramMap.get("paging"));
			mav.setViewName("admin/camping/campingList");			
		}
		return mav;
	}

	
	@RequestMapping("/adminRestList")
	public ModelAndView adminRestList(
			@RequestParam(value="first", required=false) String first,
	        HttpServletRequest request) {
	
		ModelAndView mav = new ModelAndView();
	    HttpSession session = request.getSession();
	
	    HashMap<String, Object>loginAdmin
	       = (HashMap<String, Object>)session.getAttribute("loginAdmin");
	
	    if(loginAdmin==null) {
	         mav.setViewName("admin/adminlogin");         
	    } else {
	    	if(first!=null) {
	    		request.removeAttribute("page");
	            session.removeAttribute("page");
	            request.removeAttribute("key");
				session.removeAttribute("key");
	        }
	        HashMap<String, Object>paramMap = new HashMap<String,Object>();
	        paramMap.put("ref_cursor", null);
	        paramMap.put("request", request);
	        as.adminRestList(paramMap);
	        ArrayList<HashMap<String, Object>>list
	           =(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
	        mav.addObject("key", (String)paramMap.get("key"));
	        mav.addObject("restList", list);
	        mav.addObject("paging", (Paging)paramMap.get("paging"));
	        mav.setViewName("admin/camping/campingRestList");         
	    }
	    return mav;
	 }
	 
	
	// 리뷰페이지
    @RequestMapping("/adminReviewList")
    public ModelAndView adminReviewList(
         @RequestParam(value="first", required=false) String first,
         HttpServletRequest request) {
      
         ModelAndView mav = new ModelAndView();
         HttpSession session = request.getSession();
       
        HashMap<String, Object>loginAdmin
          = (HashMap<String, Object>)session.getAttribute("loginAdmin");
   
        if(loginAdmin==null) {
           mav.setViewName("admin/adminlogin");         
        } else {
            if(first!=null) {
               request.removeAttribute("page");
               session.removeAttribute("page");
               request.removeAttribute("key");
               session.removeAttribute("key");
            }
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("request", request);
        paramMap.put("ref_cursor", null);
        as.adminReviewList(paramMap);
        ArrayList<HashMap<String, Object>> list 
           = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        mav.addObject("key", (String)paramMap.get("key"));
        mav.addObject("paging", (Paging)paramMap.get("paging"));
        mav.addObject("reviewList", list);
        mav.setViewName("admin/review/reviewList");
        }
       return mav;
     }
   
    // 리뷰 삭제
    @RequestMapping("adminReviewDelete")
    public String board_delete(@RequestParam("rseq") String[] rseqArr,
    		Model model , HttpServletRequest request) {
      
    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("rseqArr", rseqArr);
      
        as.deleteReview(paramMap);
        return "redirect:/adminReviewList";
    }
    
    
    // 공지사항 관리 페이지
 	@RequestMapping("/adminNoticeList")
 	public ModelAndView adminNoticeList(
 			@RequestParam(value="first", required=false) String first,
 			HttpServletRequest request) {

 		ModelAndView mav = new ModelAndView();
 		HttpSession session = request.getSession();

 		HashMap<String, Object>loginAdmin
 			= (HashMap<String, Object>)session.getAttribute("loginAdmin");

 		if(loginAdmin==null) {
 			mav.setViewName("admin/adminlogin");			
 		} else {
 			if(first!=null) {
 				request.removeAttribute("page");
 				session.removeAttribute("page");
 			}
 			HashMap<String, Object>paramMap = new HashMap<String,Object>();
 			paramMap.put("ref_cursor", null);
 			paramMap.put("request", request);
 			as.adminNoticeList(paramMap);
 			ArrayList<HashMap<String, Object>>list
 				=(ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
 			mav.addObject("noticeList", list);
 			mav.addObject("paging", (Paging)paramMap.get("paging"));
 			mav.setViewName("admin/notice/noticeList");			
 		}
 		return mav;
 	}
    
    
    
    
    
    
    
    
    
    
    
    

		
}