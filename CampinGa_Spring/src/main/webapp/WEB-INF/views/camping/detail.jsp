<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bar"></div>
<div id="campingdetail">
	<div id="campMain">
		<div id="campMain_img">
			<c:if test="${campMain.image != null}">
				<img id="campInfo_img" src="campingImage/${campMain.image}">
			</c:if>
			<c:if test="${campMain.image == null}">
				<img id="campInfo_img" src="images/imgfile.png">
			</c:if>
		</div>
		<div id="campMain_info">
			<div id="campMain_info-title">
				<h2>${campMain.cname}</h2>
			</div>
			<label>주소 : </label><span>${campMain.caddress1}&nbsp;${campMain.caddress2}&nbsp;${campMain.caddress3}</span><br>
			<br><label>문의처 : </label><span>${campMain.phone}</span><br>
			<br><label>캠핑장 유형 : </label><span>${campMain.category}</span><br>
			<br><label>운영기간 : </label><span>봄, 여름, 가을, 겨울</span><br>
			<br><label>운영일 : </label><span>평일+주말</span><br>
			<br><label>부대시설 : </label><span>${campMain.facilities}</span><br>
			<br>
			<div id="campMain_info-btns">
			<c:choose>
				<c:when test="${chk_fav == 'y'}">
					<input type="button" value="♥&nbsp;&nbsp;Delete" class="favDel"
						onclick="location.href='camp.do?command=deleteFavorites&bseq=${bseq}'">
				</c:when>
				<c:otherwise>
					<input type="button" value="♡&nbsp;&nbsp;Like" class="favAdd"
						onclick="location.href='camp.do?command=addFavorites&bseq=${bseq}'">
				</c:otherwise>
			</c:choose>
				

			</div>
		</div>
	</div>
	<div id="campDetail">
		<div class="detail_title"><span>캠핑장 소개</span></div>
		<!-- 아래 이미지 3개 DB에서 불러올거라면 수정해야 함 -->
		<div id="campDetail_imgs">
			<img src="images/camp01.jpg" class="campDetail_img" /> <img
				src="images/camp02.jpg" class="campDetail_img" /> <img
				src="images/camp01.jpg" class="campDetail_img" /><br>
		</div>
		<h3>${campMain.content}</h3>
	</div>
</div>