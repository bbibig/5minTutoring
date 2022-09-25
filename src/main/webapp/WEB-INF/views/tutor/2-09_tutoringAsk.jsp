<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<link href="/resources/css/1-02_login.css" rel="stylesheet">
<!-- ========================================================= -->

<!-- css 추가 -->
<link rel="stylesheet" href="/resources/css/2-07_watchAnswer.css">

<!-- WebSocket CDN 추가 -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {

		// 전송 버튼 이벤트
		$("#button-send").on("click", function(e) {
			sendMessage();
		});
	
		var sock = new SockJS('http://localhost:8080/chatting');
		sock.onmessage = onMessage;
		sock.onclose = onClose;
		sock.onopen = onOpen;
		
		// 현재 세션에 로그인 한 사람
		var cur_session = '${__LOGIN_USER__.user_name}'; 
	
		function sendMessage() {
			sock.send($("#msg").val());
		}
		
		// 서버에서 메시지를 받았을 때
		function onMessage(msg) {
	
			var data = msg.data;
			var sessionId = null; // 데이터를 보낸 사람
			var message = null;
	
			var arr = data.split(":");
	
			for (var i = 0; i < arr.length; i++) {
				console.log('arr[' + i + ']: ' + arr[i]);
			}
	
			
			// var cur_nick = '${__LOGIN_USER__.user_nick}';
			console.log("cur_session : " + cur_session);
	
			sessionId = arr[0];
			message = arr[1];
	
			// 메시지 구분
			// if (sessionId == cur_session) {
	
			// 	var str = "<div class='col-6'>";
			// 	str += "<div class='alert alert-secondary'>";
			// 	str += "<b>" + cur_nick + " : " + message + "</b>";
			// 	str += "</div></div>";
	
			// 	$("#msgArea").append(str);
			// } else {
	
				var str = "<div class='col-6'>";
				str += "<div class='alert alert-warning'>";
				str += "<b>" + sessionId + " : " + message + "</b>";
				str += "</div></div>";
	
				$("#msgArea").append(str);
			// }
	
			console.log("채팅 메시지 : " + data);
	
		}
	
		// 채팅창에서 나갔을 때
		function onClose(evt) {
			
			var str = cur_session + " 님이 퇴장하셨습니다.";
			$("#msgArea").append(str);
		}
		
		// 채팅창에 들어왔을 때
		function onOpen(evt) {
			
			var str = cur_session + "님이 입장하셨습니다.";
			$("#msgArea").append(str);
		}
		
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

						<!-- 과외하기 질문글 -->
						<h2 class="fas fa-q ask_title">. ${_ONE_TB_VO_.tb_title}</h2>
						<div class="student_info d-flex">
							<div class="SPic">
								<img src="/resources/img/profile.png" alt="튜터프로필">
							</div>
							<div class="Sname">${_ONE_TB_VO_.user_name}</div>
							<div>&nbsp;학생</div>
							<br>
							<div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_ONE_TB_VO_.tb_date}"/></div>
							
							<c:if test="${__LOGIN_USER__.user_email eq _ONE_TB_VO_.user_email}">
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
							</c:if>
						</div>
						<div class="ask_content">${_ONE_TB_VO_.tb_content}</div>
					</div>
					
					<p></p>
					<p></p>
					
					<!-- <form action=#>
						<p></p>
						<button type="submit" id="tutoring">과외하기</button>
					</form> -->
					
					<!-- 채팅 입력 -->
					<div class="col-6">
					<label><h3><b>Chatting</b></h3></label>
					</div>
					<div>
						<div id="msgArea" class="col"></div>
						<div class="col-6">
							<div class="input-group mb-3">
								<input type="text" id="msg" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-6"></div>

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