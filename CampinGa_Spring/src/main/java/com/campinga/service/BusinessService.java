package com.campinga.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.IBusinessDao;

@Service
public class BusinessService {
	
	@Autowired
	IBusinessDao bdao;

	public void getBusinessCam(HashMap<String, Object> paramMap) {
		bdao.getBusinessCam(paramMap);
	}

}
