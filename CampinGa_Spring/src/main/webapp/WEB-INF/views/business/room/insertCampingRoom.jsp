<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<article style="height: 1350px;">
	<form method="post" name="campingRoom" action="insertCampingRoom">
		<div class="roomInsert">
			<div class="roomInsert_title">
				<span>객실 등록</span>
			</div>
			<br>
			<div class="roomInput">
				<span>객실 이름</span>
				<div class="roomInput_div">
					<input type="text" name="c_class" size="60" placeholder="객실 이름을 등록하세요.">
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>객실 사진</span><br>
				<div style="text-align: center; margin-top: 15px;">
					<img type="hidden" id="previewImg" name="previewImg" src="images/icon/autoB.png"
					style="width: 300px; height: 200px; border-radius: 10px;">
					<div id="filename"></div>
				</div>
				
				<div class="filebox">
					<form name="formm" id="fileuploadForm" method="post" enctype="multipart/form-data">
		                <input type="button" id="myButton" class="upload-name" value="첨부파일" placeholder="첨부파일">
		                <label for="file">파일찾기</label> 
		                <input type="file" id="file" name="c_image">
	                </form>
            	</div>
				
				
<!-- 				<div class="filebox" style="position:relative; width:500px;"> -->
<!-- 				<form name="formm" id="fileuploadForm" method="post" enctype="multipart/form-data"> -->
<!-- 					<label for="file">파일찾기</label> -->
<!-- 				    <input type="file" name="c_image"> -->
<!-- 				    <input type="button" id="myButton" class="upload-name" value="추가" placeholder="첨부파일"> -->
<!-- 				</form> -->
<!-- 				</div> -->
				
			</div>
			<br>
			<div class="roomInput">
				<span>객실 설명</span>
				<div class="roomInput_div">
				<textarea name="c_content"></textarea>
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>객실 가격</span>
				<div class="roomInput_div">
				<input type="text" name="price" size="20" value="0">
				<span>&nbsp;원</span>
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>기준인원</span>
				<div class="roomInput_div"> 
				<input type="text" name="min_people" size="5" value="0">
				<span>&nbsp;명</span>
				</div>
			</div>
			<br>
			<div class="roomInput">
				<span>최대인원</span>
				<div class="roomInput_div">
				<input type="text" name="max_people" size="5" value="0">
				<span>&nbsp;명</span>
				</div>
			</div>
			<br>
			<div class="buttons">
				<input type="submit" value="객실등록" class="button">
			</div>
		</div>
	</form>
	
</article>

<%@ include file="../../include/footer.jsp"%>