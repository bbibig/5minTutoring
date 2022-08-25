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
										aria-controls="flush-collapseOne"> Q. ${_FAQLIST_[0].faq_title} </button>
								</h2>
								<div id="flush-collapseOne" class="accordion-collapse collapse"
									aria-labelledby="flush-headingOne"
									data-bs-parent="#accordionFlushExample">
									<div class="accordion-body"> ${_FAQLIST_[0].faq_content} </div>
								</div>
							</div>	<!--아코디언1-->

							<c:if test="${_FAQLIST_[1] != null}">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingTwo">
										<button class="accordion-button collapsed" type="button"
											style="background-color: white;" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseTwo" aria-expanded="false"
											aria-controls="flush-collapseTwo">Q. ${_FAQLIST_[1].faq_title}</button>
									</h2>
									<div id="flush-collapseTwo" class="accordion-collapse collapse"
										aria-labelledby="flush-headingTwo"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body"> ${_FAQLIST_[1].faq_content} </div>
									</div>
								</div>	<!--아코디언2-->
							</c:if>

							<c:if test="${_FAQLIST_[2] != null}">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingThree">
										<button class="accordion-button collapsed" type="button"
											style="background-color: white;" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseThree" aria-expanded="false"
											aria-controls="flush-collapseThree"> Q. ${_FAQLIST_[2].faq_title} </button>
									</h2>
									<div id="flush-collapseThree"
										class="accordion-collapse collapse"
										aria-labelledby="flush-headingThree"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body"> ${_FAQLIST_[2].faq_content} </div>
									</div>
								</div>	<!--아코디언3-->
							</c:if>

							<c:if test="${_FAQLIST_[3] != null}">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingFour">
										<button class="accordion-button collapsed" type="button"
											style="background-color: white;" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseFour" aria-expanded="false"
											aria-controls="flush-collapseFour"> Q. ${_FAQLIST_[3].faq_title} </button>
									</h2>
									<div id="flush-collapseFour" class="accordion-collapse collapse"
										aria-labelledby="flush-headingFour"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body"> ${_FAQLIST_[3].faq_content} </div>
									</div>
								</div>	<!--아코디언4-->
							</c:if>

							<c:if test="${_FAQLIST_[4] != null}">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingFive">
										<button class="accordion-button collapsed" type="button"
											style="background-color: white;" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseFive" aria-expanded="false"
											aria-controls="flush-collapseFive"> Q. ${_FAQLIST_[4].faq_title} </button>
									</h2>
									<div id="flush-collapseFive" class="accordion-collapse collapse"
										aria-labelledby="flush-headingFive"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body"> ${_FAQLIST_[4].faq_content} </div>
									</div>
								</div>	<!--아코디언5-->
							</c:if>

							<c:if test="${_FAQLIST_[5] != null}">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingSix">
										<button class="accordion-button collapsed" type="button"
											style="background-color: white;" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseSix" aria-expanded="false"
											aria-controls="flush-collapseSix"> Q. ${_FAQLIST_[5].faq_title} </button>
									</h2>
									<div id="flush-collapseSix" class="accordion-collapse collapse"
										aria-labelledby="flush-headingSix"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body"> ${_FAQLIST_[5].faq_content} </div>
									</div>
								</div>	<!--아코디언6-->
							</c:if>
						</div>
						<!-- FAQ리스트 -->

					</div>
				</div>
				<!-- End main contents -->
			</div>

			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center p-5">
					<li class="page-item"><a class="page-link rounded-circle" href="/faq?currPage=1">&laquo;</a></li>
					<li class="page-item"><a class="page-link rounded-circle" href="/faq?currPage=${_FAQPAGENATION_.cri.currPage - 1}">&lt;</a></li>
					<li class="page-item"><a class="page-link rounded-circle bg-blue" href="/faq?currPage=${_FAQPAGENATION_.cri.currPage}">${_FAQPAGENATION_.cri.currPage}</a></li>
					<li class="page-item"><a class="page-link rounded-circle" href="/faq?currPage=${_FAQPAGENATION_.cri.currPage + 1}">&gt;</a></li>
					<li class="page-item"><a class="page-link rounded-circle" href="/faq?currPage=${_FAQPAGENATION_.realEndPage}">&raquo;</a>
					</li>
				</ul>
			</nav>

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