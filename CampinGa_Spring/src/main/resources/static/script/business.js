function busiJoinCheck() {
	if (document.joinForm.name.value == "") {
		alert('캠핑장 사업자님 이름를 입력해주세요');
		document.joinForm.name.focus();
	} else if (document.joinForm.id.value == "") {
		alert('아이디를 입력해주세요');
		document.joinForm.id.focus();
	} else if (document.joinForm.reid.value != document.joinForm.id.value) {
		alert('아이디 중복확인을 하지 않았습니다.');
		document.joinForm.id.focus();
	} else if (document.joinForm.cname.value == "") {
		alert('캠핑장 이름를 입력해주세요');
		document.joinForm.cname.focus();
	} else if (document.joinForm.pwd.value == "") {
		alert('비밀번호를 입력해주세요.');
		document.joinForm.pwd.focus();
	} else if (document.joinForm.pwd.value != document.joinForm.pwd_check.value) {
		alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
		document.joinForm.pwd.focus();
	} else if (document.joinForm.phone.value == "") {
		alert('전화번호를 입력해주세요');
		document.joinForm.phone.focus();
	} else if (document.joinForm.email.value == "") {
		alert('이메일을 입력해주세요');
		document.joinForm.email.focus();
	} else if (document.joinForm.caddress1.value == "0") {
		alert('도/시를 선택해주세요');
		document.joinForm.caddress1.focus();
	} else if (document.joinForm.caddress2.value == "0") {
		alert('시/군을 선택해주세요');
		document.joinForm.email.focus();
	} else if (document.joinForm.caddress3.value == "") {
		alert('상세주소까지 입력해주세요');
		document.joinForm.caddress3.focus();
	} else {
		document.joinForm.action = "camp.do?command=businessJoin";
		document.joinForm.submit();
	}
}


function go_update() {
	document.updateBusinessInfo.action = "camp.do?command=updateBusinessInfo";
	document.updateBusinessInfo.submit();
}

function reservate_cancel() {
	document.reservateInfo.action = "camp.do?command=businessmanCancelReservate";
	document.reservateInfo.submit();
}

function delete_favorites(fseq) {
	document.favorites.action = "camp.do?command=BusinessmanDeleteFavorites&fseq=" + fseq;
	document.favorites.submit();
}


function go_view(qseq) {
	location.href = "camp.do?command=campingQnaForm&qseq=" + qseq;
}

function go_rep() {
	document.frm.action = "camp.do?command=campingQnaRepSave";
	document.frm.submit();
	// 답변 글 등록 & rep 필드를 2로 업데이트
}


function roomCheck() {
	if (document.frm.cname.value == "") {
		alert("이름 필수사항입니다");
		document.frm.cname.focus();
		return false;
	}
	if (document.frm.facilities.value == "") {
		alert("부대시설 필수사항입니다");
		document.frm.facilities.focus();
		return false;
	}
	if (document.frm.category.value == "") {
		alert("카테고리 필수사항입니다");
		document.frm.category.focus();
		return false;
	}
	if (document.frm.content.value == "") {
		alert("내용을 입력해주세요");
		document.frm.content.focus();
		return false;
	}
	if (document.frm.c_class.value == "") {
		alert("숙소종류 입력해주세요");
		document.frm.c_class.focus();
		return false;
	}
	return true;
}





