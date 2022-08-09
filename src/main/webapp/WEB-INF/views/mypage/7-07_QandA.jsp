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
                    <a href="/mypage/studentPage" class="list-group-item list-group-item-action px-lg-4">기본
                        정보</a>
                    <a href="/mypage/studentHands/use" class="list-group-item list-group-item-action px-lg-4">손들기
                        내역</a>
                    <a href="/mypage/myQuestion" class="list-group-item list-group-item-action px-lg-4">나의
                        질문글</a>
                    <a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
                        커뮤니티</a>
                    <a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4 fw-bold">1:1
                        문의하기</a>
                    <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4">회원
                        탈퇴</a>
                </div>
			</div>
			<!-- End main nav -->


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
							<div class="col-6 fs-4 fw-bold">문의합니다.</div>
							<div class="col-3">dkdkd@naver.com</div>
							<div class="col-2">2022-07-21</div>
						</div>

						<div class="contentbox">
							<!-- 문의 내용 글상자(회색) -->

							<div class="content fs-6">
								<!-- 문의 내용 -->
								어떤 점을 문의해야 답변이 되는건지 문의합니다.
							</div>
						</div>
					</div>


					<div class="container answer">
						<!--답변하기 전체박스-->

						<div class="titlebox row">
							<!--제목상자-->
							<div class="col-1 fs-2 fw-bold">A.</div>
							<div class="col-9 fs-4 fw-bold">답변입니다.</div>
							<div class="col-2">2022-07-21</div>
						</div>
						<div class="contentbox">
							<!-- 문의 내용 글상자(회색) -->

							<div class="content fs-6">
								<!-- 문의 내용 -->
								문의하신 글에 대한 답변입니다. 감사합니다.
							</div>
						</div>
					</div>

					<div class="d-grid col-1 mx-auto" style="margin-top: 15px;">
						<a class="btn btn-dark btn-md" href="/mypage/qList"
							role="button">목록</a>
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