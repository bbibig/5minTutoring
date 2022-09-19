<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
	<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
	<jsp:include page="../htmlHead.jsp" flush="true" />
	<!-- =======mypage 공통 CSS=================================== -->
	<link href="${path}/resources/css/mypage.css" rel="stylesheet">

	<title>1:1 문의 목록</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Hero Section ======= -->

	<!-- ======= main Section ======= -->
	<section id="main" class="container">
		<div class="row">

			<div id="nav" class="col-lg-3">
				<ul class="list-group mb-2 bg-dark text-white">
					<li class="list-group-item bg-blue fw-bold fs-5 px-lg-4">마이페이지</li>
				</ul>
				<div class="list-group">
					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentPage"
							class="list-group-item list-group-item-action px-lg-4">기본
							정보</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorPage"
							class="list-group-item list-group-item-action px-lg-4">기본
							정보</a></c:if>

					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentHands/use?group=1"
							class="list-group-item list-group-item-action px-lg-4">손들기
							내역</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorHands/get?group=1"
							class="list-group-item list-group-item-action px-lg-4">손들기
							내역</a></c:if>

					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/myQuestion"
							class="list-group-item list-group-item-action px-lg-4">나의
							질문글</a></c:if>

					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/community/write" 
					class="list-group-item list-group-item-action px-lg-4">나의커뮤니티</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/community/write" 
					class="list-group-item list-group-item-action px-lg-4">나의커뮤니티</a></c:if>


					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/qList" 
					class="list-group-item list-group-item-action px-lg-4 fw-bold">1:1문의하기</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/qList" 
					class="list-group-item list-group-item-action px-lg-4 fw-bold">1:1문의하기</a></c:if>


					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/unregister" 
					class="list-group-item list-group-item-action px-lg-4">회원 탈퇴</a></c:if>
						<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/unregister" 
					class="list-group-item list-group-item-action px-lg-4">회원 탈퇴</a></c:if>
				</div>
			</div><!-- End main nav -->


			<div id="contents" class="col-lg-9">

				<h3 class="fw-bold">1:1 문의하기</h3>
				<br>

				<!-- FROM -->
				<div class="container card p-4" bg-card>

					<div class="container question">
						<!--문의하기 전체박스-->

						<div class="titlebox row">
							<!--제목상자-->
							<div class="col-1 fs-2 fw-bold">Q.</div>
							<div class="col-6 fs-4 fw-bold">${_INQUIRYQUESTION_.iq_title}</div>
							<div class="col-3">${__LOGIN_USER__.user_email}</div>
							<div class="col-2"><fmt:formatDate value="${_INQUIRYQUESTION_.iq_date}" pattern="yyyy.MM.dd" /></div>
						</div>

						<div class="contentbox">
							<!-- 문의 내용 글상자(회색) -->

							<div class="content fs-6">
								<!-- 문의 내용 -->
								${_INQUIRYQUESTION_.iq_content}
							</div>
						</div>
					</div>

				<c:if test="${empty _INQUIRYANSWER_}"> </c:if>
				<c:if test= "${not empty _INQUIRYANSWER_}">
					<div class="container answer">
						<!--답변하기 전체박스-->

						<div class="titlebox row">
							<!--제목상자-->
							<div class="col-1 fs-2 fw-bold">A.</div>
							<div class="col-9 fs-4 fw-bold">${_INQUIRYANSWER_.ia_title}</div>
							<div class="col-2"><fmt:formatDate value="${_INQUIRYANSWER.ia_date}" pattern="yyyy.MM.dd" /></div>
						</div>
						<div class="contentbox">
							<!-- 문의 내용 글상자(회색) -->

							<div class="content fs-6">
								<!-- 문의 내용 -->
								${_INQUIRYANSWER_.ia_content}
							</div>
						</div>
					</div>
				</c:if>
					
					<div class="d-grid col-1 mx-auto" style="margin-top: 15px;">
						<a class="btn btn-dark btn-md" href="/mypage/qList" role="button">목록</a>
					</div>
		
				</div>
				<!--End main contents card(박스)-->

			</div>
			<!-- End main contents -->

		</div>
		<!--End section-->

	</section>
	<!-- End main Section -->

	<!-- ======= End Hero Section ======= -->


	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>