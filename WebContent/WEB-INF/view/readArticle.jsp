<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><title>게시글 읽기</title></head>
<body>
	<table border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>${articleData.article.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${articleData.article.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value="${articleData.article.title}"></c:out></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${articleData.content}</td>
		</tr>
	</table>
</body>
</html>