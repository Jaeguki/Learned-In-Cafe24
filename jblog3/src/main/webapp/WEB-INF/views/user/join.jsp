<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$('#id').change(function(){
		$('#id-check-button').show();
		$('#img-checkID').hide();
	});
	
	$('#id-check-button').click(function(){
		var id = $('#id').val();
		if(id == ''){
			return;
		}
			
		/* ajax 통신 */
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/user/api/checkID?id=" + id, 
			type: "get", 
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success"){
					console.log(response);
					//console.error(response.message);
					return;
				}
				
				if(response.data == true){
					alert('이미 존재하는 ID입니다.\n다른 ID 사용해 주세요.');
					$("#id").focus();
					$("#id").val("");
					return;
				}

				$('#id-check-button').hide();
				$('#img-checkID').show();
				
			},
			error: function(xhr, error){
				console.error("error:" + error)
			}
		});
		
		console.log(id);
	});	
});
</script>

</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<form:form
		modelAttribute="userVO"
		class="join-form"
		id="join-form"
		method="post"
		action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="">
			<spring:hasBindErrors name="userVO">
			    <c:if test="${errors.hasFieldErrors('name') }">
					<p style="font-weight:bold; color:red; text-align:left; padding:0">
			            <spring:message 
				     		code="${errors.getFieldError( 'name' ).codes[0] }" 				     
				     		text="${errors.getFieldError( 'name' ).defaultMessage }" />
			        </p> 
			   </c:if>
		   </spring:hasBindErrors>
			<label class="block-label" for="id">아이디</label>
			<input id="id" name="id" type="text"> 
			<input id="id-check-button" type="button" value="id 중복체크">
			<img id="img-checkID" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<spring:hasBindErrors name="userVO">
			    <c:if test="${errors.hasFieldErrors('id') }">
					<p style="font-weight:bold; color:red; text-align:left; padding:0">
			            <spring:message 
				     		code="${errors.getFieldError( 'id' ).codes[0] }" 				     
				     		text="${errors.getFieldError( 'id' ).defaultMessage }" />
			        </p> 
			   </c:if>
		   </spring:hasBindErrors>
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<spring:hasBindErrors name="userVO">
			    <c:if test="${errors.hasFieldErrors('password') }">
					<p style="font-weight:bold; color:red; text-align:left; padding:0">
			            <spring:message 
				     		code="${errors.getFieldError( 'password' ).codes[0] }" 				     
				     		text="${errors.getFieldError( 'password' ).defaultMessage }" />
			        </p> 
			   </c:if>
			</spring:hasBindErrors>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>
			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
