<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<script src="member/script/member.js"></script>

<article>
<form method="post" name="updateInfo">
   <input type="hidden" name="command" value="updateUserInfo" />
    <div class="joinTable">
    <h2>사용자 정보 수정</h2><br>
   <fieldset>
      <div class="inputDiv">
      <label>아이디</label><input type="text" name="mid" value="${loginUser.mid}" readonly><br>
      </div>
      <div class="inputDiv">
        <label>이름</label><input type="text" name="name"  value="${loginUser.name}"><br> 
        </div>
        <div class="inputDiv">
        <label>전화번호</label><input  type="text" name="mphone"  value="${loginUser.mphone}"><br>
        </div>
        <div class="inputDiv">
        <label>이메일</label><input type="text" name="email" value="${loginUser.email}">
      </div>   
   </fieldset><br><br>
   <div id="mypagebtn">
      <input type="button" value="정보수정" class="submit" onclick="go_update();"> 
      <input type="reset" value="취소" class="cancel">
   </div>
   </div>
</form>
</article>

<%@ include file="../../footer.jsp" %>