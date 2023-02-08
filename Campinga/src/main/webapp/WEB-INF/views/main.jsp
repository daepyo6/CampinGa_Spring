<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="mainSearch">
	<div id="main_img">
		<img id="mainImg" src="images/main.png">
	</div>
	<div id="main_searchbar">
		<div id="selects">
			<div id="selectAddress">
				<span>지역별</span> <select id="sido" class="selectAd" onchange="chDo(this.value)">
					<option value="" selected="selected">도/시 선택</option>
					<option value="1">서울</option>
					<option value="2">부산</option>
					<option value="3">대구</option>
					<option value="4">인천</option>
					<option value="5">광주</option>
					<option value="6">대전</option>
					<option value="7">울산</option>
					<option value="8">강원</option>
					<option value="9">경기</option>
					<option value="10">경남</option>
					<option value="11">경북</option>
					<option value="12">전남</option>
					<option value="13">전북</option>
					<option value="14">제주</option>
					<option value="15">충남</option>
					<option value="16">충북</option>
				</select> <select id="gungu" class="selectAd">
					<option value="" selected="selected">시/군/구 선택</option>
				</select>
				<!-- 시/군/구 value는 한글 그대로임 -->
			</div>
			<br>
			<div id="searchName">
				<span>캠핑장명</span> <input type="text" id="inputText" name="cname" size="20"> 
				<input type="button" id="mainBtn" value="검색" onclick="mainSearch()">
			</div>
		</div>
	</div>
</div>
<!-- new 캠핑장 -->
<div class="mainTitle">새로 추가된 캠핑장!!</div>
<div class="newCampings">
	<c:forEach items="${newCampList}" var="newCamp">
		<div class="newCampCard">
			<a href="camp.do?command=campDetail&bseq=${newCamp.bseq}">
				<div class="newCampImgs">
					<c:choose>
						<c:when test="${newCamp.image==null}">
							<img class="campImg" src="images/imgfile.jpg">
						</c:when>
						<c:otherwise>
							<img class="campImg" src="campingImage/${newCamp.image}">
						</c:otherwise>
					</c:choose>
					<div class="imgCover">
						<span>캠핑장정보</span><br> <span>자세히보기</span>
					</div>
				</div>
			</a>
			<div class="newCampName">
				<span>${newCamp.cname}</span>
			</div>
			<div class="newCampInfo">
				<span>${newCamp.content}</span>
			</div>
		</div>
	</c:forEach>
</div>

<!-- 추천 캠핑장 -->
<div id="recoBox">
	<div style="position: relative;" class="mainTitle">이달의 추천 캠핑장!!</div>
	<div class="recoCampings">
		<div class="recoview">
			<!-- recoCampList -->
			<div class="btnA" id="btnL">
				<img src="images/arrL.png">
			</div>
			<div class="cardView">
				<div class="cards">
					<c:forEach items="${recoCampList}" var="recoCamp">
						<div class="recoCampCard" 
							onclick="location.href='camp.do?command=campDetail&bseq=${recoCamp.bseq}'">
							<div class="cardHeader">
								<span>${recoCamp.cname}</span>
							</div>
							<div class="recoImg">
								<img src="campingImage/${recoCamp.image}">
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="btnA" id="btnR">
				<img src="images/arrR.png">
			</div>
		</div>
	</div>
</div>


<%@ include file="footer.jsp"%>
