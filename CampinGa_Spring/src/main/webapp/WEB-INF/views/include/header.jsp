<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CampinGa</title>
<link href="css/campinga.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="script/main.js"></script> 
<script src="member/script/member.js"></script>
<script src="camping/script/camping.js"></script>

</head>
<body>

<div id="wrap">
	<header>
	<nav id="top_menu"> <!-- 메인메뉴 시작 -->
		<ul>
			<c:choose>
				<c:when test="${not empty loginUser}">
					<li><a href="camp.do?command=mypage" style="width: 100px;">
						${loginUser.name}(${loginUser.mid})</a></li>
					<li><a href="camp.do?command=logout">Logout</a></li>
				</c:when>
				<c:when test="${not empty loginBusinessman}">
					<li><a href="camp.do?command=businessmanMypage">
						${loginBusinessman.name}(${loginBusinessman.bid})</a></li>
					<li><a href="camp.do?command=logout">Logout</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="camp.do?command=loginForm">Login</a></li>
					<li><a href="camp.do?command=contractForm">Join</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="camp.do?command=noticeList">Notice</a></li>
		</ul>
	</nav>
	
	<div id="logo"><!-- 로고시작 -->
		<a href="camp.do?command=index">
			<img src="images/logoH.png">
		</a>
	</div><!-- 로고 끝 -->
	
	<nav id="category_menu">		
		<div onclick="location.href='camp.do?command=category&kind=1'"><span>ALL</span></div>
		<div class="icons" onclick="location.href='camp.do?command=category&kind=2'">
			<div class="iconImg1">
				<img src="images/icon/autoB.png">
				<span>오토캠핑</span>
			</div>
			<div class="iconImg2">
				<img src="images/icon/autoW.png">
				<span>오토캠핑</span>
			</div>				
		</div>
		<div class="icons" onclick="location.href='camp.do?command=category&kind=3'">
			<div class="iconImg1">
				<img src="images/icon/glamB.png">
				<span>캠핑</span>
			</div>
			<div class="iconImg2">
				<img src="images/icon/glamW.png">
				<span>캠핑</span>
			</div>
		</div>
		<div class="icons" onclick="location.href='camp.do?command=category&kind=4'">
			<div class="iconImg1">
				<img src="images/icon/caraB.png">
				<span>카라반</span>
			</div>
			<div class="iconImg2">
				<img src="images/icon/caraW.png">
				<span>카라반</span>
			</div>
		</div>
		<div class="icons" onclick="location.href='camp.do?command=category&kind=5'">
			<div class="iconImg1">
				<img src="images/icon/picB.png">
				<span>캠프닉</span>
			</div>
			<div class="iconImg2">
				<img src="images/icon/picW.png">
				<span>캠프닉</span>
			</div>
		</div>	
	</nav>
	</header>







