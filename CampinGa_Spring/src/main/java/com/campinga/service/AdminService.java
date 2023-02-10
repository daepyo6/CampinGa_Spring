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

	public void getAdminList(HashMap<String, Object> paramMap) {
		adao.getAdminList(paramMap);
	}



}
