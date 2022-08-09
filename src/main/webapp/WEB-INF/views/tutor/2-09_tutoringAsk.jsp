<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<link href="/resources/css/1-02_login.css" rel="stylesheet">
<!-- ========================================================= -->

<!-- css 추가 -->
<link rel="stylesheet" href="/resources/css/2-07_watchAnswer.css">

<title>튜터페이지</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<section class="d-flex align-items-center">
		<div class="container">
			<!-- ask -->
			<!-- <div class="fas fa-chevron-left back_icon"></div> -->
			<div class="row">
				<div class="col-lg-9">
					<div class="ask_card">

						<!-- 게시글 수정 modal -->
						<div class="modal fade" id="community_revise">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form>
											<div class="mb-3">
												<label for="recipient-name" class="col-form-label">제목</label>
												<input type="text" class="form-control" id="askTitle"
													placeholder="제목을 입력해주세요.">
											</div>
											<div class="mb-3">
												<label for="message-text" class="col-form-label">내용</label>
												<textarea class="form-control" id="askContent"
													placeholder="내용을 입력해주세요."></textarea>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary col-2"
											data-bs-dismiss="modal">취소</button>
										<button type="button" class="btn btn-primary col-2">저장</button>
									</div>
								</div>
							</div>
						</div>

						<!-- 댓글 수정 modal -->
						<div class="modal fade" id="comment_revise"
							data-bs-backdrop="static">
							<div class="modal-dialog modal-dialog-centered">
								<div
									class="modal-content d-flex flex-column justify-content-center">
									<div class="modal-body">
										<div class="pop-up-body d-flex flex-column align-items-center">

											<p class="my-3 fs-5"># 수정할 댓글을 입력하세요.</p>

											<form class="was-validated col-12 d-flex flex-column">
												<div class="text-box">
													<textarea class="form-control" placeholder=""
														id="floatingTextarea1" style="height: 150px" required></textarea>
												</div>

												<div class="pop-up-button-box align-self-end">
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

						<!-- 댓글 삭제 modal -->
						<div class="modal fade" id="delete" data-bs-backdrop="static">
							<div class="modal-dialog modal-dialog-centered">
								<div
									class="modal-content d-flex flex-column justify-content-center">

									<div class="modal-body">
										<div class="pop-up-body d-flex flex-column align-items-center">
											<div class="warnning-img">
												<i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
											</div>

											<p class="my-3 ">
												<strong class="fs-4">삭제하시겠습니까?</strong>
											</p>

											<div
												class="pop-up-button-box d-flex flex-row align-self-center">
												<button type="button" class="btn btn-outline-primary"
													data-bs-dismiss="modal">취소</button>
												&nbsp;&nbsp;&nbsp;
												<button type="button" class="btn btn-outline-primary">확인</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- <h2 class="fas fa-q ask_title">. 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십 일이삼사오육칠팔구십!</h2> -->
						<h2 class="fas fa-q ask_title">. ㅁㅁ이 무엇인가요??</h2>
						<div class="student_info d-flex">
							<div class="SPic">
								<img src="/resources/img/profile.png" alt="튜터프로필">
							</div>
							<div class="Sname">가나다</div>
							<div>&nbsp;학생</div>
							<br>
							<div class="date">&nbsp;2022-03-01</div>
							<div class="hamburger-button col-8 d-flex justify-content-end">
								<div class="dropdown">
									<button class="btn pt-0" type="button"
										data-bs-toggle="dropdown" aria-expanded="false">
										<i class="bi bi-list fs-4"></i>
									</button>
									<ul class="dropdown-menu">
										<li class="list-unstyled"><a class="dropdown-item"
											data-bs-toggle="modal" href="#community_revise">수정</a></li>
										<li class="list-unstyled"><a class="dropdown-item"
											data-bs-toggle="modal" href="#delete">삭제</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="ask_content">Lorem ipsum dolor sit amet
							consectetur, adipisicing elit. Cupiditate qui totam sapiente.
							Consectetur neque, vitae nemo impedit, numquam sit ex officia
							autem recusandae qui quia temporibus non minima corrupti enim
							sequi quos porro eum omnis itaque velit veritatis est debitis
							maiores. Similique saepe autem, rem doloribus tempora soluta quo
							quas!</div>
					</div>

					<form action="#">
						<p></p>
						<input type="submit" value="과외하기" id="tutoring">
					</form>
				</div>
			</div>

		</div>
	</section>

	<!-- CKEditor5 CDN 연결 및 언어 설정 -->
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/translations/ko.js"></script>
	<script>
		ClassicEditor.create(document.querySelector('#askContent'), {
			language : "ko"
		});
	</script>

	<!-- ============= 공통 footer + js =============== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<script src="/resources/js/1_01_login.js"></script>
	<!-- ============================================== -->
</body>

</html>