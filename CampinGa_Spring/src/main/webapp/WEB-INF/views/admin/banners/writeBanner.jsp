<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/sub_menu.jsp"%>
<article style="text-align: center;">
<h1>배너 등록</h1>  
<form name="frm" action="adminBannerWrite" style="margin: 20px auto;">
	<table class="bannerTable">
		<tr><th>제목 </th><td width="642" ><input type="text" name="name" size="47" value="${dto.name}"></td></tr>
		<tr><th>순위 </th>
			<td width="642">
				<select name="order_seq">
					<option value="">디스플레이될 순서를 선택하세요 </option>
					<option value="1">1</option><option value="2">2</option><option value="3">3</option>
					<option value="4">4</option><option value="5">5</option><option value="6">사용안함 </option>
				</select>
			</td></tr>
		<tr height="250"><th>배너 이미지</th>
	    	<td width="650" style="vertical-align:top;">
	   			<input type="hidden" name="image" id="image" value="${dto.image}">
	   			<div id="filename">
	   				<c:if test="${dto.image!=null}">
	   				fileName : ${dto.image}
	   				</c:if>
	   			</div>
	   		</td>
	   	</tr>	    
	</table>
	<c:if test="${message!=null}">
	 <div id="errorMsg" style="color:red;">ERROR : ${message}</div>
	</c:if>
	<input class="btn" type="submit" value="배너등록">   
	<input class="btn" type="button" value="취소" onClick="location.href='adminBannerList'">
	</form>
	
	<div style="position:relative; top:-80px; ">
	<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
		<input type="file" name="fileimage"><input type="button" id="fileAddBtn" value="이미지 업로드">
	</form>
	</div>
	
</article>
<%@ include file="../include/footer.jsp" %>