<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
	// 값은 오는데 form 태그 name 설정이 잘못됬나? 연결이 안됨
	opener.frmbs.imagefiles.value='${image}';  // 전송될 히든태그에 이름 삽입
	self.close();
</script>

</body>
</html>