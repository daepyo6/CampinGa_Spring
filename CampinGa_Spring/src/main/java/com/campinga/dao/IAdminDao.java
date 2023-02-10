package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDao {

	void getAdminList(HashMap<String, Object> paramMap);

	

}
