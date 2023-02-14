<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/sub_menu.jsp"%>
<script type="text/javascript">
$(function(){
	$('#fileAddBtn').click( function(){
		var formselect = $("#fileupForm")[0];  
		var formdata = new FormData(formselect); 
		$.ajax({ 
			url:"<%=request.getContextPath() %>/fileup", 
			type:"POST",
			enctype:"multipart/form-data",
			async: false,
			data: formdata,
	    	timeout: 10000,
	    	contentType : false,
	        processData : false,
	        success : function(data){
	            if(data.STATUS == 1){
	            	$("#filename").empty();
	            	$("#filename").append("<div>"+data.FILENAME+"</div>");
	            	$("#image").val(data.FILENAME);
	            	$("#filename").append("<img src='images/"+data.FILENAME+"' height='150'/>");
	            }
	        },
	        error: function() {	alert("파일 업로드 실패");}
		});
	});
});

function bannerUpdate(){
	let name = document.frm.name;
	let newimg = document.frm.image;
	let oldimg = document.frm.oldimage;
	if(name.value==""){
		alert("배너 제목을 작성해주세요");
		name.focus();
		return
	}else if(newimg.value==""){
		if(confirm("이전 이미지를 사용합니다.")){
			document.frm.submit();
		}else{
			return;
		}
	}
	document.frm.submit();
}
</script>
<article>
<h1>배너 등록</h1>  
<form name="frm" method="post" action="adminBannerUpdate">
	<table id="list">
		<tr>
			<th>번호</th>
			<td width="642" >
				${banner.MBSEQ}<input type="hidden" name="mbseq" size="47" value="${banner.MBSEQ}">
			</td>
		</tr>
		<tr>
			<th>제목 </th>
			<td width="642" >
				<input type="text" name="name" size="47" value="${banner.NAME}">
			</td>
		</tr>
		<tr><th>순위 </th>
			<td width="642">
				<select name="order_seq" >
					<c:forEach var="cnt" begin="1" end="5" varStatus="status">
				    	<c:choose>
							<c:when test="${cnt==banner.ORDER_SEQ}">
								<option value="${cnt}" selected>${cnt}</option>
							</c:when>
							<c:otherwise>
								<option value="${cnt}" >${cnt}</option>
							</c:otherwise>
						</c:choose>
				    </c:forEach>
				    <c:choose>
						<c:when test="${banner.ORDER_SEQ==6}">
							<option value="6" selected>사용안함</option>
						</c:when>
						<c:otherwise>
							<option value="6" >사용안함</option>
						</c:otherwise>
						</c:choose>
			    	</select>
			</td>
		</tr>
		<tr><th>사용유무</th><td>${banner.USEYN}</td></tr>
		<tr><th>등록일</th><td><fmt:formatDate value="${banner.INDATE}"/></td></tr>
		<tr height="200">
			<th>이전 배너 이미지</th>
	    	<td width="642" style="vertical-align:top;">
	   			<input type="hidden" name="oldimage" value="${banner.IMAGE}">
	   			<img src="images/${banner.IMAGE}" height="150">
	   			<div>${banner.IMAGE}</div>
	   		</td>
	   	</tr>
		<tr height="250">
			<th>새 배너 이미지</th>
	    	<td width="642" style="vertical-align:top;">
	   			<input type="hidden" name="image" id="image" value="">
	   			<div id="filename"></div>
	   		</td>
	   	</tr>
	</table>
	<input class="btn" type="button" value="등록" onClick="bannerUpdate()">   
	<input class="btn" type="button" value="취소" onClick="location.href='adminBannerList'">
	</form>
	
	<div style="position:relative; top:-80px; ">
	<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
		<input type="file" name="fileimage"><input type="button" id="fileAddBtn" value="이미지 업로드">
	</form>
	</div>
	
</article>
<%@ include file="../include/footer.jsp" %>