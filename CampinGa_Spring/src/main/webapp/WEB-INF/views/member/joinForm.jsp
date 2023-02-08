<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<article>
   <form method="post" name="joinForm" action="camp.do">      
      <input type="hidden" name="command" value="join">
      <div class="joinTable">
         <h1>회원가입</h1>
         <div class="inputDiv">
         <label>이름</label><input type="text" class="joinInput" name="name" size="20"><br>
         </div>
         <div class="inputDiv">
         <label>아이디</label> 
            <input type="text" class="joinInput" name="id" size="20">
            <input type="button" value="ID중복체크" onclick="idcheck(1);" class="btn">
            <input type="hidden" name="reid">
         </div>                  
         <br>
         <div class="inputDiv">
         <label>비밀번호</label> 
            <input type="password" class="joinInput" name="pwd" size="20">
         </div>
         <br>
         <div class="inputDiv">
         <label>비밀번호 확인</label> 
            <input type="password" class="joinInput" name="pwd_check" size="20"></div>
        <br>
         <div class="inputDiv">
          <label>핸드폰 번호</label> 
            <input type="text" class="joinInput" name="phone" size="20"></div>
         <br>
         <div class="inputDiv">
         <label>이메일</label> 
            <input type="text" class="joinInput" name="email" size="30"></div>         
       <div class="buttons">
         <input type="submit" value="가입하기" onclick="return joinCheck()" class="button">
         <input type="button" value="메인으로" onclick="location.href='camp.do?command=index'" class="button">
         </div>
      </div>
   </form>
</article>

<%@ include file="../footer.jsp" %>