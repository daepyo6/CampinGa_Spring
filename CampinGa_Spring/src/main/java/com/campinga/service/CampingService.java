package com.campinga.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.ICampingDao;

@Service
public class CampingService {
	
	@Autowired
	ICampingDao cdao;

	public void getNewRecoCamping(HashMap<String, Object> paramMap) {
		cdao.getNewRecoCamping(paramMap);	
	}

	public void getSearchResult(HashMap<String, Object> paramMap) {
		cdao.getSearchResult(paramMap);
	}

}
