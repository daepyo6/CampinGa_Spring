<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
 <%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article id="mypage" class="adminPage">
	<h1>공지사항 작성</h1>
	<div class="noticeWrite">
	<form name="frm" method="post">
	<input type="hidden" name="aid" value="${loginAdmin}">
	<div class="writeSubject">		
		<div><span>제 목</span></div>
		<div>
       		<input type="text" name="subject" maxlength="100" placeholder="제목을 입력해주세요.">
       	</div>
	</div>
	<div class="writeContent">
   		<div><span>내 용</span></div>
   		<div>
   			<textarea name="content" placeholder="공지사항 내용을 입력해주세요."></textarea>
   		</div>			
	</div>
	<div class="writeBtns">
		<input type="button" value="등록" onClick="go_save()">           
		<input type="button" value="목록" onClick="go_mov()"> 
	</div>
	</form>
	</div>
</article>

<%@ include file="/admin/footer.jsp"%>