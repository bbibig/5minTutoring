<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!doctype html>
	<html lang="ko">
	<head>
		<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
		<jsp:include page="../htmlHead.jsp" flush="true" />
		<link href="/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
		<link href="/resources/css/2-02_tutorpage.css" rel="stylesheet">
		<!-- ========================================================= -->

		<title>튜터페이지-튜터정보</title>
	</head>
	<body>
		<!-- ============= 공통 Header : 로그인 후 =================== -->
		<jsp:include page="../header_login.jsp" flush="true" />
		<!-- ========================================================= -->

		<!-- ======= Section ======= -->
		<section class="d-flex">
			<div class="container">
				<div class="row">
				
					<!-- 프로필 카드 : 프로필사진 + 이름 + 한줄소개 + 대표과목 + 누적답변 + 평점 -->
					<div class="left-section col-3">
						<div class="card d-flex flex-column align-items-center text-center">
							<div class="profile-image">
								<img src="/resources/img/profile.png" alt="Admin" class="rounded-circle"
									width="150">
							</div>
							<div class="tutorname_introduction">
								<h4>${_TUTOR_INFO_.user_name}</h4>
								<p class="text-secondary mb-1">${_TUTOR_INFO_.tp_title}</p>
							</div>
							<div class="emblem d-flex flex-row">
								<div class="emblem-circle rounded-circle ">
									<br>대표과목<br> ${_TUTOR_INFO_.tt_subject}
								</div>
								<div class="emblem-circle rounded-circle ">
									<br>누적답변<br> ${_TUTOR_INFO_.tp_accu_answer}
								</div>
								<div class="emblem-circle rounded-circle ">
									<br>평점<br> ${_TUTOR_INFO_.tp_average}
								</div>
							</div>
						</div>
						<!-- nav bar -->
						<div class="left-nav" id="left-navigation">
							<ul class="nav nav-pills nav-stacked flex-column">
								<li class="nav-item mt-3"><a class="nav-link nav-tabs active" aria-current="page"
										href="/tutor/info/?num=${_TUTOR_INFO_.tp_number}">튜터정보</a></li>
								<li class="nav-item nav-tabs mt-3"><a class="nav-link"
										href="/tutor/writeReview/?num=${_TUTOR_INFO_.tp_number}">학생리뷰</a></li>
								<li class="nav-item nav-tabs mt-3"><a class="nav-link" 
										href="/tutor/ask/?num=${_TUTOR_INFO_.tp_number}">튜터에게 질문하기</a></li>
								<li class="nav-item nav-tabs  mt-3"><a class="nav-link"
										href="/tutor/tutoring/?num=${_TUTOR_INFO_.tp_number}">튜터에게 과외받기</a></li>
							</ul>
						</div>
					</div>

					<!-- 우측 section -->
					<div class="right-section col-9">
						<div class="headers d-flex flex-row align-items-center justify-content-between">
							<div class="head-line">
								<h2>
									<strong>튜터정보</strong>
								</h2>
							</div>
							<div class="edit-button d-flex flex-column">
								<button type="button" class="btn btn-primary" data-bs-toggle="modal"
									data-bs-target="#tutor-info-edit">
									수정</button>
							</div>
						</div>
						<div class="tutor-info d-flex flex-column justify-content-center">
							<div class="tutor-career flex-column ms-5">
								<div class="graduation row">

									<div class="col-1">
										&nbsp;<span class="badge bg-dark">학력</span>
									</div>
									<div class="col-11">
										<p>${_TUTOR_INFO_.tt_school}</p>
									</div>
								</div>

								<div class="history row">
									<div class="col-1">
										&nbsp;<span class="badge bg-dark">경력</span>
									</div>
									<div class="col-11">
										<p>${_TUTOR_INFO_.tp_career}</p>
									</div>
								</div>
							</div>

							<div class="introduction ms-5 mt-2">
								<div
									class="introduction-title d-flex flex-row justify-content-baseline align-items-center">
									<div>
										&nbsp;<span class="badge bg-dark">튜터소개</span>
									</div>
									<div class="ju">
										<h2 class="mb-0 ms-4">${_TUTOR_INFO_.tp_title}</h2>
									</div>
								</div>
								<div class="introduction-text">
									<span class="mt-4"> ${_TUTOR_INFO_.tp_comment} </span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--수정 modal -->
				<div class="modal fade" id="tutor-info-edit" data-bs-backdrop="static" data-bs-keyboard="false"
					tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-lg">
						<div class="modal-content d-flex flex-column justify-content-center">

							<div class="modal-body">
								<form class="was-validated d-flex flex-column">
									<label for="validationDefault01" class="form-label mb-0">학력</label>
									<input type="text" class="form-control" id="validationDefault01" required>
									<label for="validationDefault02" class="form-label mb-0">경력</label> <input
										type="text" class="form-control" id="validationDefault02" required>

									<label for="validationDefault03" class="form-label mb-0">제목</label>
									<input type="text" class="form-control" id="validationDefault03" required>
									<label for="validationDefault04" class="form-label mb-0">내용</label>
									<textarea class="form-control" placeholder="" id="validationDefault04"
										style="height: 150px" required></textarea>

									<div class="pop-up-button-box align-self-end mt-3">
										<button type="button" class="btn btn-outline-primary"
											data-bs-dismiss="modal">취소</button>
										&nbsp;&nbsp;&nbsp;
										<button type="submit" class="btn btn-outline-primary">확인</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End section -->

		<!-- ============= 공통 footer + js =============== -->
		<jsp:include page="../footer.jsp" flush="true" />
		<!-- ============================================== -->

	</body>

	</html>