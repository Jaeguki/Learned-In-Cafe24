<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
 
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="update-form" name="updateForm" method="post" action="${pageContext.servletContext.contextPath}/user" >
					<input type="hidden" name="a" value="update">	
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${sessionScope.authUser.name}">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${sessionScope.authUser.email}" disabled>					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<fieldset>
						<legend>성별</legend>
						<c:if test='${sessionScope.authUser.gender eq "male"}'>
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</c:if>
						<c:if test='${sessionScope.authUser.gender eq "female"}'>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</c:if>
					</fieldset>
					
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>