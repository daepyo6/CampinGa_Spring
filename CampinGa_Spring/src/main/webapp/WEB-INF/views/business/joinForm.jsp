<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script src="business/business.js"></script>
<article>
   <form method="post" name="joinForm"> 
      <div class="joinTable">
         <h1>사업자 신청</h1>
         <div class="inputDiv">
         <label>아이디</label>
            <input type="text" class="joinInput" name="id" size="20">
            <input type="button" value="ID중복체크" onclick="idcheck(2);" class="btn">
            <input type="hidden" name="reid">
         </div>   
         <div class="inputDiv">
          <label>사업자 이름</label>
            <input type="text" class="joinInput" name="name" size="20">
         </div>
         <div class="inputDiv">
         <label>캠핑장 이름</label>
            <input type="text" class="joinInput" name="cname" size="20">
         </div>                        
         <div class="inputDiv">
         <label>비밀번호</label>
            <input type="password" class="joinInput" name="pwd" size="20">
         </div>
         <div class="inputDiv">
         <label>비밀번호 확인</label> <br>
            <input type="password" class="joinInput" name="pwd_check" size="20"></div>
         <div class="inputDiv">
         <label>핸드폰 번호</label>
            <input type="text" class="joinInput" name="phone" size="20"></div>
         <div class="inputDiv">
          <label>이메일</label>
            <input type="text" class="joinInput" name="email" size="30"></div>
         <div class="inputDiv">
          <label>캠핑장 주소</label><br><br>
          	&nbsp;&nbsp;
            <select id="sido" name="caddress1" class="selectAd" onchange="chDo(this.value)">
				    <option value="" selected="selected">도/시 선택</option>
		            <option value="1">서울</option>
		            <option value="2">부산</option>
		            <option value="3">대구</option>
		            <option value="4">인천</option>
		            <option value="5">광주</option>
		            <option value="6">대전</option>
		            <option value="7">울산</option>
		            <option value="8">강원</option>
		            <option value="9">경기</option>
		            <option value="10">경남</option>
		            <option value="11">경북</option>
		            <option value="12">전남</option>
		            <option value="13">전북</option>
		            <option value="14">제주</option>
		            <option value="15">충남</option>
		            <option value="16">충북</option>
				</select>
				<select id="gungu" name="caddress2" class="selectAd">
				    <option value="" selected="selected">시/군/구 선택</option>
				</select> <br>          
            <input type="text" name="caddress3" placeholder="상세주소를 입력해주세요">
         </div>  
         <div class="buttons">
      		<input type="button" value="가입하기" onclick="busiJoinCheck()" class="button">
      		<input type="button" value="메인으로" onclick="location.href='camp.do?command=index'" class="button">
        </div>      
      </div>       
   </form>
</article>

<%@ include file="../footer.jsp" %>