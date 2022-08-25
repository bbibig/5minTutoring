<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="ko">

<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<link href="${path}/resources/css/5_FAQ.css" rel="stylesheet">
<!-- ========================================================= -->

<title>자주 묻는 질문</title>
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

					<h2 id="faqtitle" class="fw-bold text-center">자주묻는 질문(FAQ)</h2>

					<div id="faq" class="container">

						<!-- FAQ 리스트 -->
						<div class="accordion accordion-flush" id="accordionFlushExample">
							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingOne">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseOne" aria-expanded="false"
										aria-controls="flush-collapseOne">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseOne" class="accordion-collapse collapse"
									aria-labelledby="flush-headingOne"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>

							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingTwo">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseTwo" aria-expanded="false"
										aria-controls="flush-collapseTwo">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseTwo" class="accordion-collapse collapse"
									aria-labelledby="flush-headingTwo"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>

							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingThree">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseThree" aria-expanded="false"
										aria-controls="flush-collapseThree">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseThree"
									class="accordion-collapse collapse"
									aria-labelledby="flush-headingThree"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>

							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingFour">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseFour" aria-expanded="false"
										aria-controls="flush-collapseFour">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseFour" class="accordion-collapse collapse"
									aria-labelledby="flush-headingFour"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>

							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingFive">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseFive" aria-expanded="false"
										aria-controls="flush-collapseFive">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseFive" class="accordion-collapse collapse"
									aria-labelledby="flush-headingFive"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>

							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingSix">
									<button class="accordion-button collapsed" type="button"
										style="background-color: white;" data-bs-toggle="collapse"
										data-bs-target="#flush-collapseSix" aria-expanded="false"
										aria-controls="flush-collapseSix">Q. 질문입니다.</button>
								</h2>
								<div id="flush-collapseSix" class="accordion-collapse collapse"
									aria-labelledby="flush-headingSix"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">A. Lorem ipsum dolor sit amet
										consectetur adipisicing elit. Necessitatibus sunt, sapiente
										consectetur vero et fugiat, aspernatur voluptas dicta debitis
										inventore ut delectus cupiditate fuga tenetur? Quidem
										similique earum nam error!</div>
								</div>
							</div>
						</div>
						<!-- FAQ리스트 -->

					</div>
				</div>
				<!-- End main contents -->
			</div>
		</div>
		<!--End card-->
	</section>
	<!-- End main Section -->

	</section>
	<!-- End Hero -->



	<!-- ============= 공통 footer + js =============== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ============================================== -->
</body>

</html>