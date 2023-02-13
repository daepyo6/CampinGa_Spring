package com.campinga.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.IBusinessDao;
import com.campinga.dto.Paging;


@Service
public class BusinessService {
	
	@Autowired
	IBusinessDao bdao;

	public void getBusinessCam(HashMap<String, Object> paramMap) {
		bdao.getBusinessCam(paramMap);
	}

	public void updateBusiness(HashMap<String, Object> paramMap) {
		bdao.updateBusiness(paramMap);
		
	}

	public void deleteBusiness(HashMap<String, Object> paramMap) {
		bdao.deleteBusiness(paramMap);
		
	}

	public void getBusinessRestList(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		
		if( request.getParameter("first")!=null) {
			session.removeAttribute("page");
			}
		int page=1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		}else if( session.getAttribute("page") != null ) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0);
		cntMap.put("tableName", 1);
	    cntMap.put("bseq", paramMap.get("bseq"));
	   
		bdao.BusinessGetAllCount(cntMap);
		
		int count = Integer.parseInt( cntMap.get("cnt")+"" );
		paging.setTotalCount(count);
		
		paging.paging();
		
		
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		bdao.getBusinessRestList(paramMap);	
		paramMap.put("paging", paging);
}
		
		
		
		
	

}
