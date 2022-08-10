<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="ko">
  <head>

    <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
      <jsp:include page="../htmlHead.jsp" flush="true" />
      <link href="${path}/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
      <link href="${path}/resources/css/2-02_tutorpage.css" rel="stylesheet">
    <!-- ========================================================= -->  

    <title>튜터페이지-튜터정보</title>
    
  </head>
  <body>
     <!-- ============= 공통 Header : 로그인 후 =================== -->
	    <jsp:include page="../header_login.jsp" flush="true" />
     <!-- ========================================================= -->
  
    <!-- ======= Section ======= -->
    <section class="d-flex">
      <div class="container">
        <div class="row">
          
           <!--좌측섹션!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
          <div class="left-section col-3"> 
            
            <div class="card d-flex flex-column align-items-center text-center"> <!-- 프로필 카드영역==>프로필사진+이름+한줄소개+대표과목+누적답변+평점-->
              
              <div class="profile-image"> <!--프로필카드-프로필사진!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
                <img src="${path}/resources/img/profile.png" alt="Admin" class="rounded-circle" width="150">
              </div><!--프로필카드-프로필사진 end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
              
              <div class="tutorname_introduction">
                <h4>홍길동</h4>
                <p class="text-secondary mb-1">한줄소개: Lorem ipsum dolor sit, amet consectetur adipisicing elit. Dolores, quasi!</p>
              </div>
              
              <div class="emblem d-flex flex-row">
                <div class="emblem-circle rounded-circle "><br>대표과목<br>수학</div>
                <div class="emblem-circle rounded-circle "><br>누적답변<br>00개</div>
                <div class="emblem-circle rounded-circle "><br>평점<br>00점</div>
              </div>

            </div>  <!-- 프로필 카드영역==>프로필사진+이름+한줄소개+대표과목+누적답변+평점  end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

            <div class="left-nav" id="left-navigation"> <!--좌측섹션 nav!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
              <ul class="nav nav-pills nav-stacked flex-column">
                <li class="nav-item mt-3">
                  <a class="nav-link nav-tabs active" aria-current="page" href="/tutor/info" >튜터정보</a>
                </li>
                <li class="nav-item nav-tabs mt-3">
                  <a class="nav-link" href="/tutor/writeReview" >학생리뷰</a>
                </li>
                <li class="nav-item nav-tabs mt-3">
                  <a class="nav-link" href="/tutor/ask" >튜터에게 질문하기</a>
                </li>
                <li class="nav-item nav-tabs  mt-3">
                  <a class="nav-link" href="/tutor/tutoring" >튜터에게 과외받기</a>
                </li>
              </ul>

            </div> <!--좌측섹션 nav end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            
          </div> <!--좌측섹션 end!!!-->

          <!--우측 섹션!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
          <div class="right-section col-9"> 

            <!--우측섹션 header!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <div class="headers d-flex flex-row align-items-center justify-content-between"> 
              <div class="head-line"><h2><strong>튜터정보</strong></h2></div>
              
              <div class="edit-button d-flex flex-column">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tutor-info-edit">
                  수정
                </button>
              </div>
              
            </div><!--우측섹션 header end !!!!!!-->

            <!--============================튜터 info !!!!!!!!!!!!!!!!!!-->
            <div class="tutor-info d-flex flex-column justify-content-center"> 
              <div class="tutor-career flex-column ms-5">
                
                <div class="graduation row">
                 
                  <div class="col-1">
                    &nbsp;<span class="badge bg-dark">학력</span>
                  </div>
                 
                  <div class="col-11">
                    <p>오분대학교 재학</p>
                  </div>

                </div>

                <div class="history row">
                  
                  <div class="col-1">
                    &nbsp;<span class="badge bg-dark">경력</span>
                  </div>

                  <div class="col-11">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing.</p>
                  </div>

                </div>

              </div>

              <div class="introduction ms-5 mt-2">
                <div class="introduction-title d-flex flex-row justify-content-baseline align-items-center">
                    
                  <div>
                    &nbsp;<span class="badge bg-dark">튜터소개</span>
                  </div>
                  
                  <div class="ju">
                    <h2 class="mb-0 ms-4">Lorem ipsum dolor sit amet consectetur.</h2>
                  </div>

                </div>
                 
                <div class="introduction-text">
                  
                  <span class="mt-4">
                    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Autem asperiores dolorem inventore. Temporibus qui natus unde cupiditate perferendis, dolores doloremque ut neque magnam reprehenderit quidem suscipit sed, iste maiores sit at delectus quaerat adipisci. Facilis eos eveniet ea nam deserunt?
                  </span>
                  
                </div>
                  
                
              </div>

            </div>
            <!--============================튜터 info end!!!!!!!!!!!!!!!!!!-->


          </div> <!--우측섹션 end!!!!!!-->



        </div> <!--전체 row end-->
          
        <!--수정모달-->
        <div class="modal fade" id="tutor-info-edit" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content d-flex flex-column justify-content-center">
               
              <div class="modal-body" > 
                <!-- ------------------------------------------------------------------- -->
                <form class="was-validated d-flex flex-column">
                    
                  <label for="validationDefault01" class="form-label mb-0">학력</label>
                  <input type="text" class="form-control" id="validationDefault01" required>

                  <label for="validationDefault02" class="form-label mb-0">경력</label>
                  <input type="text" class="form-control" id="validationDefault02" required>

                  <label for="validationDefault03" class="form-label mb-0">제목</label>
                  <input type="text" class="form-control" id="validationDefault03" required>

                  <label for="validationDefault04" class="form-label mb-0">내용</label>
                  <textarea class="form-control" placeholder="" id="validationDefault04" style="height: 150px" required></textarea>

                  <div class="pop-up-button-box align-self-end mt-3">
                    <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-outline-primary">확인</button>
                  </div> 

                </form>    
                
                <!-- ------------------------------------------------------------------- -->
              </div>
            
            </div>
          </div>
        </div>
          
        
      </div> <!--섹션 전체 컨테이너 end-->
    
    </section>
    <!-- End section -->  
  
     <!-- ============= 공통 footer + js =============== -->
     <jsp:include page="../footer.jsp" flush="true" />
     <!-- ============================================== -->

  </body>
</html>