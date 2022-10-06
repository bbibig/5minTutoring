<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	    
	    <!-- CKeditor -->
		<script type="text/javascript" src="/resources/js/ckeditor/ckeditor.js"></script>

	    <!-- comment.js -->
		<script type="text/javascript" src="/resources/js/comment.js"></script>
		<script type="text/javascript">
			
				
			// 댓글 등록 테스트
			// console.log("댓글 등록 Test");
			// var a_number = '<c:out value="${_A_.a_number}"/>';
			
			// commentService.add(
			// 	{a_number:a_number, user_email:"test@gmail.com", cm_content:"create comment test."},
			// 	function(result){
			// 		alert("RESULT: 댓글 등록 성공");
			// 	}
			// );

			// 댓글 조회 테스트
			// console.log("댓글 조회 Test");
			// commentService.get(49, function(data) {
			// 	console.log("data: " + data);
			// 	alert("data: " + data.cm_number +", "+ data.cm_content);
			// });

			// 댓글 수정 테스트
			// commentService.update({
			// 	cm_number: 39,
			// 	cm_content: "수정 테스트"
			// }, function(result) {
			// 	console.log("result: " + result);
			// 	alert("수정 완료");
			// });

			// 댓글 삭제 테스트
			// console.log("댓글 삭제 Test");
			// commentService.remove(56, function(result) {
			// 	console.log("result: " + result);

			// 	if(result !== "undefined") {
			// 		alert("댓글 삭제 완료");
			// 	}
			// }, function(err) {
			// 	alert("ERROR");
			// });
			

			$(document).ready(function() {
				var userEmail = "${__LOGIN_USER__.user_email}";
				var a_number = "${_A_.a_number}";
				var commentList = $(".commentList");
				var currPage = 1; // 현재 댓글 페이지 번호

				
				// 댓글 리스트 출력
				showList(1, true);
				
				// 답변이 없을 경우 답변 영역 없애기
				console.log(a_number);
				if(a_number === "") {
					$("#answerArea").css("display", "none");
				}
				
				// 댓글 리스트 출력
	       		function showList(currPage, reset) {
					commentService.getList({a_number:a_number, currPage:currPage||1}, function(data) {
							
							var commentCnt = data.commentCnt;
							var list = data.list;
							var profile = data.profileList;
							var str = "";
							
							
							// 댓글 개수 출력
							$("#commentTotal").html(commentCnt);
							
							// 현재 표시된 페이지의 댓글이 5개 이하면 더보기 버튼 숨기기
							if(currPage * 5 >= commentCnt) {
								$("#moreComment").css("display", "none");
							} else {
								$("#moreComment").css("display", "inline");
							}
							
							// 댓글 모달 처리 후 1 페이지로 돌아오기 위한 처리
							if(reset) {
								commentList.html("");
								currPage = 1;
							}
							
							// 댓글 영역 출력
							if(list == null || list.length==0) {
								commentList.html("");
								return;
							}

							
							for(var i=0, len=list.length || 0; i<len; i++) {
									str+= '<div class="commentDiv" data-cm_number="'+list[i].cm_number+'"><div class="comment_info d-flex">';
									
									str+= '		<div class="sSPic"><img src="/resources/img/profile.png"></div>';
									
									str+= '		<div class="Sname">' +list[i].user_name+ '</div>';
									str+= '		<div class="date">' +commentService.displayTime(list[i].regdate)+ '</div></div>';
									
									console.log("userEmail: "+userEmail);
									console.log(list[i].user_email);
									
									str+= '	<div class="d-flex"><p class="d-flex col-10">' +list[i].cm_content+ '</p>';
									
									// 본인이 쓴 댓글에만 수정/삭제 버튼 나타남
									if(userEmail === list[i].user_email) {
										str+= '			<div class="hamburger-button col-1 d-flex justify-content-end ham">';
										str+= '				<div class="dropdown">';
										str+= '					<button class="btn pt-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">';
										str+= '						<i class="bi bi-list fs-4"></i></button>';
										str+= '					<ul class="dropdown-menu">';
										str+= '						<li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal" href="#comment_revise" id="cmModalBtn">수정</a></li>';
										str+= '						<li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal" href="#delete">삭제</a></li>';
										str+= '					</ul></div></div></div><hr>';
									} else {
										str+= '		<div class="empty" style=""></div></div><hr>';
									}
							}
						
						commentList.append(str);

					
					}); // getList
					
				} // showList

				
				// 댓글 더보기 버튼
		        $("#moreComment").click(function() {
		        	showList(++currPage, false);
		        });

				// 댓글 등록 
				$("#commentSave").on("click", function(e) {
					var comment = {
						a_number : a_number,
						user_email : $("#newComment").find("input[name='user_email']").val(),
						cm_content : $("#newComment").find("input[name='cm_content']").val()
					};
					commentService.add(comment, function(result) {
						alert("RESULT: 댓글 등록 성공");
						showList(1, true);
					}); // add
				}); // onclick

				// 댓글 모달에 데이터 전달 
				commentList.on("click", ".commentDiv", function(e) {
					var cno = $(this).data("cm_number");

					commentService.get(cno, function(comment) {

						$(".modal").find("input[name='cm_number']").val(comment.cm_number);
						$(".modal").find("textarea[name='cm_content']").val(comment.cm_content);
					});
				}); // onclick

				// 댓글 수정 
				$("#cmModifyBtn").on("click", function(e) {
					var comment = {
						cm_number: $(".modal").find("input[name='cm_number']").val(),
						cm_content: $(".modal").find("textarea[name='cm_content']").val()
					};
					commentService.update(comment, function(result) {
						if(result !== "undefined") {
							alert("댓글 수정 완료");
							$("#comment_revise").modal("hide");
							showList(1, true);
						}
					}, function(err) {
						alert("ERROR");
					});
				}); // onclick
				
				// 댓글 삭제 
				$("#cmRemoveBtn").on("click", function(e) {
					var cno = $(".modal").find("input[name='cm_number']").val();
					commentService.remove(cno, function(result) {
						if(result !== "undefined") {
							alert("댓글 삭제 완료");
							$("#delete").modal("hide");
							showList(1, true);
						}
					}, function(err) {
						alert("ERROR");
					});
				}); // onclick

			}); // .jq

		</script>

	
	    <title>튜터페이지</title>
	</head>
	
	<body>
   	    <!-- ============= 공통 Header : 로그인 후 =================== -->
	    <jsp:include page="../header_login.jsp" flush="true" />
	    <!-- ========================================================= -->
	
	    <section class="d-flex align-items-center">
	        <div class="container">
	            <!-- ask -->
	            <div class="row">
	                <div class="col-lg-9">
	                    <div class="ask_card">
	
	                        <!-- 질문글 수정 modal -->
	                        <div class="modal fade" id="reviseQ">
	                            <div class="modal-dialog modal-lg">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
	                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                                    </div>
                                        <form action="modifyAsk?num=${_ONE_Q_.qb_number}" method="post">
                                    		<div class="modal-body">
	                                            <div class="mb-3">
	                                                <label for="recipient-name" class="col-form-label">제목</label>
	                                                <input type="hidden" name="qb_number" value="${_ONE_Q_.qb_number}" class="form-control" id="askTitle"/>
	                                                <input type="text" name="qb_title" value="${_ONE_Q_.qb_title}" class="form-control" id="askTitle"
	                                                    placeholder="제목을 입력해주세요.">
	                                            </div>
	                                            <div class="mb-3">
	                                                <label for="message-text" class="col-form-label">내용</label>
	                                           		<textarea class="form-control" name="qb_content" id="contentsQ" placeholder="내용을 입력해주세요.">${_ONE_Q_.qb_content}</textarea>
		                                            <script>
		                                            	var ckeditor_config = {
															filebrowserUploadUrl: "upload/img",
															font_defaultLabel : "맑은 고딕/Malgun Gothic",
												            fontSize_defaultLabel : "12",
												            language : "ko"
												        };
												        CKEDITOR.replace('contentsQ', ckeditor_config);
													</script>
	                                            </div>
                                    		</div>
		                                    <div class="modal-footer">
		                                        <button type="button" class="btn btn-secondary col-2"
		                                            data-bs-dismiss="modal">취소</button>
		                                        <button type="submit" class="btn btn-primary col-2">저장</button>
		                                    </div>
                                        </form>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <!-- 질문글 삭제 modal -->
	                        <div class="modal fade" id="deleteQ" data-bs-backdrop="static">
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
	                                                <button type="button" onclick="location.href='deleteAsk?num=${_ONE_Q_.qb_number}&tp=${_ONE_Q_.tp_number}'" 
	                                                	class="btn btn-outline-primary">확인</button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <!-- 답변글 수정 modal -->
	                        <div class="modal fade" id="reviseA">
	                            <div class="modal-dialog modal-lg">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
	                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                                    </div>
                                        <form action="modifyAnswer?num=${_ONE_Q_.qb_number}" method="post">
                                    		<div class="modal-body">
	                                            <div class="mb-3">
	                                                <label for="message-text" class="col-form-label">내용</label>
	                                                <input type="hidden" name="qb_number" value="${_ONE_Q_.qb_number}" />
	                                                <input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" />
	                                           		<textarea class="form-control" name="a_content" id="contentsA" placeholder="내용을 입력해주세요.">${_A_.a_content}</textarea>
		                                            <script>
														var ckeditor_config = {
															filebrowserUploadUrl: "upload/img",
															font_defaultLabel : "맑은 고딕/Malgun Gothic",
												            fontSize_defaultLabel : "12",
												            language : "ko"
												        };
												        CKEDITOR.replace('contentsA', ckeditor_config);
													</script>
	                                            </div>
                                    		</div>
		                                    <div class="modal-footer">
		                                        <button type="button" class="btn btn-secondary col-2" data-bs-dismiss="modal">취소</button>
		                                        <button type="submit" class="btn btn-primary col-2">저장</button>
		                                    </div>
                                        </form>
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
	
	                                            <div id="cmModifyForm" class="was-validated col-12 d-flex flex-column">
	                                                <div class="text-box">
														<input type="hidden" id="modalCno" name="cm_number">
	                                                    <textarea name="cm_content" class="form-control" placeholder="" id="floatingTextarea1 modalContent"
	                                                        style="height: 150px" required></textarea>
	                                                </div>
	                                                <div class="pop-up-button-box align-self-end">
	                                                    <button type="button" class="btn btn-outline-primary"
	                                                        data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
	                                                    <button id="cmModifyBtn" type="button" class="btn btn-outline-primary">확인</button>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	
	                        <!-- 댓글 삭제 modal -->
	                        <div class="modal fade" id="delete" data-bs-backdrop="static">
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
	                                                <button id="cmRemoveBtn" type="button" class="btn btn-outline-primary">확인</button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	
							<!-- question -->
							<hr>
	                        <h2 class="fas fa-q ask_title">. ${_ONE_Q_.qb_title}</h2>
	                        <div class="student_info d-flex">
	                            <div class="SPic">
									<c:if test="${QProfile eq 'false'}"> <img src="/resources/img/profile.png"> </c:if>
									<c:if test="${QProfile eq 'true'}">
										<img src="<spring:url value='/profile/${QNick}_profile.png'/>" class="rounded-circle">
									</c:if>
	                            </div>
	                            <div class="Sname">${_ONE_Q_.user_name}</div>
	                            <div>&nbsp;학생</div>
	                            <br>
	                            <div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_ONE_Q_.regdate}"/></div>

	                            <c:if test="${__LOGIN_USER__.user_email eq _ONE_Q_.user_email}">
		                            <div class="hamburger-button col-8 d-flex justify-content-end">
		                                <div class="dropdown">
		                                    <button class="btn pt-0" type="button" data-bs-toggle="dropdown"
		                                        aria-expanded="false">
		                                        <i class="bi bi-list fs-4"></i>
		                                    </button>
		                                    <ul class="dropdown-menu">
		                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
		                                                href="#reviseQ">수정</a>
		                                        </li>
		                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
		                                                href="#deleteQ">삭제</a>
		                                        </li>
		                                    </ul>
		                                </div>
		                            </div>
								</c:if>	
	                        </div>
	                        <div class="ask_content">${_ONE_Q_.qb_content}</div>
	                    </div>
	                </div>
	            </div>
	            
	            <!-- answer -->
	            <div class="row" id="answerArea">
	                <div class="col-lg-9 answer">
	                    <div class="Tutor_info d-flex">
	                        <div class="TPic">
								<c:if test="${AProfile eq 'false'}"> <img src="/resources/img/profile.png"> </c:if>
								<c:if test="${AProfile eq 'true'}">
									<img src="<spring:url value='/profile/${ANick}_profile.png'/>" class="rounded-circle">
								</c:if>
	                        </div>
	                        <div class="Tname">${_A_.user_name}</div>
	                        <div>&nbsp;튜터</div>
	                        <br>
	                        <div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_A_.regdate}"/></div>
	                        
	                        <c:if test="${__LOGIN_USER__.user_email eq _A_.user_email}">
		                        <div class="hamburger-button col-8 d-flex justify-content-end">
		                            <div class="dropdown">
		                                <button class="btn pt-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		                                    <i class="bi bi-list fs-4"></i>
		                                </button>
		                                <ul class="dropdown-menu">
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#reviseA">수정</a>
	                                        </li>
		                                </ul>
		                            </div>
		                        </div>
		                    </c:if>
	                    </div>
	                    <p class="answer_content">${_A_.a_content}</p>
	
						   
	                    <!-- comment Area -->
	                    <span class="bi bi-chat-right-dots fs-4 comment_icon"><span id="commentTotal"> </span></span>
	                    <div class="comment_box">
	                        <div class="comment d-flex">
	                            <div class="sSPic">
	                                <img src="/profile/${__LOGIN_USER__.user_nick}_profile.png" class="rounded-circle" >
	                            </div>
								<div id="newComment">
									<input type="hidden" name="a_number" value="${_A_.a_number}" />
									<input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" />
									<input id="comment_write" name="cm_content" type="text" size="80" placeholder="댓글을 입력하세요.">
									<input id="commentSave" type="button" value="등록">
								</div>
	                        </div>
	                        <hr>
	                        
	                        <!-- comment List -->
	                        <div class="commentList">


							</div>
							<div class="col text-center">
								<button type="button" class="btn btn-default btn-block" id="moreComment">▽ 댓글 더보기</button>
							</div>
	                    </div>
	                </div>
	            </div>
			</div>
	    </section>
	
	    <!-- ============= 공통 footer + js =============== -->
	    <jsp:include page="../footer.jsp" flush="true" />
	    <!-- ============================================== -->

	</body>

</html>