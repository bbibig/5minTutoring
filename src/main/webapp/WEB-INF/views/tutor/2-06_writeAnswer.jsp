<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="ko">
<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<link href="/resources/css/1-02_login.css" rel="stylesheet">
<!-- ========================================================= -->

<!-- css 추가 -->
<link rel="stylesheet" href="/resources/css/2-06_writeAnswer.css">

<!-- CKeditor -->
<script type="text/javascript" src="/resources/js/ckeditor/ckeditor.js"></script>

<title>튜터페이지</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<section class="d-flex align-items-center">
		<div class="container">
			<div class="row">
				<div class="col-lg-9">
					<div class="ask_card">

						<!-- question -->
						<hr>
						<h2 class="fas fa-q ask_title">. ${_ONE_Q_.qb_title}</h2>
						<div class="student_info d-flex">

							<div class="sPic">
								<c:if test="${QProfile eq 'false'}"> <img src="/resources/img/profile.png"> </c:if>
								<c:if test="${QProfile eq 'true'}">
									<img src="<spring:url value='/profile/${QNick}_profile.png'/>" class="rounded-circle">
								</c:if>
							</div>

							<div class="Sname">${_ONE_Q_.user_name}</div>
							<div>&nbsp;학생</div>
							<div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_ONE_Q_.regdate}"/></div>
						</div>

						<div class="ask_content">${_ONE_Q_.qb_content}</div>
					</div>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-9 answer">
					<div class="Tutor_info d-flex">
						<div class="TPic">
							<c:if test="${_USER_PROFILE_ eq 'false'}"> <img src="${path}/resources/img/profile.png"> </c:if>
							<c:if test="${_USER_PROFILE_ eq 'true'}">
								<img src="<spring:url value='/profile/${__LOGIN_USER__.user_nick}_profile.png'/>" class="rounded-circle">
							</c:if>
						</div>
						<div class="Tname">${_T_NAME_}</div>
						<div>튜터님, 답변해주세요!</div>
					</div>
					<form action="createAnswer?num=${_ONE_Q_.qb_number}" method="post">
						<input type="hidden" name="qb_number" value="${_ONE_Q_.qb_number}" >
						<input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" >
						<textarea class="form-control" name="a_content" id="contentsA"></textarea>
                            <script>
                              	var ckeditor_config = {
									filebrowserUploadUrl: "upload/img",
									font_defaultLabel : "맑은 고딕/Malgun Gothic",
						            fontSize_defaultLabel : "12",
						            language : "ko",
						            height : 350
						        };
						        CKEDITOR.replace('contentsA', ckeditor_config);
							</script>
						<p></p>
						<input type="submit" value="답변등록" id="save">
					</form>
				</div>
			</div>
	</section>

	</div>

	<!-- ============= 공통 footer + js =============== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<script src="/resources/js/1_01_login.js"></script>
	<!-- ============================================== -->
</body>

</html>