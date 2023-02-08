<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="camping/campingList.js"></script>
<article>
	<div class="clear"></div>
	<div id="campSearch">
		<input type="text" id="key" placeholder="캠핑장 이름을 입력해주세요"> <a
			href="#" onclick="searchCamp()"><img id="searchBtn"
			src="images/sBtn.png"></a>
	</div>
	<div class="bar"></div>
	<div id="searchResult">
		<c:forEach items="${campList}" var="campVO">
			<div id="campInfo"
					onclick="location.href='camp.do?command=campDetail&bseq=${campVO.bseq}'">
				<c:if test="${campVO.image != null}">
					<img id="campInfo_img" src="campingImage/${campVO.image}">
				</c:if>
				<c:if test="${campVO.image == null}">
					<img id="campInfo_img" src="images/imgfile.png">
				</c:if>
				<div id="campInfo_text">
					<span>${campVO.cname}</span><br> 
					<span> 주소 : ${campVO.caddress1} ${campVO.caddress2}</span><br> 
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						${campVO.caddress3}</span><br> 
					<span>카테고리 : ${campVO.category}</span><br> 
					<span>부대시설 : ${campVO.facilities}</span>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="clear"></div>
</article>
<%@ include file="../footer.jsp"%>