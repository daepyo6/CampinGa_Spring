<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article id="mypage" class="adminPage">
	<h1>공지사항 수정</h1>
	<div class="noticeWrite">
	<form name="frm" method="post">
	<input type="hidden" name="nseq" value="${noticeVO.nseq}">
	<div class="writeSubject">		
		<div><span>제 목</span></div>
		<div>
       		<input type="text" name="subject" maxlength="100" value="${noticeVO.subject}">
       	</div>
	</div>
	<div class="writeContent">
   		<div><span>내 용</span></div>
   		<div>
   			<textarea name="content">${noticeVO.content}</textarea>
   		</div>			
	</div>
	<div class="writeBtns">
		<input type="button" value="수정" onClick="go_mod_save()">
		<input type="button" value="취소"
			onClick="location.href='camp.do?command=adminNoticeDetail&nseq=${noticeVO.nseq}'">		
	</div>
	</form>
	</div>
</article>







<%@ include file="/admin/footer.jsp"%>