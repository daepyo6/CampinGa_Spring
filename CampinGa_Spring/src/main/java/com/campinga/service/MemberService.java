package com.campinga.service;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campinga.dao.IMemberDao;
import com.campinga.dto.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	IMemberDao mdao;

	public void getMemberCam(HashMap<String, Object> paramMap) {
		mdao.getMemberCam(paramMap);
		
	}

	public void insertMemberCam(MemberVO membervo) {
		mdao.insertMemberCam(membervo);
		
	}

}
