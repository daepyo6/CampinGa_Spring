<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>



<article id="mypage">
   <!-- 회원 간단한 정보 + 정보수정 버튼-->
   <form method="post" name="memberInfo">
   
    <div>
      <h1>사용자 정보</h1>
      <table class="memberInfoList1">
         <tr> 
            <th>이름</th>
            <td>${loginUser.NAME}</td>
         </tr>
         <tr> 
            <th>이메일</th>
            <td>${loginUser.EMAIL}</td>
         </tr>
         <tr>
            <th>전화번호</th>  
            <td>${loginUser.MPHONE}</td>
         </tr>
        
      </table>
      </div>
      <div style="float:right;" id="mypagebtn">
            <input type="button" value="정보수정"
                  onclick="location.href='editForm'">
            <input type="button" value="회원탈퇴"
                  onclick="location.href='deleteMember'">      
         </div>         
         
   </form>
	<br><br>
   <!-- "예약 조회" 현재 예약조회, 지난 예약조회-->
   <form method="post" name="reservateInfo">
      <h1>예약 조회</h1>
      <table class="memberInfoList2">
         <tr id="myrest">
            <th>예약일자</th>
            <th>예약장소</th>
            <th>인원</th>
            <th>체크인</th>
            <th>체크아웃</th>
            <th>금액</th>
            <th>예약취소</th>
         </tr>
         <!-- 가짜데이터 이후 c:forEach 사용하여 테이블 조회할 것.--> 
         <c:forEach items="${reList}" var="resVO">  
            <tr>
               <td><fmt:formatDate value="${resVO.RES_DATE}" type="both" 
                     dateStyle="short" timeStyle="short"/></td>
               <td><a href="campDetail?bseq=${resVO.BSEQ}">${resVO.CNAME}&nbsp;${resVO.C_CLASS}</a></td>
               <td>${resVO.PEOPLE}명</td>
               <td> 
            	  <fmt:parseDate value="${resVO.CHK_IN}" var = "check_in" pattern = "yy-MM-dd" />
            	  <fmt:formatDate type="date" value="${check_in}"/>
               </td>
               <td>
            	  <fmt:parseDate value="${resVO.CHK_OUT}" var = "check_out" pattern = "yy-MM-dd" />
            	  <fmt:formatDate type="date" value="${check_out}"/>
               </td>
               <td>${resVO.PRICE}</td> 
               <td><input type="button" value="취소" onclick="reservate_cancel('${resVO.RESEQ}');"></td>
            </tr>
         </c:forEach>
      </table>
      <div class="clear"></div>
   </form>
	<div class="paging" style="font-size:120%; font-weight:bold; text-align: center">
			<c:if test="${paging1.prev}">
				<a href="mypage&page1=${paging1.beginPage-1}#logo">◀</a>&nbsp;
			</c:if>
			<c:forEach begin="${paging1.beginPage}" end="${paging1.endPage}" var="index">
				<c:choose>
					<c:when test="${paging1.page==index}">
						<span style="color:red">${index}&nbsp;</span>
					</c:when>
					<c:otherwise>
						<a href="cmypage?page1=${index}#logo">${index}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${paging1.next}">
				<a href="mypage&page1=${paging1.endPage+1}#logo">▶</a>&nbsp;
			</c:if>
	</div>
   <!-- "내 즐겨찾기" 현재 회원이 즐겨찾기한 캠핑장의 내역을 출력 -->
   <form method="post" name="favorites">
      <h1>내 즐겨찾기</h1>
      <table class="memberInfoList2">
         <tr id="myfav">
            <th>캠핑장이름</th>
            <th>캠핑장 전화번호</th>
            <th>즐겨찾기 해제</th>
         </tr>
         <!-- 가짜데이터 이후 c:forEach 사용하여 테이블 조회할 것.-->
         <c:forEach items="${favorList}" var="favorVO">
            <tr>
               <td><a href="campDetail?bseq=${favorVO.BSEQ}">${favorVO.CNAME}</a></td>
               <td>${favorVO.PHONE}</td> 
               <td><input type="button" value="해제"
                        onclick="delete_favorites( '${favorVO.FSEQ}' );">
               </td>

            </tr>
         </c:forEach>
      </table>
      <div class="clear"></div>
   </form>
   <div class="paging" style="font-size:120%; font-weight:bold; text-align: center">
			<c:if test="${paging2.prev}">
				<a href="mypage&page2=${paging2.beginPage-1}#logo">◀</a>&nbsp;
			</c:if>
			<c:forEach begin="${paging2.beginPage}" end="${paging2.endPage}" var="index">
				<c:choose>
					<c:when test="${paging2.page==index}">
						<span style="color:red">${index}&nbsp;</span>
					</c:when>
					<c:otherwise>
						<a href="mypage&page2=${index}#logo">${index}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${paging2.next}">
				<a href="mypage&page1=${paging2.endPage+1}#logo">▶</a>&nbsp;
			</c:if>
	</div>
</article>
<%@ include file="../../include/footer.jsp"%>