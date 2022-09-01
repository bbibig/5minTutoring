<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.zerock.fmt.domain.TutorPageVO" 
		import="java.util.List"
		import="java.util.ArrayList"
%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
	<jsp:include page="../htmlHead.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- css파일 추가 -->
	<link rel="stylesheet" href="/resources/css/2-01_tpMain.css">

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script>
		$('.carousel').carousel({
			interval: 2000
			// 슬라이드 5초 간격으로 설정
		});
	</script>
	
	<title>튜터페이지</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Section ======= -->
	<section class="d-flex align-items-center">

		<div class="container">

<%
	String subject = "국어";
	String searchType = "누적답변순";
	
	if(request.getParameter("subject") != null) {
		subject = request.getParameter("subject");
	}
	if(request.getParameter("searchType") != null) {
		searchType = request.getParameter("searchType");
	}
%>
			<!-- 신규 튜터 -->
			<div class="row tutors">
				<h1>
					<div>신규 튜터</div>
				</h1>
				<div class="col-md-12">
					<div id="tutorCarousel_1" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">

							<div class="carousel-item active">
								<div class="row">
									<c:forEach var="recent" items="${_RECENT_LIST_}" begin="0" end="3" step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${recent.user_name} </h3>
													<span>튜터</span> <span class="post"> ${recent.tp_title}
													</span>
													<span class="fas fa-book"> ${recent.tt_subject} </span>
													<span class="fas fa-comment-dots"> ${recent.tp_accu_answer}
													</span> <span class="fas fa-star"> ${recent.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${recent.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="carousel-item">
								<div class="row">
									<c:forEach var="recent" items="${_RECENT_LIST_}" begin="4" end="7" step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${recent.user_name} </h3>
													<span>튜터</span> <span class="post"> ${recent.tp_title}
													</span>
													<span class="fas fa-book"> ${recent.tt_subject} </span>
													<span class="fas fa-comment-dots">
														${recent.tp_accu_answer}
													</span> <span class="fas fa-star"> ${recent.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${recent.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="carousel-item">
								<div class="row">
									<c:forEach var="recent" items="${_RECENT_LIST_}" begin="8" end="11"
										step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${recent.user_name} </h3>
													<span>튜터</span> <span class="post"> ${recent.tp_title}
													</span>
													<span class="fas fa-book"> ${recent.tt_subject} </span>
													<span class="fas fa-comment-dots">
														${recent.tp_accu_answer}
													</span> <span class="fas fa-star"> ${recent.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${recent.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

						</div>
					</div>

					<!-- controller 버튼 -->
					<a class="carousel-control-prev" href="#tutorCarousel_1" role="button" data-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					</a> 
					<a class="carousel-control-next" href="#tutorCarousel_1" role="button" data-slide="next"> 
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
					</a>
				</div>

				<!-- ====================================================================================================== -->

				<!-- 추천 튜터 -->
				<h1>
					<div>추천 튜터</div>
				</h1>

				<form action="/tutor/main" id="search">
					<input id="search_bt" type="submit" value="검색"> 
					<select name="searchType" class="sort">
						<option value="누적답변순">누적답변순</option>
						<option value="평점순" <% if(searchType.equals("평점순")) out.println("selected"); %>>평점순</option>
					</select>

					<select name="subject" class="sort">
						<option value="국어" <% if(subject.equals("국어")) out.println("selected"); %>>국어</option>
						<option value="영어" <% if(subject.equals("영어")) out.println("selected"); %>>영어</option>
						<option value="수학" <% if(subject.equals("수학")) out.println("selected"); %>>수학</option>
					</select>
				</form>
				
				<button class="subject_bt <% if(subject.equals("국어")) out.println("selected"); %>">국어</button>
				<button class="subject_bt <% if(subject.equals("영어")) out.println("selected"); %>">영어</button>
				<button class="subject_bt <% if(subject.equals("수학")) out.println("selected"); %>">수학</button>

				<div class="col-md-12">
					<div id="tutorCarousel_2" class="carousel slide" data-ride="carousel">
					
						<!-- Carousel items -->
						<div class="carousel-inner">
							<div class="carousel-item active">
								<div class="row">
									<c:forEach var="sortedT" items="${_SORTED_TUTOR_}" begin="0" end="3"
										step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${sortedT.user_name} </h3>
													<span>튜터</span> <span class="post"> ${sortedT.tp_title}
													</span>
													<span class="fas fa-book"> ${sortedT.tt_subject} </span>
													<span class="fas fa-comment-dots">
														${sortedT.tp_accu_answer}
													</span> <span class="fas fa-star"> ${sortedT.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${sortedT.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="carousel-item">
								<div class="row">
									<c:forEach var="sortedT" items="${_SORTED_TUTOR_}" begin="4" end="7"
										step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${sortedT.user_name} </h3>
													<span>튜터</span> <span class="post"> ${sortedT.tp_title}
													</span>
													<span class="fas fa-book"> ${sortedT.tt_subject} </span>
													<span class="fas fa-comment-dots">
														${sortedT.tp_accu_answer}
													</span> <span class="fas fa-star"> ${sortedT.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${sortedT.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							
							<div class="carousel-item">
								<div class="row">
									<c:forEach var="sortedT" items="${_SORTED_TUTOR_}" begin="8" end="11"
										step="1">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname"> ${sortedT.user_name} </h3>
													<span>튜터</span> <span class="post"> ${sortedT.tp_title}
													</span>
													<span class="fas fa-book"> ${sortedT.tt_subject} </span>
													<span class="fas fa-comment-dots">
														${sortedT.tp_accu_answer}
													</span> <span class="fas fa-star"> ${sortedT.tp_average}
													</span>
												</div>
												<div class="more">
													<a href="tutoring/?num=${sortedT.tp_number}" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>

						<!-- controller 버튼 -->
						<a class="carousel-control-prev adjust_pre" href="#tutorCarousel_2" role="button" data-slide="prev"> 
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						</a> 
						<a class="carousel-control-next adjust_nex" href="#tutorCarousel_2" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Section -->

	<!-- ============= 공통 footer + js =============== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ============================================== -->
	

</body>

</html>