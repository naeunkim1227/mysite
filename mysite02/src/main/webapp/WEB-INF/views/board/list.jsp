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
					action="${pageContext.request.contextPath }/board?b=search"
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


					<c:set var="count" value="${fn:length(list) }" />
					<c:choose>
						<c:when test="${empty kwdlist}">
							<c:forEach items="${list }" var="dto" varStatus="status">
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
						</c:when>
						<c:otherwise>
							<b>${param.kwd} 에 대한 검색결과 입니다.</b>
							<c:set var="count" value="${fn:length(kwdlist) }" />
							<c:forEach items="${kwdlist }" var="dto" varStatus="status">
								<tr>
								<td>${count-status.index}</td>
								<td>
								<a href="${pageContext.request.contextPath }/board?b=view&no=${dto.no}">${dto.title}</a>
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
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${boardcnt != 0}">
						<c:set var="pageBlock" value="1"/>
						<fmt:parseNumber var= "pageCount" integerOnly= "true" value="${boardcnt/pvo.pagesize + (boardcnt % pvo.pagesize == 0? 0:1)}" />
						<fmt:parseNumber var= "startPage" integerOnly= "true" value="${((pvo.currentpage-1)/pageBlock) * pageBlock + 1}" />
						<fmt:parseNumber var= "endPage" integerOnly= "true" value="${startPage + pageBlock-1 }" />
						
						
						<c:if test="${startPage > pageCount}">
							<c:set var="endPage" value="${pageCount}"/> 
						</c:if>
						<c:if test="${startPage > pageBlock}">
							<li><a href="${pageContext.request.contextPath }/board?b=list&pageNum=${startPage-pageBlock}">◀</a></li>
						</c:if>
						<c:forEach begin="${startPage }" end="${endPage}" var="i">
							<li><a href="${pageContext.request.contextPath }/board?b=list&pageNum=${i}">${i}</a></li>
						</c:forEach>	
						
						<c:if test="${endPage < pageCount}">
							<li><a href="${pageContext.request.contextPath }/board?b=list&pageNum=${startPage+pageBlock}"> ▶</a></li>
						</c:if>					
					
						</c:if>
					</ul>
				</div>,
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