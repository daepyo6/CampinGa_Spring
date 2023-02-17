<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/campinga.css" >
<script src="/script/campinga.js"></script>

</head>
<body>
<article style="text-align: center;">
<br><h1>이미지 등록</h1>  
<form name="frm" action="bsCampingImgs" style="margin: 20px auto;">
	<table class="bannerTable" style="width: 650px; height: 400px;
		margin-left:auto; margin-right:auto;">
		<tr>
			<th width="100px" height="350px">배너 이미지</th>
	    	<td style="vertical-align:top; text-align: center">
	   			
	   		</td>
	   	</tr>
	   	<tr>
	   		<td colspan="2" height="50px"></td>
	   	</tr>	  
	</table>
	<input class="banBtn" type="submit" value="이미지 등록" style="margin-right: 40px;">
</form>
	
	<div style="position:relative; top:-130px; left: -100px; width: 600px; margin-left:200px;">
		<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
			<input type="file" name="fileimage">
			<input style="margin-left: 20px" type="button" id="fileAddBtn" value="이미지 업로드">
		</form>
	</div>
	
</article>

</body>
</html>