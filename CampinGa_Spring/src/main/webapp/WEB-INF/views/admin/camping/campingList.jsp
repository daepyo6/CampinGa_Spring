<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/sub_menu.jsp"%>

<article id="mypage" class="adminPage">
	<h1>캠핑장 정보</h1>
	<form name="frm" method="post">
	   <table class="adSearch" style="float:right;"><br>
      <tr><td class="searchTd">캠핑장 이름 <input type="text" name="key" value="${key}">
      <input class="adpage_btn" type="button" value="검색" onclick="go_search('adminCampingList')">
      <input class="adpage_btn" type="button" name="btn_total" value="전체보기 " onClick="go_total('adminCampingList')"></td> </tr>
   </table><br>

		<table class="rentList">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>연락처</th>
				<th>on / off</th>
			</tr>
			<c:forEach items="${campingList}" var="campingList">
				<tr>
					<td height="2" align="center">${campingList.BSEQ}</td>
					<td height="2" align="center">${campingList.CNAME}</td>
					<td height="2" align="center">
						${campingList.CADDRESS1} ${campingList.CADDRESS2}<br> 
						${campingList.CADDRESS3}</td>
					<td height="2" align="center">${campingList.PHONE}</td>
					<td>
						<input type="checkbox" name="onOff" disabled="disabled" checked="checked"
						onchange="#">											
					</td>
				</tr>
			</c:forEach>
			<!-- on/off : 비지니스에 useyn을 추가해서 가입 승인, 휴면처리를 한 번에 처리 -->
		</table>
		<div style="float:right;" id="mypagebtn">
		     	<input type="button" value="삭제" onClick="go_delete();">
		</div>
	</form>

	<div class="clear"></div>

	<jsp:include page="../paging/paging.jsp">
		<jsp:param name="command" value="adminCampingList" />
	</jsp:include>

</article>

<%@ include file="../include/footer.jsp"%>