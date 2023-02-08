<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="/business/sub_menu.jsp"%>

<script src="business/business.js"></script>
<article>
<form method="post" name="updateBusinessInfo">
	<input type="hidden" name="command" value="updateBusinessInfo" />
	<div class="joinTable">
	<h2>사업자 정보 수정</h2> <br><br>
	<fieldset>
		<div class="inputDiv" style="padding-left: 30px;">
		<label>User ID </label><input type="text" name="bid" value="${loginBusinessman.bid}" readonly><br>
		</div>
		<div class="inputDiv" style="padding-left: 30px;">
        <label>Name </label><input type="text" name="name"  value="${loginBusinessman.name}"><br> 
		</div>
		<div class="inputDiv" style="padding-left: 30px;">
        <label>Phone Number </label><input  type="text" name="phone"  value="${loginBusinessman.phone}"><br>
		</div>
		<div class="inputDiv" style="padding-left: 30px;">
        <label>E-Mail </label><input type="text" name="email" value="${loginBusinessman.email}">
		</div>
	</fieldset><br><br>
	<div id="mypagebtn">
		<input type="button" value="정보수정" class="submit" onclick="go_update();"> 
		<input type="reset" value="취소" class="cancel" onclick="location.href='camp.do?command=businessmanMypage'">
	</div>
	</div>
</form>
</article>

<%@ include file="../../footer.jsp" %>