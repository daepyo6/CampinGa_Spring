package com.campinga.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.ICampingDao;

@Service
public class CampingService {
	
	@Autowired
	ICampingDao cdao;
	
	public void categoryAll(HashMap<String, Object> paramMap) {
		cdao.categoryAll(paramMap);		
	}

	public void categoryList(HashMap<String, Object> paramMap) {
		cdao.categoryList(paramMap);		
	}

	public void campNameSerch(HashMap<String, Object> paramMap) {
		cdao.campNameSerch(paramMap);		
	}

}
