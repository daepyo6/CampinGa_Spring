package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICampingDao {

	void getNewRecoCamping(HashMap<String, Object> paramMap);
	void getSearchResult(HashMap<String, Object> paramMap);

}
