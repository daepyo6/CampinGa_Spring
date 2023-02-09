// member JavaScript 


function loginCheck(){
   if(document.loginFrm.id.value==""){
      alert("아이디는 필수입력사항입니다");
      document.loginFrm.id.focus();
      return false;
   }else if(document.loginFrm.pwd.value==""){
      alert("비밀번호는 필수입력사항입니다");
      document.loginFrm.pwd.focus();
      return false;
   }else if(document.loginFrm.user.value==""){
      alert("개인/사업자 체크는 필수입력사항입니다");
      document.loginFrm.user.focus();
      return false;
   }else{
      return true; 
   }   
}

function idcheck(type){
    if(document.joinForm.id.value==""){
        alert("체크할 아이디를 입력해주세요");
        document.joinForm.id.focus();
        return;
    }	
    var id = document.joinForm.id.value;
    var url = "camp.do?command=idCheckForm&id="+id+"&type="+type;
	var opt = "toolbar=no, menubar=no, resizable=no, width=600, height=400, scrollbars=no";
	window.open(url, "idCheck", opt);
}

function idok(id){
    opener.document.joinForm.id.value=id;
   	opener.document.joinForm.reid.value=id;
    self.close();
}

function joinCheck(){
    if(document.joinForm.id.value==""){
        alert('아이디를 입력해주세요');
        document.joinForm.id.focus();
    } else if(document.joinForm.reid.value != document.joinForm.id.value){
        alert('아이디 중복확인을 하지 않았습니다.');
        document.joinForm.id.focus();
    }else if(document.joinForm.pwd.value==""){
        alert('비밀번호를 입력해주세요.');
        document.joinForm.pwd.focus();
    }else if(document.joinForm.pwd.value != document.joinForm.pwd_Check.value){
        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        document.joinForm.pwd.focus();
    }else if(document.joinForm.name.value==""){
        alert('이름를 입력해주세요');
        document.joinForm.name.focus();
    }else if(document.joinForm.phone.value==""){
        alert('전화번호를 입력해주세요');
        document.joinForm.phone.focus();
   }else if(document.joinForm.email.value==""){
        alert('이메일을 입력해주세요');
        document.joinForm.email.focus();
    }else {
        document.joinForm.action = "camp.do";
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
    document.contractFrm.action = "camp.do?command=joinForm";
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

