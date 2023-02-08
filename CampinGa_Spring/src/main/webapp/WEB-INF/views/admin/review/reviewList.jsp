<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<article id="mypage" class="adminPage">
	<h1>리뷰 목록</h1>  
	<form name="frm" method="post">
	<table class="adSearch" style="float:right;">
		<tr><td class="searchTd">내용 검색 
			<input type="text" name="key" value="${key1}">
			<input class="adpage_btn" type="button" value="검색" onclick="go_search('adminReviewList')">
			<input class="adpage_btn" type="button" name="btn_total" value="전체보기" onClick="go_total('adminReviewList')">
		</td></tr>
	</table><br>
	<table id="reviewList">
		<tr>
			<th width="80">번호</th>
			<th>내용</th>
			<th>아이디</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
	    <c:forEach items="${reviewList}" var="reviewVO">
		    <tr>
		    	<td>${reviewVO.rseq}</td>
		    	<td>${reviewVO.content}</td>
		    	<td>${reviewVO.mid}</td>
		    	<td><fmt:formatDate value="${reviewVO.indate}"/></td>
		    	<td><input type="checkbox" name="rseq" value="${reviewVO.rseq}"></td>
		    </tr>
	  </c:forEach>
	  <tr>
     <th colspan="4"><a href="#" onClick="go_review_delete();">리뷰 삭제하기</a></th>
	  </tr>
	</table>
	<jsp:include page="/admin/paging/paging.jsp">   
	    <jsp:param value="camp.do?command=adminReviewList" name="command"/>
	</jsp:include>
	</form>
</article>


<%@ include file="/admin/footer.jsp"%>