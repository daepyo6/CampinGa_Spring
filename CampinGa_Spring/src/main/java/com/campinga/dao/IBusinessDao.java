package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBusinessDao {

	void getBusinessCam(HashMap<String, Object> paramMap);

	void updateBusiness(HashMap<String, Object> paramMap);

	void deleteBusiness(HashMap<String, Object> paramMap);

}
