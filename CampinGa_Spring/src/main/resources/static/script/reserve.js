function go_reserve() {
	var input = confirm('예약하시겠습니까?');

	if (parseInt(document.reserve.people.value) > parseInt(document.reserve.max_people.value)) {
		alert('예약가능한 최대 인원 수 보다 많습니다.');
		document.reserve.people.focus();
		input = false;
	}

	if (input) {
		document.reserve.action = "camp.do?command=reserveInsert";
		document.reserve.submit();

	}
}