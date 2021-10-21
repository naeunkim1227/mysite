<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="get" action="${pageContext.request.contextPath }/board">
					<input type = "hidden" name = "b" value="rewrite">
					<input type = "hidden" name = "no" value="${param.no}">
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="" required></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content" required="required"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
					<c:choose>
						<c:when test="${userno == null} ">
							로그인 후 작성이 가능합니다. 유의하세요.
						</c:when>
						<c:otherwise>
							<input type="submit" value="등록">
						</c:otherwise>
					</c:choose>
						<a href="${pageContext.request.contextPath }/board">취소</a>
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>