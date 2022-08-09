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

<title>탈퇴하기</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Hero Section ======= -->

	<section id="main" class="container">

		<div class="container">

			<!-- <div class="row"> -->
			<div class="completed row justify-content-md-center">
				<i class="fas fa-circle-check fa-4x"></i>
				<div class="row justify-content-md-center">
					<p class="col-md-auto">
						출금 신청이 완료되었습니다.<br> 상세 내역은 마이페이지에서 확인해주세요.
					</p>
				</div>
				<div class="d-flex flex-row justify-content-center">
					<a class="btn btn-outline-dark me-1 col-1"
						href="/mypage/tutorHands/withdraw" role="button">확인</a>
				</div>
				<!-- <div class="row justify-content-md-center">
        <ul class="col-md-auto" style="margin-top: 10px; padding-left: 50px">
          <li>금액: 40,000원</li>
          <li>계좌번호: 0000-0000-0000</li>
          <li>출금신청일: 2022-07-28</li>
        </ul>
       </div> -->
			</div>
			<!-- </div> -->

		</div>
		</div>
		<!--End card-->
	</section>
	<!-- End main Section -->
	<!-- End main Section -->


	<!-- ======= End Hero Section ======= -->


	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>

<!-- ======= footer + js  ======= -->
<jsp:include page="../footer.jsp" flush="true" />
<!-- ============================= -->

</body>

</html>