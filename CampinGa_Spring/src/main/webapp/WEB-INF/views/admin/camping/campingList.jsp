<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article id="mypage" class="adminPage">
	<h1>캠핑장 정보</h1>
	<form name="frm" method="post">

		<table class="rentList">
			<tr>
				<th>사업자번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>연락처</th>
				<!-- <th>등록일</th> -->
				<th>삭제</th>
			</tr>
			<c:forEach items="${campingList}" var="campingList">
				<tr>
					<td height="2" align="center">${campingList.bseq}</td>
					<td height="2" align="center">${campingList.cname}</td>
					<td height="2" align="center">${campingList.caddress1}
						${campingList.caddress2} ${campingList.caddress3}</td>
					<td height="2" align="center">${campingList.phone}</td>
					<td><input type="checkbox" name="bseq" value="${campingList.bseq}"></td>
				</tr>
			</c:forEach>
		</table>
		<div style="float:right;" id="mypagebtn">
		     	<input type="button" value="삭제" onClick="go_delete();">
		</div>
	</form>

	<div class="clear"></div>

	<jsp:include page="/admin/paging/paging.jsp">
		<jsp:param name="command" value="camp.do?command=adminCampingList" />
	</jsp:include>

</article>





<%@ include file="/admin/footer.jsp"%>