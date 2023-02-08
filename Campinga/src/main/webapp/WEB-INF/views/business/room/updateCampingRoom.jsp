<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<%@ include file="/business/sub_menu.jsp"%>

<article style="height: 1400px">
	<form method="post" name="campingRoom" enctype="multipart/form-data" action="camp.do?command=updateCampingRoom">
		<input type="hidden" name="cseq" value="${campVO.cseq}">
		<input type="hidden" name="oldc_image" value="${campVO.c_image}">			
		<div class="roomInsert">
			<div class="roomInsert_title">
				<span>객실 수정</span>
			</div>
			<br>
			<div class="roomInput">
				<span>객실 이름</span>
				<div class="roomInput_div">
					<input type="text" name="c_class" size="20" value="${campVO.c_class}">
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>현재 객실 사진</span><br>
				<div style="text-align: center; margin-top: 15px;">
					<img src="campingImage/${campVO.c_image}" 
					style="width: 300px; height: 200px; border-radius: 10px;">
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>이미지 파일 선택</span><br>
				<div style="text-align: center; margin-top: 15px;">
					<img id="previewImg" src="images/imgfile.png" 
					style="width: 300px; height: 200px; border-radius: 10px;">
				</div>
				<div class="filebox">
				    <input class="upload-name" value="첨부파일" placeholder="첨부파일">
				    <label for="file">파일찾기</label> 
				    <input type="file" id="file" name="c_image">
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>객실 가격</span>
				<div class="roomInput_div">
				<input type="text" name="price" size="20" value="${campVO.price}">
				<span>&nbsp;원</span>
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>기준인원</span>
				<div class="roomInput_div"> 
				<input type="text" name="min_people" size="5" value="${campVO.min_people}">
				<span>&nbsp;명</span>
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>최대인원</span>
				<div class="roomInput_div">
				<input type="text" name="max_people" size="5" value="${campVO.max_people}">
				<span>&nbsp;명</span>
				</div>
			</div>			
			<br>
			<div class="buttons">
				<input type="submit" value="객실수정" class="button">
			</div>
		</div>
	</form>
</article>

<%@ include file="../../footer.jsp"%>