<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head><title>게시글 등록</title></head>
<body>
	게시글을 등록 했습니다.
	<br>
	${ctxPath = pageContext.request.contextPath;}
	<a href="${ctxPath}/article/list.do">[게시글 목록보기]</a>
	<a href="${ctxPath}/article/read.do?no=${newArticleNo}">[게시글 목록보기]</a>
</body>
</html>
