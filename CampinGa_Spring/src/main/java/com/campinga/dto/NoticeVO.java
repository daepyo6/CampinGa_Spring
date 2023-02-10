package com.campinga.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NoticeVO {
	
	private Integer nseq;
	private String aid;
	private String subject;
	private String content;
	private Timestamp indate;

}
