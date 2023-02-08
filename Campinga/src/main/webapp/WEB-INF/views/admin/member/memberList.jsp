<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<article  id="mypage" class="adminPage">
   <h1>회원목록</h1>  
   <form name="frm" method="post">
   <table class="adSearch" style="float:right;"><br><br>
      <tr><td class="searchTd">회원 이름 <input type="text" name="key" value="${key1}">
      <input class="adpage_btn" type="button" value="검색" onclick="go_search('adminMemberList')">
      <input class="adpage_btn" type="button" name="btn_total" value="전체보기 " onClick="go_total('adminMemberList')"></td> </tr>
   </table><br>
   <table class="memberInfoList2">
      <tr>
         <th>아이디</th>
         <th>이름</th>
         <th>전화번호</th>
         <th>이메일</th>
         <th>삭제</th>
      </tr>
       <c:forEach items="${memberList}" var="memberVO">
          <tr>
             <td>${memberVO.mid}</td>
             <td>${memberVO.name}</td>
             <td>${memberVO.mphone}</td>
             <td>${memberVO.email}</td>
             <td><input type="checkbox" name="mid" value="${memberVO.mid}"></td>      
          </tr>
     </c:forEach>
   </table>
    <div style="float:right;" id="mypagebtn">
     <input type="button" value="회원 삭제하기" onClick="go_members_delete();">
    </div><br><br><br>
    <div>
   <jsp:include page="/admin/paging/paging.jsp">   
       <jsp:param value="camp.do?command=adminMemberList" name="command"/>
   </jsp:include>
   </div>
   </form>
</article>


<%@ include file="/admin/footer.jsp"%>