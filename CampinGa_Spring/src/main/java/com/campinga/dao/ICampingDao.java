package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICampingDao {
	
	void categoryAll(HashMap<String, Object> paramMap);
	void categoryList(HashMap<String, Object> paramMap);
	void campNameSerch(HashMap<String, Object> paramMap);

}
