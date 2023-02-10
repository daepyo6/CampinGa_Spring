package com.campinga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.IAdminDao;

@Service
public class AdminService {
	
	@Autowired
	IAdminDao adao;

}
