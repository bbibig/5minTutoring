<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 


<c:set var="path" value="${pageContext.request.contextPath}" />

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

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"
		integrity="sha512-n/4gHW3atM3QqRcbCn6ewmpxcLAHGaDjpEBu4xZd47N0W2oQ+6q7oc3PXstrJYXcbNU1OHdQ1T7pAP+gi5Yu8g=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
		integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"
		integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

		<script>
			$(function(){
				


			$('#comment_list').on("click", '.commentDiv', function() {
			
			var cm_number = $(this).data("cm_no");
			console.log(cm_number);
			var cm_content = $(this).data("cm_content");
			console.log(cm_content);
			var fb_number = $(this).data("fb_number");
			console.log(fb_number);	

			$("input[name='cm_number']").val(cm_number);
			$("textarea[name='cm_content']").val(cm_content);
			$("input[name='fb_number']").val(fb_number);
					
			}); // onclick


		}) //jquery		

		</script>


	<style>
		/* toggleHeart */
		.bi-suit-heart:hover,
		.bi-suit-heart-fill {
			color: rgb(255, 70, 70);
		}

		.form-control{
			resize: none;
		}

	</style>



</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<section class="d-flex align-items-center">
		<div class="container">
			<!-- answer -->
			<div class="row">
				<div class="col-lg-9 answer">

					<!-- 게시글 수정 modal -->
					<div class="modal fade" id="community_revise">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<form action="/community/modify?fb_number=${_BOARD_.fb_number}" method="post">
										<div class="mb-3">
											<label for="recipient-name" class="col-form-label">제목</label>
											<input type="text" class="form-control" name="fb_title" id="askTitle"
												value="${_BOARD_.fb_title}">
										</div>
										<div class="mb-3">
											<label for="message-text" class="col-form-label">내용</label>
											<textarea class="form-control" name="fb_content"
												id="askContent">${_BOARD_.fb_content}</textarea>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-secondary col-2"
												data-bs-dismiss="modal">취소</button>
											<button type="submit" class="btn btn-primary col-2"
												id="updateBtn">저장</button>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>

					<!-- 댓글 수정 modal -->
					<div class="modal fade" id="comment_revise" data-bs-backdrop="static">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content d-flex flex-column justify-content-center">
								<div class="modal-body">
									<div class="pop-up-body d-flex flex-column align-items-center">

										<p class="my-3 fs-5"># 수정할 댓글을 입력하세요.</p>

										<form class=" col-12 d-flex flex-column" id="commentUpdateForm" action="/community/commentUpdate" method="post">
											<div class="text-box">
												<input type="hidden" name="cm_number">
												<input type="hidden" name="fb_number">
												<textarea class="form-control" placeholder="" id="floatingTextarea1"
													name="cm_content" style="height: 150px" required></textarea>
											</div>

											<div class="pop-up-button-box align-self-end">
												<button type="button" class="btn btn-outline-primary"
													data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
												<button type="submit" class="btn btn-outline-primary"
													id="commentUpdateBtn">확인</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- post 삭제 modal -->
					<div class="modal fade" id="postDelete" data-bs-backdrop="static">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content d-flex flex-column justify-content-center">

								<div class="modal-body">
									<div class="pop-up-body d-flex flex-column align-items-center">
										<div class="warnning-img">
											<i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
										</div>

										<p class="my-3 "><strong class="fs-4">삭제하시겠습니까?</strong></p>

										<div class="pop-up-button-box d-flex flex-row align-self-center">
											<button type="button" class="btn btn-outline-primary"
												data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
											<button type="button"
												onclick="location.href='remove?num=${_BOARD_.fb_number}' "
												class="btn btn-outline-primary" id="removeBtn">확인</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- 댓글 삭제 modal -->
					<div class="modal fade" id="commentDelete" data-bs-backdrop="static">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content d-flex flex-column justify-content-center">

								<div class="modal-body">
									<div class="pop-up-body d-flex flex-column align-items-center">
										<div class="warnning-img">
											<i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
										</div>
										<form action="/community/commentDelete" method="post" class="d-flex flex-column justify-content-center">
										
										<p class="my-3 align-self-center"><strong class="fs-4">삭제하시겠습니까?</strong></p>
										<input type="hidden" name="cm_number">
										<input type="hidden" name="fb_number">
					
										<div class="pop-up-button-box d-flex flex-row align-self-center">
											<button type="button" class="btn btn-outline-primary"
												data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
											<button type="submit" class="btn btn-outline-primary">확인</button>
					
										</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!-- post -->
					<form action="#" id="postBoard">
						<h2 class="ask_title" name="fb_title">${_BOARD_.fb_title}</h2>
						<div class="Tutor_info d-flex">

							<div class="TPic mt-3">
								<c:if test="${profileResult eq 'false'}"> <img src="/resources/img/profile.png"> </c:if>
								<c:if test="${profileResult eq 'true'}">
									<img src="<spring:url value='/profile/${_BOARD_.user_nick}_profile.png'/>"
										class="rounded-circle ms-0">
								</c:if>
							</div>
							<div class="Tname mt-3">${_BOARD_.user_nick}</div>

							<br>
							<div class="date mt-3 ms-0" name="fb_date">&nbsp;
								<fmt:formatDate value="${_BOARD_.fb_date}" pattern="yyyy.MM.dd" />
							</div>

							<c:if test="${__LOGIN_USER__.user_email eq _BOARD_.user_email}">
								<div class="hamburger-button col-8 d-flex justify-content-end">

									<div class="dropdown">
										<button class="btn pt-0" type="button" data-bs-toggle="dropdown"
											aria-expanded="false">
											<i class="bi bi-list fs-4"></i>
										</button>
										<ul class="dropdown-menu">
											<li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
													href="#community_revise">수정</a></li>
											<li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
													href="#postDelete">삭제</a></li>
										</ul>
									</div>
								</div>
							</c:if>
							
						</div>
						<p name="fb_content">${_BOARD_.fb_content}</p>
					</form>

					<!-- comment -->
					<!-- icon -->
					
					<span class="bi bi-chat-right-dots fs-4 comment_icon ps-0"><span>  ${_BOARD_.fb_comment_count}</span></span>

					<div class="comment_box">
						<div class="comment d-flex mt-2">
							<div class="sSPic">
								<c:if test="${_USER_PROFILE_ eq 'false'}"> <img src="${path}/resources/img/profile.png"> </c:if>
            					<c:if test="${_USER_PROFILE_ eq 'true'}">
									<img src="<spring:url value='/profile/${__LOGIN_USER__.user_nick}_profile.png'/>"class="rounded-circle">
								</c:if>
							</div>
							<form action="/community/commentWrite?fb_number=${_BOARD_.fb_number}" method="post" class="pb-0">

								<input type="hidden" id="user_email" name="user_email"
									value="${__LOGIN_USER__.user_email}">
								<input id="comment_write" type="text" size="83" name="cm_content" class="mt-1" 
									placeholder="댓글을 입력하세요.">
								<button type="submit" id="save" type="submit" class="ms-2">등록</button>
							</form>
						</div>
						<hr>

						<div id="comment_list">
						<c:forEach items='${_COMMENTLIST_}' var="commentList" varStatus="statusNm">
							<div class="commentDiv" data-cm_no="${commentList.cm_number}" data-cm_content="${commentList.cm_content}" data-fb_number="${commentList.fb_number}">
							<div class="comment_info d-flex">
								<div class="sSPic">
									<c:forEach var="profileC" items="${profileCList[statusNm.index]}" varStatus="status">
										<c:if test="${profileC.profile_number == 0}">
											<img src="/resources/img/profile.png">
										</c:if>
										<c:if test="${profileC.profile_number != 0}">
											<img src="<spring:url value='/profile/${profileC.user_nick}_profile.png'/>" class="rounded-circle">
										</c:if>			
									</c:forEach>
								</div>
								<div class="Sname">${commentList.user_nick}</div>
								<input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}">
								<div class="date">
									<fmt:formatDate value="${commentList.regdate}" pattern="yyyy.MM.dd" />
								</div>
								
								<c:if test="${__LOGIN_USER__.user_email eq commentList.user_email}">
									<div class="hamburger-button col-9 d-flex justify-content-end">

										<div class="dropdown">
											<button class="btn pt-0" type="button" data-bs-toggle="dropdown"
												aria-expanded="false">
												<i class="bi bi-list fs-4"></i>
											</button>
											<ul class="dropdown-menu">
												<li class="list-unstyled"><a class="dropdown-item" 
														data-bs-toggle="modal" href="#comment_revise">수정</a>
												</li>
												<li class="list-unstyled"><a class="dropdown-item"
														data-bs-toggle="modal" href="#commentDelete">삭제</a>
												</li>
											</ul>
										</div>
									</div>
								</c:if>
							</div>

							<p>
								${commentList.cm_content}
							</p>

							<hr>
						</div>	
						</c:forEach>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- ============= 공통 footer + js =============== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<script src="/resources/js/1_01_login.js"></script>
	<!-- ============================================== -->

	<!-- CKEditor5 CDN 연결 및 언어 설정 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/translations/ko.js"></script>
	<script>
		ClassicEditor.create(document.querySelector('#askContent'), {
			language: "ko"
		});
		var i = 0;
		$('#heart').on('click', function () {
			if (i == 0) {
				$(this).removeClass('bi-suit-heart');
				$(this).addClass('bi-suit-heart-fill');
				i++;
			} else if (i == 1) {
				$(this).removeClass('bi-suit-heart-fill');
				$(this).addClass('bi-suit-heart');
				i--;
			}
		});
	</script>
</body>

</html>