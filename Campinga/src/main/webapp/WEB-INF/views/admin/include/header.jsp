<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administer</title>
<link href="css/campinga.css" rel="stylesheet">
<script src="admin/script/admin.js"></script>
</head>
<body>

<div id="wrap">
   <header class="adminHeader">         
      <div id="logo">
         <a href="camp.do?command=index">
            <img src="images/logoF.png" width="220" height="50">
         </a>
      </div>
      <div class="adLogout">
      <c:if test="${loginAdmin!=null}">
         <input type="button" value="logout" 
         onClick="location.href='camp.do?command=adminLogout'">
      </c:if>            
      </div>      
   </header>