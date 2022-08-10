<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<!-- ========================================================= -->

<title>1:1 문의하기</title>

<style>
#exampleFormControlTextarea1 {
	resize: none;
}
</style>

</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Hero Section ======= -->
	<section id="main" class="container">
		<div class="container">
			<div class="row justify-content-md-center"">


				<div id="contents" class="col-lg-9 ">

					<h3 class="fw-bold">1:1 문의하기</h3>
					<br>

					<!-- FROM -->
					<div class="container card p-4" id="inquiry_form" bg-card>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">제목</label>
							<input type="email" class="form-control"
								id="exampleFormControlInput1" placeholder="제목을 입력해주세요.">
						</div>
						<br>

						<div class="mb-3">
							<label for="exampleFormControlTextarea1" class="form-label">질문내용</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="10"></textarea>
						</div>
						<br>

						<div class="mb-3">
							<label for="formFileMultiple" class="form-label">파일선택</label> <input
								class="form-control" type="file" id="formFileMultiple" multiple>
						</div>


						<div class="row">
							<div class="d-flex flex-row justify-content-center">
								<a class="btn btn-outline-dark me-1" href="/mypage/qList"
									role="button">목록으로</a>
								<a class="btn btn-outline-dark me-1" href="/mypage/qList"
									role="button">문의하기</a> 
							<!--  <button class="btn btn-dark ms-1" type="submit" href="/mypage/qList">문의하기</button>-->	
							</div>
						</div>



					</div>
					<!--End main contents card(박스)-->

				</div>
				<!-- End main contents -->
			</div>
		</div>
		<!--End card-->
	</section>
	<!-- End main Section -->



	<!-- End Hero -->



	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>