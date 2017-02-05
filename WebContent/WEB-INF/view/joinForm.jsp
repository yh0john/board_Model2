<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head><title>회원 가입</title></head>
<body>
	<p>
		아이디:<br/><input type="text" name="id" value="${param.id}">
		<c:if test="${errors.id}">ID를 입력하세요</c:if>
		<c:if test="${errors.duplicated}">이미 사용중인 아이디입니다.</c:if>
	</p> 
	<p>
		이름:<br/><input type="text" name="name" value="${param.name}">
	</p>
	<p>
		비밀번호:<br/>
	</p>
</body>
</html>