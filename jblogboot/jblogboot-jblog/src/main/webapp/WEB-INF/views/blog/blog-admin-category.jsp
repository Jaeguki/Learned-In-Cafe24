<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" >
			<c:param name="title" value="${blogVO.title }"/>
		</c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/includes/blog-navigation.jsp">
				<c:param name="menu" value="category"/>
			</c:import>
			
			
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
			      		<c:set var="count" value="${fn:length(categoryVOList) }"/>
						<c:forEach items="${categoryVOList }" var="categoryVO" varStatus="status">
							<tr>
								<td>${status.index +1 }</td>
								<td name = "name" >${categoryVO.name }</td>
								<td name = "postCnt">${categoryVO.postCnt }</td>
								<td name = "description">${categoryVO.description }</td>
															
								<td>
								<form action="${pageContext.servletContext.contextPath }/${blogVO.id }/admin/category/delete" method="post">
								<input type="hidden" name="no" value="${categoryVO.no}">
								<input type="IMAGE" src="${pageContext.request.contextPath}/assets/images/delete.jpg" >
								</form>
								</td>
							
							</tr>
						</c:forEach>
					
				</table>
      			<form action="${pageContext.servletContext.contextPath }/${blogVO.id}/admin/category" method="post">
					<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" >
			<c:param name="title" value="${blogVO.title }"/>
		</c:import>
	</div>
</body>
</html>