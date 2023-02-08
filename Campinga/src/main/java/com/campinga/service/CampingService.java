package com.campinga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.ICampingDao;

@Service
public class CampingService {
	
	@Autowired
	ICampingDao cdao;

}
