<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<article>
	<div class="view">
		<h2>회원 가입 약관</h2>
		<form id="join" method="post" name="contractFrm">
			<div class="btns">
				<input type="button" value="일반 회원" onclick="joinChoose(1)">
				<input type="button" value="사업자 회원" onclick="joinChoose(2)">
				<input type="hidden" name="user" value=""> <br>
				<div class="jv">
					<textarea name="contract" rows="15" cols="100" disabled="disabled">
    					가입유형을 선택해주세요
      				</textarea>
				</div>
				<br> <br>
				<div>
					<input type="radio" name="okon"> 동의함 &nbsp; &nbsp; &nbsp; 
					<input type="radio" name="okon" checked="checked"> 동의안함
				</div>
				<input type="button" value="Next" class="submit"
					 					onclick="go_next();" class="btn">
			</div>
		</form>
	</div>
</article>

<%@ include file="../footer.jsp"%>