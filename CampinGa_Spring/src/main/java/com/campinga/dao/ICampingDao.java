package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICampingDao {
	
	void categoryAll(HashMap<String, Object> paramMap);
	void categoryList(HashMap<String, Object> paramMap);
	void campNameSerch(HashMap<String, Object> paramMap);
	void campDetailByBseq(HashMap<String, Object> paramMap);
	void getFav(HashMap<String, Object> paramMap);
	void selectCampingList(HashMap<String, Object> paramMap);
	void getCount(HashMap<String, Object> cntMap);
	void selectQnaByBseq(HashMap<String, Object> paramMap);
	void selectReviewByBseq(HashMap<String, Object> paramMap);
	

}
