<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<%@ include file="/business/sub_menu.jsp"%>


<div class="detail_title"><span>캠핑장 정보</span></div>
<div class="myCampInfo">
	<table class="myCampInfo_table">
	<tr>
		<th colspan="2" height="60" style="font-size: 1.3rem; color:navy; border-bottom: 1px solid black;">
		${BusinessmanVO.cname}</th>
	</tr>
	<tr>
		<th height="220">이미지 : </th>
		<td>	
			<c:if test="${BusinessmanVO.image != null}">
				<img id="campInfo_img" src="campingImage/${BusinessmanVO.image}">
			</c:if>
			<c:if test="${BusinessmanVO.image == null}">
				<img id="campInfo_img" src="images/imgfile.png">
			</c:if>
		</td>
	</tr>
	<tr>
		<th height="150">소 개 : </th>
		<td style="line-height: 1.5;">${BusinessmanVO.content}</td>
	</tr>
	<tr>
		<th height="45">주 소 : </th>
		<td>${BusinessmanVO.caddress1}&nbsp;${BusinessmanVO.caddress2}&nbsp;${BusinessmanVO.caddress3}</td>
	</tr>
	<tr height="15"></tr>
	<tr>
		<th height="45">카테고리 : </th>
		<td>${BusinessmanVO.category}</td>
	</tr>
	<tr height="20"></tr>
	<tr>
		<th height="45">부대시설 : </th>
		<td>${BusinessmanVO.facilities}</td>
	</tr>
	<tr height="20"></tr>
	</table>
	<div>
		<input type="button" value="수정하기" class="adpage_btn"
		onclick="location.href='camp.do?command=businessmanCampingListDetail'">
	</div>
</div>

<%@ include file="../../footer.jsp"%>