package com.campinga.dao;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.campinga.dto.MemberVO;

@Mapper
public interface IMemberDao {

	void getMemberCam(HashMap<String, Object> paramMap);
	void insertMemberCam(MemberVO membervo);

}
