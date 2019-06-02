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
			<c:param name="pathID" value="${pathID }"/>
		</c:import>
		<div id="wrapper">
			<div id="content">
			<!-- 최신업데이트된 글 -->
				<div class="blog-content">
					<c:set var="postListSize" value="${fn:length(postVOList) }"/>
						<c:choose>
						<c:when test='${postListSize == 0}'>
							<h4>아직 게시된 포스트가 없습니다.</h4>
							<p>첫 글을 작성해주세요!</p>
						</c:when>
						<c:when test='${postVO != null}'>
							<h4><c:out value="${postVO.title}"/></h4>
							<p><c:out value="${postVO.contents}"/></p>
						</c:when>
						<c:otherwise>
							<h4><c:out value="${postVOList[postListSize-1].title}"/></h4>
							<p><c:out value="${postVOList[postListSize-1].contents}"/></p>
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:set var="postListSize" value="${fn:length(postVOList) }"/>
					<c:choose>
						<c:when test='${postListSize == 0}'>
							<p>게시된 포스트가 없습니다.</p>
						</c:when>		
						<c:otherwise>
							<c:forEach var="i" begin="1" end="5">
								<c:if test='${postListSize-i > -1}'>
									<li><a href="${pageContext.request.contextPath}/${blogVO.id}/${postVOList[postListSize-i].categoryNo}/${postVOList[postListSize-i].no}">${postVOList[postListSize-i].title }</a> <span>${postVOList[postListSize-i].regDate }</span>	</li>
								</c:if>
							</c:forEach>
						</c:otherwise>							
					</c:choose>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:set var="categoryListSize" value="${fn:length(categoryVOList) }"/>
			<c:choose>
				<c:when test='${categoryListSize == 0}'>
					<p>아직 개설된 카테고리가 없습니다...^^</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="${categoryListSize }">
						<c:if test='${categoryListSize-i > -1}'>
							<li><a href="${pageContext.request.contextPath}/${blogVO.id}/${categoryVOList[categoryListSize-i].no}">${categoryVOList[categoryListSize-i].name}</a></li>
							
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" >
			<c:param name="title" value="${blogVO.title }"/>
		</c:import>
	</div>
</body>
</html>