<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<script src="business/business.js"></script>
<%@ include file="/business/sub_menu.jsp"%>
<article id="mypage" class="adminPage">
  <div class="detail_title"><span>Q&amp;A</span></div>
  <table id="reviewList">
		<tr>
			<th>번호(답변여부)</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>

		<c:forEach items="${qnalist}" var="camp_qnaVO">
			<input type="hidden" name="bseq" value="${bseq}">
			<tr>
				<td>${camp_qnaVO.qseq}<c:choose>
						<c:when test='${camp_qnaVO.repyn=="n"}'>(미처리)</c:when>
						<c:otherwise>(답변처리완료)</c:otherwise>
					</c:choose></td>
				<td><a href="#" onClick="go_view('${camp_qnaVO.qseq}');">${camp_qnaVO.content}</a></td>
				<td>${camp_qnaVO.mid}</td>
				<td><fmt:formatDate value="${camp_qnaVO.indate}" /></td>
			</tr>		
		</c:forEach>
	</table>
	<br>

<jsp:include page="/business/paging/paging.jsp">
	<jsp:param name="command" value="camp.do?command=campingQna" />
</jsp:include>


</article>







<%@ include file="../../footer.jsp"%>