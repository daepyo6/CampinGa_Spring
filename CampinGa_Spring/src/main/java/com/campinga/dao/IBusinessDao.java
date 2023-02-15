package com.campinga.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.campinga.dto.QnaVO;

@Mapper
public interface IBusinessDao {

	void getBusinessCam(HashMap<String, Object> paramMap);
	void updateBusiness(HashMap<String, Object> paramMap);
	void deleteBusiness(HashMap<String, Object> paramMap);
	void BusinessGetAllCount(HashMap<String, Object> cntMap);
	
    void getBusinessRestList(HashMap<String, Object> paramMap);

	void getBusinessQnaList(HashMap<String, Object> paramMap);
	void getQnaOne(HashMap<String, Object> paramMap);
	void SaveQnaRep(QnaVO qnavo);

	void campingRoomList(HashMap<String, Object> paramMap);
	void campingRoomOne(HashMap<String, Object> paramMap);
	void insertCampingRoom(HashMap<String, Object> paramMap);
	void deleteCampingRoom(HashMap<String, Object> paramMap);
	void updateCampingRoom(HashMap<String, Object> paramMap);
	
	

}
