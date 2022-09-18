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

					<a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
						커뮤니티</a>
						
					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/qList" 
					 		class="list-group-item list-group-item-action px-lg-4 fw-bold">1:1 문의하기</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/qList" 
					 		class="list-group-item list-group-item-action px-lg-4 fw-bold">1:1 문의하기</a></c:if>

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

					<!--문의하기 안내-->
					<div class="info">
						<p class="fs-5">
							안녕하세요 오분과외입니다.<br> 도움이 필요하신가요?<br> 문의를 남겨주시면 자세히
							안내해드리겠습니다.
						</p>
						<div class="d-grid col-2 mx-auto">
							<a class="btn btn-dark btn-lg" href="/inquiry" role="button">문의하기</a>
						</div>
					</div>

					<!-- 문의내역 리스트 -->
					
					<div class="list">
						<c:forEach var="myinquiry" items="${_MYINQUIRY_}">
						<hr>
							<div class="row"> 
								<i class="col-1 fas fa-q fa-2x" style="color: rgb(56, 105, 239); text-align: center;"></i>
								<div class="col-9">
									<a href="/mypage/question" id=qtitle> ${myinquiry.iq_title}</a>
									<div class="fs-6">
										<fmt:formatDate value="${myinquiry.iq_date}" pattern="yyyy.MM.dd" />
									</div>
								</div>
							<!-- <c:set  var="iq_pass" value="Y" /> -->	
								<c:choose>
									<c:when test="${myinquiry.iq_pass eq 'Y'}">
										<div class="col-2 state2">답변 완료</div>
									</c:when>
									<c:when test="${myinquiry.iq_pass eq 'N'}">
										<div class="col-2 state1">답변 대기</div>
									</c:when>
								</c:choose>
							</div>
						</c:forEach>
					</div>
					<!--문의리스트 end-->

				</div>
				<!-- FORM -->

			</div>
			<!--End main contents card(박스)-->

  		<nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center p-5">
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/qList?currPage=1">&laquo;</a></li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/qList?currPage=${_MYINQUIRYPAGENATION_.cri.currPage - 1}">&lt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle bg-blue"
                href="/mypage/qList?currPage=${_MYINQUIRYPAGENATION_.cri.currPage}">${_MYINQUIRYPAGENATION_.cri.currPage}</a>
            </li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/qList?currPage=${_MYINQUIRYPAGENATION_.cri.currPage + 1}">&gt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="mypage/qList?currPage=${_MYINQUIRYPAGENATION_.realEndPage}">&raquo;</a></li>
          </ul>
        </nav>


		</div>
		<!-- End main contents -->

		</div>
		<!--End section-->

	</section>
	<!-- End main Section -->




	<!-- End Hero -->



	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>