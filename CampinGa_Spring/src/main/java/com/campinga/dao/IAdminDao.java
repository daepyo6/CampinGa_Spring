package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDao {

	void getAdminList(HashMap<String, Object> paramMap);

	void AdminCount(HashMap<String, Object> cntMap);
	void adminMemberList(HashMap<String, Object> paramMap);

	void adminReviewList(HashMap<String, Object> paramMap);
	void deleteReview(HashMap<String, Object> paramMap);
	void adminCampingList(HashMap<String, Object> paramMap);




}
