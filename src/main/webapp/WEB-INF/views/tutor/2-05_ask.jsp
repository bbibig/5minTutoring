<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.zerock.fmt.domain.UserVO" %>
<!doctype html>
<html lang="ko">
	<head>
		<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
			<jsp:include page="../htmlHead.jsp" flush="true" />
			<link href="/resources/css/1-02_login.css" rel="stylesheet">
			<link href="/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
		<!-- ========================================================= -->
		
	    <!-- CSS 추가 -->
	    <link rel="stylesheet" href="/resources/css/2-05_ask.css">

	    <!-- CKeditor -->
		<script type="text/javascript" src="/resources/js/ckeditor/ckeditor.js"></script>
		
		<script>
			String tutorName = ${_TUTOR_INFO_.user_name};
			String userName = userVO.getUser_name;
		</script>
	
	    <title>튜터페이지</title>
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
	                    <div class="left-nav" id="left-navigation">
							<ul class="nav nav-pills nav-stacked flex-column">
								<li class="nav-item mt-3"><a class="nav-link nav-tabs active" aria-current="page"
										href="/tutor/info?num=${_TUTOR_INFO_.tp_number}">튜터정보</a></li>
								<li class="nav-item nav-tabs mt-3"><a class="nav-link"
										href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}">학생리뷰</a></li>
								<li class="nav-item nav-tabs mt-3"><a class="nav-link" 
										href="/tutor/ask?num=${_TUTOR_INFO_.tp_number}">튜터에게 질문하기</a></li>
								<li class="nav-item nav-tabs  mt-3"><a class="nav-link"
										href="/tutor/tutoring?num=${_TUTOR_INFO_.tp_number}">튜터에게 과외받기</a></li>
							</ul>
						</div>
	                </div>
	
	                <div class="right-section col-9">
	                    <div class="headers d-flex justify-content-between">
	                        <div>
	                            <h2><strong>튜터에게 질문하기</strong></h2>
	                        </div>
	                        <div class="edit-button d-flex flex-column">
	                            <button type="button" id="ask_bt" value="질문하기" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ask_write">
	                                질문하기
	                            </button>
	                        </div>
	
	                        <!-- 질문하기 modal -->
	                        <div class="modal fade" id="ask_write">
	                            <div class="modal-dialog modal-lg">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLabel"># 질문하기</h5>
	                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                                    </div>
	                                    <div class="info d-flex">
	                                        <div class="red">3일 이내&nbsp;</div>
	                                        <div>답변 미등록시 손들기는&nbsp;</div>
	                                        <div class="red">자동 회수</div>
	                                        <div>됩니다.</div>
	                                    </div>
	                                    <form action="createAsk?num=${_TUTOR_INFO_.tp_number}" enctype="multipart/form-data" method="post">
		                                    <div class="modal-body">
		                                            <div class="mb-3">
		                                                <label for="recipient-name" class="col-form-label">제목</label>
		                                                <input type="hidden" name="tp_number" value="${_TUTOR_INFO_.tp_number}">
		                                                <input type="text" name="qb_title" class="form-control" id="askTitle" placeholder="제목을 입력해주세요.">
		                                            </div>
		                                            <div class="mb-3">
		                                                <label for="message-text" class="col-form-label">내용</label>
		                                                <textarea class="form-control" name="qb_content" id="contents" placeholder="내용을 입력해주세요."></textarea>
		                                               <script>
															var ckeditor_config = {
																filebrowserUploadUrl: "upload/img"
													        };
													        CKEDITOR.replace("contents", ckeditor_config);
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
	                    </div>

	                    <!-- Ask Cards -->
	                    <div class="row">
	                    	<c:forEach var="Q" items="${_QB_VO_}">
		                        <div class="col-lg-4 col-md-6 col-sm-12">
		                            <div class="ask_card" onclick="location.href='watchAnswer?num=${Q.qb_number}&tp=${Q.tp_number}';" id="askLink" style="cursor:pointer">
		                                <div class="student_info">
		                                    <div class="sPic">
		                                        <img src="/resources/img/profile.png" alt="튜터프로필">
		                                    </div>
		                                    <div class="Sname">${Q.user_name}</div>
		                                    <div class="date"><fmt:formatDate pattern="yyyy.MM.dd" value="${Q.regdate}"/></div>
		                                </div>
		                                <div class="ask_title" style="height:100%;">
		                                    ${Q.qb_title}
		                                    <div class="answer_ok">
			                                    <c:if test="${Q.qb_answer == '0'}">
			                                    	<c:out value="답변미완료"/>
			                                    </c:if>
			                                    <c:if test="${Q.qb_answer == '1'}">
			                                    	<c:out value="답변완료"/>
			                                    </c:if>
			                                </div>
		                                </div>
		                            </div>
		                        </div>
	                        </c:forEach>
	                        
	                        <div class="col-lg-4 col-md-6 col-sm-12">
	                            <div class="ask_card" onclick="location.href='watchAnswer';" style="cursor:pointer">
	                                <div class="student_info">
	                                    <div class="sPic">
	                                        <img src="/resources/img/profile.png" alt="튜터프로필">
	                                    </div>
	                                    <div class="Sname">마바사</div>
	                                </div>
	                                <div class="ask_title">
	                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus minus laboriosam
	                                    pariatur consectetur excepturi.
	                                    <div class="answer_ok yes">답변 미완료</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                  
	                    </div>
	
	                    <nav aria-label="Page navigation example">
	                        <ul class="pagination justify-content-center p-5">
	                          <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a></li>
	                          <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
	                          <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a></li>
	                          <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
	                          <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a></li>
	                        </ul>
	                    </nav>
	
	                    </main>
	                </div>
	            </div>
	        </div>
	        </div>
	    </section>
	    <!-- End section -->
	
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
	        
	        $(function() {

	            CKEDITOR.replace('contents',{
	                // filebrowserUploadUrl: '/common/ckeditor/fileUpload',
	                font_names : "맑은 고딕/Malgun Gothic;굴림/Gulim;돋움/Dotum;바탕/Batang;궁서/Gungsuh;Arial/Arial;Comic Sans MS/Comic Sans MS;Courier New/Courier New;Georgia/Georgia;Lucida Sans Unicode/Lucida Sans Unicode;Tahoma/Tahoma;Times New Roman/Times New Roman;MS Mincho/MS Mincho;Trebuchet MS/Trebuchet MS;Verdana/Verdana",
	                font_defaultLabel : "맑은 고딕/Malgun Gothic",
	                fontSize_defaultLabel : "12",
	                skin : "office2013",
	                language : "ko"
	            });

	            // ...
	        });
	    </script>
	
	    <!-- Option 1: Bootstrap Bundle with Popper -->
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	        crossorigin="anonymous"></script>
	
	</body>

</html>