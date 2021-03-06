<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">

				<form id="search_form"
					action="${pageContext.request.contextPath }/board?b=list"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left;">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>


					<c:set var="count" value="${fn:length(map.list) }" />
							<c:forEach items="${map.list }" var="dto" varStatus="status">
								<c:choose>
									<c:when test="${dto.is_del == 'true'}">
										<tr>
											<td>${count-status.index}</td>
											<td colspan="5">글이 삭제 되었습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${count-status.index}</td>
											<td style="text-align: left; padding-left: ${20*dto.depth}px">
												<c:if test="${dto.depth != 0}">
													<img
														src="${pageContext.request.contextPath }/assets/images/reply.png">
												</c:if> <a
												href="${pageContext.request.contextPath }/board?b=view&no=${dto.no}">${dto.title}</a>
											</td>
											<td>${dto.name}</td>
											<td>${dto.hit }</td>
											<td>${dto.reg_date }</td>
											<c:choose>
												<c:when test="${authUser.no == null}">
													<td><a
														href="${pageContext.request.contextPath }/user?a=loginform">로그인
															후 삭제 가능</a></td>
													</td>
												</c:when>
												<c:otherwise>
													<td><a
														href="${pageContext.request.contextPath }/board?b=delete&userno=${authUser.no}&no=${dto.no}"
														class="del">삭제</a></td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${map.prevPage > 0 }" >
							<li><a href="${pageContext.request.contextPath }/board?p=${map.prevPage }&kwd=${map.keyword }">◀</a></li>
						</c:if>
						
						<c:forEach begin="${map.beginPage }" end="${map.beginPage + map.listSize - 1 }" var="page">
							<c:choose>
								<c:when test="${map.endPage < page }">
									<li>${page }</li>
								</c:when> 
								<c:when test="${map.pageNum == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise> 
									<li><a href="${pageContext.request.contextPath }/board?p=${page }&kwd=${map.keyword }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${map.nextPage > 0 }" >
							<li><a href="${pageContext.request.contextPath }/board?p=${map.nextPage }&kwd=${map.keyword }">▶</a></li>
						</c:if>	
					</ul>
				</div>				
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?b=writeform"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>