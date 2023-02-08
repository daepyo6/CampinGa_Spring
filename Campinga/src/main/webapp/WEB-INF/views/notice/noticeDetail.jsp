<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="bar"></div>
<article >
<div id="noticeview" >
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
    	<input style="vertical-align: middle;" class="btn"  type="button" value="목록" onClick="location.href='camp.do?command=noticeList'">
    </div>
</div>
</article>
<%@ include file="../footer.jsp"%>