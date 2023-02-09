// member JavaScript 

function idcheck(type){
    if(document.joinForm.mid.value==""){
        alert("체크할 아이디를 입력해주세요");
        document.joinForm.mid.focus();
        return;
    }	
    var mid = document.joinForm.mid.value;
    var url = "idCheckForm?mid="+mid+"&type="+type;
	var opt = "toolbar=no, menubar=no, resizable=no, width=600, height=400, scrollbars=no";
	window.open(url, "idCheck", opt);
}

function idok(mid){
    opener.document.joinForm.mid.value=mid;
   	opener.document.joinForm.reid.value=mid;
    self.close();
}

function joinCheck(){
    if(document.joinForm.mid.value==""){
        alert('아이디를 입력해주세요');
        document.joinForm.mid.focus();
    } else if(document.joinForm.reid.value != document.joinForm.mid.value){
        alert('아이디 중복확인을 하지 않았습니다.');
        document.joinForm.mid.focus();
    }else if(document.joinForm.pwd.value==""){
        alert('비밀번호를 입력해주세요.');
        document.joinForm.pwd.focus();
    }else if(document.joinForm.pwd.value != document.joinForm.pwd_Check.value){
        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        document.joinForm.pwd.focus();
    }else if(document.joinForm.name.value==""){
        alert('이름를 입력해주세요');
        document.joinForm.name.focus();
    }else if(document.joinForm.mphone.value==""){
        alert('전화번호를 입력해주세요');
        document.joinForm.mphone.focus();
   }else if(document.joinForm.email.value==""){
        alert('이메일을 입력해주세요');
        document.joinForm.email.focus();
    }else {
        document.joinForm.action = "loginForm";
        document.joinForm.submit();
    }
}

function joinChoose(type){
    if(type==1){
        document.contractFrm.contract.value = "일반회원 가입약관";
        document.contractFrm.user.value = 1;
        return;
    } else if(type==2){
        document.contractFrm.contract.value = "사업자회원 가입약관";
        document.contractFrm.user.value = 2;
        return;
    }
    return;
}

function go_next(){	
	console.log(document.contractFrm.user.value);
	if(document.contractFrm.user.value==""){
		alert("가입유형을 선택해 주세요")
		return;
	}
    if(document.contractFrm.okon[1].checked==true){
    alert("회원 약관에 동의 하셔야 회원으로 가입이 가능합니다.")
    }else{
    document.contractFrm.action = "joinForm";
    document.contractFrm.submit();
    }
}

function go_update(){
	    document.updateInfo.action = "camp.do?command=updateUserInfo";
	    document.updateInfo.submit();
}

function reservate_cancel( reseq ){
	    document.reservateInfo.action = "camp.do?command=cancelReservate&reseq="+ reseq;
	    document.reservateInfo.submit();
}

function delete_favorites( fseq ){
	    document.favorites.action = "camp.do?command=deleteFavorites&fseq="+ fseq;
	    document.favorites.submit();
}

