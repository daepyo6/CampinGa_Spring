<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
<div id="noticeview">
   <div class="noticeTitle">
		<h1>공지사항</h1>
	</div> 
	<div class="noticeOne">
		<div class="noticeOne_title">
		 <div><span>제목</span></div>
		 <div><span>${noticeVO.subject}</span></div>
		</div>
		<div class="noticeOne_content">
			<pre>${noticeVO.content}</pre>
		</div>   
	</div>
    <div class="buttons" style="height: 80px; line-height: 80px;">
    	<input class="btn"  type="button" value="수정" onClick="go_mod('${noticeVO.nseq}')">
		<input class="btn"  type="button" value="삭제" onClick="go_dod('${noticeVO.nseq}')">
		<input class="btn"  type="button" value="목록" onClick="go_mov()">    	
    </div>   
   <div class="buttons">   
</div>
</article>

<%@ include file="/admin/footer.jsp"%>