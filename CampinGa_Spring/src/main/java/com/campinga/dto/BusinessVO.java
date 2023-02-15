package com.campinga.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BusinessVO {
	// 사업자 정보
	private int bseq;
	@NotNull(message="사업자 아이디를 입력하세요")
	@NotEmpty(message="사업자 아이디를 입력하세요")
	private String bid;
	@NotNull (message="비밀번호를 입력하세요")
	@NotEmpty(message="비밀번호를 입력하세요")
	private String pwd;
	@NotNull (message="이름을 입력하세요")
	@NotEmpty(message="이름을 입력하세요")
	private String name;
	private String phone;
	private String email;
	
	// 캠핑장 정보
	@NotNull (message="캠핑장 이름을 입력하세요")
	@NotEmpty(message="캠핑장 이름을 입력하세요")
	private String cname;
	@NotNull (message="시/도를 입력하세요")
	@NotEmpty(message="시/도를 입력하세요")
	private String address1;	// 전체, 특별시, 도
	@NotNull (message="시/군/구를 입력하세요")
	@NotEmpty(message="시/군/구를 입력하세요")
	private String address2;	// 시, 군
	@NotNull (message="상세주소를 입력하세요")
	@NotEmpty(message="상세주소를 입력하세요")
	private String address3;	// 세부주소
	private String facilities;
	private String image;
	private String content;
	private String category;
	private String chkyn;
}
