<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<!-- =======mypage 공통 CSS=================================== -->
<link href="${path}/resources/css/mypage.css" rel="stylesheet">

<title>탈퇴완료</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header.jsp" flush="true" />
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
                    <a href="/mypage/studentPage" class="list-group-item list-group-item-action px-lg-4">기본
                        정보</a>
                    <a href="/mypage/studentHands/use" class="list-group-item list-group-item-action px-lg-4">손들기
                        내역</a>
                    <a href="/mypage/myQuestion" class="list-group-item list-group-item-action px-lg-4">나의
                        질문글</a>
                    <a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
                        커뮤니티</a>
                    <a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4">1:1
                        문의하기</a>
                    <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4 fw-bold">회원
                        탈퇴</a>
                </div>
			</div>
			<!-- End main nav -->


			<div id="contents" class="col-lg-9">

				<h3 class="fw-bold">회원 탈퇴</h3>
				<br>

				<!-- FROM -->
				<div class="container card p-4" bg-card>
					<div class="content7-9">
						<h2 class="text-center">
							<span class="text-danger">회원탈퇴</span>가 완료되었습니다.
						</h2>
						<br>
						<p class="text-center">보다 나은 오분과외로 다시 만나뵐 수 있기를 바랍니다.</p>
					</div>
				</div>
				<!--End main contents card(박스)-->

			</div>
			<!-- End main contents -->

		</div>
		<!--End Section-->

	</section>
	<!-- End main Section -->

	<!-- ======= End Hero Section ======= -->


	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>
