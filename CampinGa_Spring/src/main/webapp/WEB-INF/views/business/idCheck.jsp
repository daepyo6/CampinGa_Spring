<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check</title>
<link href="css/business.css" rel="stylesheet">
<script src="script/campinga.js"></script>
</head>
<body>
   <h1>ID 중복 확인</h1>
   <form method="post" name="idCheckForm" action="BidCheckForm">
      <input type="hidden" name="type" value="${type}">
      ID : <input type="text" name="bid" value="${bid}">
      <input type="submit" value="검색" class="submit"> <br><br><br>
      
      <div>
         <c:if test="${result==1}">
            <script type="text/javascript">
               opener.document.joinForm.bid.value="";
               opener.document.joinForm.reid.value="";
            </script>
            ${bid}는 이미 사용 중인 ID 입니다.
         </c:if>
         <c:if test="${result==-1}">
            ${bid}는 사용 가능한 ID입니다.
            <input type="button" value="사용" class="cancel" onclick="bidok('${bid}')">
         </c:if>
      </div>
   </form>
</body>
</html>