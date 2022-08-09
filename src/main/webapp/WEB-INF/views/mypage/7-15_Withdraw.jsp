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
			<div class="row justify-content-md-center">


				<div id="contents" class="col-lg-8">

					<h2 id="withdrawTitle" class="fw-bold text-center">출금신청</h2>

					<div id="withdraw" class="container">

						<!-- 튜터 보유자산 박스-->
						<div class="withdrawInfo">
							<h5 class="text-center" style="color: rgb(103, 102, 102);">(user_id)
								튜터님의 보유자산</h5>
							<div class="d-flex flex-row justify-content-center">
								<p class="ownHands fw-bold col-3">100개 손들기</p>
								<p class="price col-2">= 20,000원</p>
							</div>
						</div>

						<!-- 출금 수량, 개수 입력폼 -->
						<div class="d-flex flex-row justify-content-center" id="account">
							<div class="col-2">
								<label for="address">출금 계좌</label>
							</div>
							<div class="col-10">
								<input type="text" class="form-control" id="address"
									placeholder="출금 계좌번호를 입력해주세요" required>
							</div>
						</div>
						<div class="max_amount">1일 최대 출금 가능 수량: 500개</div>
						<div class="d-flex flex-row justify-content-center" id="amount">
							<div class="col-2">
								<label for="address">출금 수량</label>
							</div>
							<div class="col-10">
								<input type="text" class="form-control" id="address"
									placeholder="최소 출금 수량: 손들기 50개" required>
							</div>
						</div>
						<hr>

						<!-- 출금 수수료 -->
						<div class="row" id="fee">
							<div class="fee col-6">출금 수수료</div>
							<div class="won col-6">2,000원</div>
						</div>

						<!-- 출금 유의사항 -->
						<div class="check">
							<div>
								<i class="bi bi-exclamation-circle-fill" style="float: left;"></i>
								<h6>출금 유의사항을 확인해주세요.</h6>
							</div>
							<ul>
								<li>손들기 1개당 180원으로 환전됩니다.</li>
								<li>출금가능 손들기는 50개 이상이어야 가능합니다.</li>
								<li>출금 신청이 완료되면 취소할 수 없습니다. 신청 전 수량이 맞는지 꼭 확인해주세요.</li>
								<li>은행 서비스 점검시간(23:00~00:30)내에는 출금신청이 어려울 수 있습니다.</li>
								<li>매주 목요일 19:00 이전으로 입금됩니다.</li>
							</ul>
						</div>

						<!-- 취소, 신청 버튼 -->
						<div class="d-flex flex-row justify-content-center">
							<a class="btn btn-outline-dark me-1 col-1" id="cancel"
								href="/mypage/tutorHands/withdraw" role="button">취소</a> <a
								class="btn btn-dark ms-1 col-1" id="apply" type="submit"
								href="/mypage/withdraw/completed">신청</a>
						</div>

					</div>

				</div>
				<!-- End main contents -->
			</div>
		</div>
		<!--End card-->
	</section>
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