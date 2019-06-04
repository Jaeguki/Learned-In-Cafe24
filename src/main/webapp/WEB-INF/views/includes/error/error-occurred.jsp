<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Oooops!</h1>
	<p>
		죄송합니다. <br>
		올바르지 않은 접근입니다.<br>
		<br>
		자신의 홈페이지 이외의 홈페이지는 관리 할 수 없습니다.<br>
		<br>
		<a href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/basic">내 관리 페이지로 돌아가기</a>
	</p>
</body>
</html>