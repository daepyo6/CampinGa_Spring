package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDao {


	void AdminCount(HashMap<String, Object> cntMap);
	void adminMemberList(HashMap<String, Object> paramMap);

	void getAdminList(HashMap<String, Object> paramMap);



}
