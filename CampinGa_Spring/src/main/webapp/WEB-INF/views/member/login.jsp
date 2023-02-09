<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<article>
    <form method="post" action="login" name="loginFrm" id="loginTable">
      <div class="login">
      <h2>로그인</h2>
      <fieldset>
         <legend></legend>
         <div class="login_id">
            <label>User ID</label><input name="mid" type="text"><br> 
         </div>
         <div class="login_pwd">
              <label>Password</label><input name="pwd" type="password"><br>
          </div>    
          <div class="login_radio">
               <input type="radio" name="user" value="1" checked="checked"/>개인 &nbsp; &nbsp; 
                <input type="radio" name="user" value="2"/>사업자
          </div>
      </fieldset>
      <div class="buttons">
           <input type="submit" value="login" class="button">
           <input type="button" value="join" class="button" 
              onclick="location.href='contractForm'">  
       </div><br><br>
       <div>&nbsp;&nbsp;&nbsp;${message}</div>  
   </div>
   </form>
   
</article>


<%@ include file="../include/footer.jsp" %>