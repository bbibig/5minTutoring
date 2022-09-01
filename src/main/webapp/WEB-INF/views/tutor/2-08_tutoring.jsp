<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
  <head>
    <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
    <jsp:include page="../htmlHead.jsp" flush="true" />
    <link href="/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
    <link href="/resources/css/2-08_tutorpage_tutoring.css" rel="stylesheet">
    <!-- ========================================================= -->

    <title>튜터페이지 - 튜터에게과외받기</title>
  
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
                <img src="/resources/img/profile.png" alt="Admin" class="rounded-circle" width="150">
              </div>
              <div class="tutorname_introduction">
                <h4>${_TUTOR_INFO_.user_name}</h4>
                <p class="text-secondary mb-1">${_TUTOR_INFO_.tp_title}</p>
              </div>
              
              <div class="emblem d-flex flex-row">
                <div class="emblem-circle rounded-circle "><br>대표과목<br>${_TUTOR_INFO_.tt_subject}</div>
                <div class="emblem-circle rounded-circle "><br>누적답변<br>${_TUTOR_INFO_.tp_accu_answer}</div>
                <div class="emblem-circle rounded-circle "><br>평점<br>${_TUTOR_INFO_.tp_average}</div>
              </div>
            </div>  
			<!-- nav bar -->
            <div class="left-nav" id="left-navigation">
				<ul class="nav nav-pills nav-stacked flex-column">
					<li class="nav-item mt-3"><a class="nav-link nav-tabs active" aria-current="page"
							href="/tutor/info/?num=${_TUTOR_INFO_.tp_number}">튜터정보</a></li>
					<li class="nav-item nav-tabs mt-3"><a class="nav-link"
							href="/tutor/writeReview/?num=${_TUTOR_INFO_.tp_number}">학생리뷰</a></li>
					<li class="nav-item nav-tabs mt-3"><a class="nav-link" 
							href="/tutor/ask/?num=${_TUTOR_INFO_.tp_number}">튜터에게 질문하기</a></li>
					<li class="nav-item nav-tabs  mt-3"><a class="nav-link"
							href="/tutor/tutoring/?num=${_TUTOR_INFO_.tp_number}">튜터에게 과외받기</a></li>
				</ul>
			</div>
          </div> 
 
		  <!-- 우측 section -->        
          <div class="right-section col-9"> 
            <div class="headers d-flex flex-row align-items-center justify-content-between ms-5"> 
              <div class="head-line"><h2><strong>튜터에게 과외받기</strong></h2></div>

              <div class="edit-button d-flex flex-column">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tutor-ask">
                  과외요청하기
                </button>
              </div>
              
              <!-- 과외요청하기 modal -->
              <div class="modal fade" id="tutor-ask"> 
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"># 과외요청</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        
                        <div class="modal-body">
                            <form>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label">제목</label>
                                    <input type="text" class="form-control" id="askTitle" placeholder="제목을 입력해주세요.">
                                </div>
                                <div class="mb-3">
                                    <label for="message-text" class="col-form-label">내용</label>
                                    <textarea class="form-control" id="askContent" placeholder="내용을 입력해주세요."></textarea>
                                </div>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary col-2" data-bs-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-primary col-2">저장</button>
                        </div>
                    </div>
                </div>
              </div>
            </div>

            
            <div class="tutoring-list d-flex flex-column ms-5 mt-3"> <!--======================과외 리스트=========================== -->
              
              <div class="list-box"> <!--===========================리스트박스===============================-->
                <div class="row mt-3 d-flex flex-row align-items-center">
                  <div class="col-1"><span class="badge bg-primary">과외중</span></div>
                  <div class="col-8" id="tutoring-title"><a href="/tutor/tutoringAsk" style="color:black" class="fs-4">Lorem ipsum dolor sit amet, consectetur adipisicing.</a></div>
                  <div class="col-1"><span>홍길동</span></div>
                  <div class="col-2"><button class="btn btn-primary ms-5">취소</button></div>
                </div>

                <hr>
              </div> 

              <div class="list-box"> <!--===========================리스트박스===============================-->
                <div class="row mt-3 d-flex flex-row align-items-center">
                  <div class="col-1"><span class="badge bg-secondary">대기중</span></div>
                  <div class="col-8" id="tutoring-title"><a href="/tutor/tutoringAsk" style="color:black" class="fs-4">Lorem ipsum dolor sit amet, consectetur adipisicing.</a></div>
                  <div class="col-1"><span>홍길동</span></div>
                  <div class="col-2"><button class="btn btn-primary ms-5">취소</button></div>
                </div>

                <hr>
              </div> 

              <div class="list-box"> <!--===========================리스트박스===============================-->
                <div class="row mt-3 d-flex flex-row align-items-center">
                  <div class="col-1"><span class="badge bg-secondary">대기중</span></div>
                  <div class="col-8" id="tutoring-title"><a href="/tutor/tutoringAsk" style="color:black" class="fs-4">Lorem ipsum dolor sit amet, consectetur adipisicing.</a></div>
                  <div class="col-1"><span>홍길동</span></div>
                  <div class="col-2"><button class="btn btn-primary ms-5">취소</button></div>
                </div>

                <hr>
              </div> 

              <div class="list-box"> <!--===========================리스트박스===============================-->
                <div class="row mt-3 d-flex flex-row align-items-center">
                  <div class="col-1"><span class="badge bg-secondary">대기중</span></div>
                  <div class="col-8" id="tutoring-title"><a href="/tutor/tutoringAsk" style="color:black" class="fs-4">Lorem ipsum dolor sit amet, consectetur adipisicing.</a></div>
                  <div class="col-1"><span>홍길동</span></div>
                  <div class="col-2"><button class="btn btn-primary ms-5">취소</button></div>
                </div>

                <hr>
              </div> 

              <div class="list-box"> <!--===========================리스트박스===============================-->
                <div class="row mt-3 d-flex flex-row align-items-center">
                  <div class="col-1"><span class="badge bg-secondary">대기중</span></div>
                  <div class="col-8" id="tutoring-title"><a href="/tutor/tutoringAsk" style="color:black" class="fs-4">Lorem ipsum dolor sit amet, consectetur adipisicing.</a></div>
                  <div class="col-1"><span>홍길동</span></div>
                  <div class="col-2"><button class="btn btn-primary ms-5">취소</button></div>
                </div>

                <hr>
              </div> 

            </div> <!--======================과외 리스트 end=========================== -->

            <div class="page-number d-flex flex-row justify-content-center">   <!--페이지 넘버-->
             
              <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center p-5">
                  <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a></li>
                  <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
                  <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a></li>
                  <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
                  <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a></li>
                </ul>
              </nav>
            </div> <!--페이지 넘버end-->

            

          </div> <!--우측섹션 end!!!!!!-->



        </div> <!--전체 row end-->
          
       
          
        
      </div> <!--섹션 전체 컨테이너 end-->
    
    </section>
    <!-- End section -->  
    
    <!-- ============= 공통 footer + js =============== -->
    <jsp:include page="../footer.jsp" flush="true" />
    <!-- ============================================== -->
  
    <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/translations/ko.js"></script>
    <script>
        ClassicEditor.create(document.querySelector('#askContent'), {
            language: "ko"
        });
    </script>
    
  </body>
</html>