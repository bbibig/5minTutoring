<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
		<jsp:include page="../htmlHead.jsp" flush="true" />
		<link href="/resources/css/1-02_login.css" rel="stylesheet">
		<!-- ========================================================= -->
		
		<!-- css파일 추가 -->
		<link rel="stylesheet" href="/resources/css/2-01_tpMain.css">
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
		<script>
			$('.carousel').carousel({
				interval : 2000
			// 슬라이드 5초 간격으로 설정
			})
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
				<div class="row tutors">
					<h1>
						<div>신규 튜터</div>
					</h1>
					<div class="col-md-12">
						<div id="tutorCarousel_1" class="carousel slide"
							data-ride="carousel">
	
							<!-- Carousel items -->
							<div class="carousel-inner">
	
								<div class="carousel-item active">
									<div class="row">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="https://picsum.photos/600/400">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">일이삼사오육칠팔구십일이삼사오육칠팔구십</span>
													<span class="fas fa-book"> 국어 </span> <span
														class="fas fa-comment-dots"> 45개 </span> <span
														class="fas fa-star">4.5</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김소희</h3>
													<span>튜터</span> <span class="post">안녕하세요. 국어 마스터입니다.</span>
													<span class="fas fa-book"> 국어 </span> <span
														class="fas fa-comment-dots"> 45개 </span> <span
														class="fas fa-star">4.5</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">문희철</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 수학 </span> <span
														class="fas fa-comment-dots"> 55개 </span> <span
														class="fas fa-star">4.5</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">박수인</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 영어 </span> <span
														class="fas fa-comment-dots"> 65개 </span> <span
														class="fas fa-star">4.5</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</div>
									<!--.row-->
								</div>
								<!--.item-->
	
								<div class="carousel-item">
									<div class="row">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">배지현</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">이윤정</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</div>
									<!--.row-->
								</div>
								<!--.item-->
	
							</div>
							<!--.carousel-inner-->
						</div>
						<!--.Carousel-->
	
						<!-- controller 버튼 -->
						<a class="carousel-control-prev" href="#tutorCarousel_1"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span>
						</a> <a class="carousel-control-next" href="#tutorCarousel_1"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span>
						</a>
					</div>
	
					<h1>
						<div>추천 튜터</div>
					</h1>
	
					<form action="#" id="search">
	
						<input id="search_bt" type="submit" value="검색"> <select
							name="sort" id="sort">
							<option value="1" selected>누적답변순</option>
							<option value="2">평점순</option>
						</select>
	
						<button class="subject_bt selected">국어</button>
						<button class="subject_bt">영어</button>
						<button class="subject_bt">수학</button>
					</form>
	
					<div class="col-md-12">
						<div id="tutorCarousel_2" class="carousel slide"
							data-ride="carousel">
							<!-- Carousel items -->
							<div class="carousel-inner">
	
								<div class="carousel-item active">
									<div class="row">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김소희</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">문희철</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">박수인</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</div>
									<!--.row-->
								</div>
								<!--.item-->
	
								<div class="carousel-item">
									<div class="row">
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">배지현</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">이윤정</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
	
										<div class="col-lg-3 col-md-6 col-sm-6">
											<div class="tutorProfile">
												<div class="tPic">
													<img src="/resources/img/profile.png">
												</div>
												<div class="tutorInfo">
													<h3 class="Tname">김보배</h3>
													<span>튜터</span> <span class="post">한줄 소개 입니다.</span> <span
														class="fas fa-book"> 대표과목 </span> <span
														class="fas fa-comment-dots"> 누적답변 </span> <span
														class="fas fa-star">평점</span>
												</div>
												<div class="more">
													<a href="tutoring" class="fas fa-arrow-pointer"> 과외신청 바로가기</a>
												</div>
											</div>
										</div>
									</div>
									<!--.item-->
								</div>
								<!--.carousel-inner-->
							</div>
							<!--.Carousel-->
	
							<!-- controller 버튼 -->
							<a class="carousel-control-prev adjust_pre"
								href="#tutorCarousel_2" role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span>
							</a> <a class="carousel-control-next adjust_nex"
								href="#tutorCarousel_2" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Section -->
	
	
		<!-- ============= 공통 footer + js =============== -->
	    <jsp:include page="../footer.jsp" flush="true" />
	    <script src="/resources/js/1_01_login.js"></script>
	    <!-- ============================================== -->
	
	
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous">
		</script>
	
	</body>
</html>