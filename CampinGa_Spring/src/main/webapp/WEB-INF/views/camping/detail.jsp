<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bar"></div>
<div id="campingdetail">
	<div id="campMain">
		<div id="campMain_img">
			<c:if test="${campMain.IMAGE != null}">
				<img id="campInfo_img" src="images/campingImage/${campMain.IMAGE}">
			</c:if>
			<c:if test="${campMain.IMAGE == null}">
				<img id="campInfo_img" src="images/imgfile.png">
			</c:if>
		</div>
		<div id="campMain_info">
			<div id="campMain_info-title">
				<h2>${campMain.CNAME}</h2>
			</div>
			<label>주소 : </label><span>${campMain.CADDRESS1}&nbsp;${campMain.CADDRESS2}&nbsp;${campMain.CADDRESS3}</span><br>
			<br><label>문의처 : </label><span>${campMain.PHONE}</span><br>
			<br><label>캠핑장 유형 : </label><span>${campMain.CATEGORY}</span><br>
			<br><label>운영기간 : </label><span>봄, 여름, 가을, 겨울</span><br>
			<br><label>운영일 : </label><span>평일+주말</span><br>
			<br><label>부대시설 : </label><span>${campMain.FACILITIES}</span><br>
			<br>
			<div id="campMain_info-btns">			
			<c:if test="${loginUser!=null}">
				<c:choose>
					<c:when test="${chk_fav == 'Y'}">
					<input type="button" value="♥&nbsp;&nbsp;Delete" class="favDel" 
						onclick="location.href='deleteFavorites?bseq=${bseq}'">
					</c:when>
					<c:otherwise>
						<input type="button" value="♡&nbsp;&nbsp;Like" class="favAdd"
							onclick="location.href='addFavorites?bseq=${bseq}'">
					</c:otherwise>
				</c:choose>
			</c:if>	

			</div>
		</div>
	</div>
	<div id="campDetail">
		<div class="detail_title"><span>캠핑장 소개</span></div>
		<!-- 아래 이미지 3개 DB에서 불러올거라면 수정해야 함 -->
		<div id="campDetail_imgs">
			<img src="images/campingImage/camp01.jpg" class="campDetail_img" /> <img
				src="images/campingImage/camp02.jpg" class="campDetail_img" /> <img
				src="images/campingImage/camp01.jpg" class="campDetail_img" /><br>
		</div>
		<h3>${campMain.CONTENT}</h3>
	</div>
</div>