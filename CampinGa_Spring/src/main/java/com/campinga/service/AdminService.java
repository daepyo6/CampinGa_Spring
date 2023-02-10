package com.campinga.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.IAdminDao;
import com.campinga.dto.Paging;

@Service
public class AdminService {
	
	@Autowired
	IAdminDao adao;

	public void adminMemberList(HashMap<String, Object> paramMap) {
		// 회원 리스트 조회
		int page = 1;
		HttpServletRequest requset = (HttpServletRequest) paramMap.get("request");
		HttpSession session = requset.getSession();
		if (requset.getParameter("page") != null) {
			page = Integer.parseInt(requset.getParameter("page"));
			session.setAttribute("page", page);
		} else if (session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);

		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0);
		cntMap.put("tableName", 1); 		
		adao.AdminCount(cntMap);

		int count = Integer.parseInt(cntMap.get("cnt") + "");
		paging.setTotalCount(count);
		paging.setDisplayPage(10);
		paging.setDisplayRow(10);
		paging.paging();
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("paging", paging);
		adao.adminMemberList(paramMap);		
	}

	public void getAdminList(HashMap<String, Object> paramMap) {
		adao.getAdminList(paramMap);
	}

	public void adminReviewList(HashMap<String, Object> paramMap) {
		// 리뷰 리스트 조회
		int page = 1;
		HttpServletRequest requset = (HttpServletRequest) paramMap.get("request");
		HttpSession session = requset.getSession();
		if (requset.getParameter("page") != null) {
			page = Integer.parseInt(requset.getParameter("page"));
			session.setAttribute("page", page);
		} else if (session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);

		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0);
		cntMap.put("tableName", 4); 		
		adao.AdminCount(cntMap);

		int count = Integer.parseInt(cntMap.get("cnt") + "");
		paging.setTotalCount(count);
		paging.setDisplayPage(10);
		paging.setDisplayRow(10);
		paging.paging();
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("paging", paging);
		adao.adminReviewList(paramMap);			
	}

	public void deleteReview(HashMap<String, Object> paramMap) {

		String [] rseqArr = (String[])paramMap.get("rseqArr");
		
		for(String rseq : rseqArr) {
			paramMap.put("rseq", rseq);
			adao.deleteReview(paramMap);
		}
		
	}


}
