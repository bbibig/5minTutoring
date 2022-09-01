<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="ko">
  <head>
    <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
      <jsp:include page="../htmlHead.jsp" flush="true" />
      <link href="${path}/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
      <link href="${path}/resources/css/2-03-04_tutorpage.css" rel="stylesheet">
    <!-- ========================================================= -->  

    <title>튜터페이지-리뷰작성</title>
    
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

          <div class="right-section col-9"> 
            
            <div class="headers d-flex flex-column">  
              <div class="head-line"><h2>학생리뷰</h2></div>
              <div class="review-count text-end">후기 총??개</div>
            </div>

            <div class="star-rating row d-flex flex-row justify-content-center"> <!--우측섹션 평균별점 & 프로그래스 바 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
                
              <div class="rating-block col-4 d-flex flex-column justify-content-center align-items-center">  <!-- 평균별점 -->
                <h2 class="average pb-5">4.0</h2>
                  <div class="stars">
                    <i class="fa fa-star star-color"></i>
                    <i class="fa fa-star star-color"></i>
                    <i class="fa fa-star star-color"></i>
                    <i class="fa fa-star star-color"></i>
                    <i class="fa fa-star"></i>
                  </div>
              </div>  <!-- 평균별점 end-->

              <div class="progress-number col-1 d-flex flex-column justify-content-around">  <!---프로그래스바 점수-->
                <span class="badge bg-primary">5점</span>
                <span class="badge bg-primary">4점</span>
                <span class="badge bg-primary">3점</span>
                <span class="badge bg-primary">2점</span>
                <span class="badge bg-primary">1점</span>
              </div><!---프로그래스바 점수 end-->

              <div class="star-progressbar col-7 d-flex flex-column justify-content-around"> <!--프로그래스 바-->
                <div class="progress">
                  <div class="progress-bar bg-warning" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-warning" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-secondary" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-secondary" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-dark" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="" aria-valuemax="100"></div>
                </div>
              </div><!--프로그래스 바 end-->			
            
            </div><!--우측 윗부분 평균별점 & 프로그래스 바 end !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            
            <div class="comment-box d-flex flex-column align-items-center"> <!-- 리뷰 작성 박스-->
                <div class="rating "> 
                  <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                  <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label> 
                  <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                  <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                  <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
                </div>

                <p class="my-3">별점을 선택해주세요.</p>

                
                <div class="text-box col-12">                              
                  <textarea class="form-control" placeholder="후기를 남겨주세요!" id="floatingTextarea1" style="height: 150px"></textarea>
                </div>

                <div class="write-button align-self-end">
                  <a class="btn btn-dark my-2 col-push-4" type="submit" href="/tutor/reviewList">등록</a>
                </div>

            </div><!-- 리뷰 작성 박스 end-->

            <div class="review-block "><!-- 유저리뷰 전체 블락  -->
                  
              <div class="filter"> <!--최신순 / 낮은평점순 / 높은평점순 전체 필터-->

                <div class="row">
                  <div class="col-1">
                    <span class="badge text-dark fs-6">View&nbsp;&nbsp;|</span>
                  </div>
                  
                  <div class="col-4">
                    <ul class="d-flex flex-row justify-content-around mb-0 ps-0">
                      <li class="list-unstyled text-dark"><a href="#"><span class="text-dark">최신순</span></a></li>
                      <li class="list-unstyled"><a href="#"><span class="text-dark">낮은평점순</span></a></li>
                      <li class="list-unstyled"><a href="#"><span class="text-dark">높은평점순</span></a></li>
                    </ul>
                  </div>

                </div>
                
                <hr>
              </div> <!--최신순 / 낮은평점순 / 높은평점순 전체 필터 end-->

              <div class="review-box"> <!-- 리뷰 박스!!!!-->

                <div class="row d-flex flex-row"><!--댓글 박스 첫번째 row -->
                  <div class="col-1 d-flex justify-content-center"> <!---댓글 프로필 사진-->
                    <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                  </div> <!---댓글 프로필 사진 end-->

                  <div class="col-3 d-flex flex-column justify-content-center"> <!--댓글 별점&이름&날짜-->                    
                    <div class="stars">
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star fs-5"></i>
                    </div>

                    <div class="review-block-name">
                      <span class="review-name"><strong>홍길동</strong></span>
                      <span class="review-block-date ms-3">2022-01-01</span> 
                    </div>

                  </div><!--댓글 별점&이름&날짜 end--> 

                  <div class="hamburger-button col-8 d-flex justify-content-end"> <!-- 수정/삭제 버튼-->
                    
                    <div class="dropdown">
                      
                      <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list fs-2" ></i>
                      </button>
                      
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li class="list-unstyled"><a class="dropdown-item" href="#form1" data-bs-toggle="modal">수정</a></li> 
                        <li class="list-unstyled"><a class="dropdown-item" href="#form2" data-bs-toggle="modal">삭제</a></li> 
                      </ul>

                    </div>
      
                  </div><!-- 수정/삭제 버튼-->
      
                </div><!--댓글 박스 두번째 row -->
                
                
                <div class="reply">
                  <div class="review-block-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis deserunt aspernatur harum soluta voluptatem possimus fugit, laboriosam aut. Doloribus, quas?</div>
                </div>

                <hr> <!--리뷰 나누는 줄-->

              </div>   
              
              <div class="review-box"> <!-- 리뷰 박스!!!!-->

                <div class="row"><!--댓글 박스 첫번째 row -->
                  <div class="col-1 d-flex justify-content-center"> <!---댓글 프로필 사진-->
                    <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                  </div> <!---댓글 프로필 사진 end-->

                  <div class="col-3 d-flex flex-column justify-content-center"> <!--댓글 별점&이름&날짜-->                    
                    <div class="stars">
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star fs-5"></i>
                    </div>

                    <div class="review-block-name">
                      <span class="review-name"><strong>홍길동</strong></span>
                      <span class="review-block-date ms-3">2022-01-01</span> 
                    </div>

                  </div><!--댓글 별점&이름&날짜 end--> 

                  <div class="hamburger-button col-8 d-flex justify-content-end"> <!-- 수정/삭제 버튼-->
                    
                    <div class="dropdown">
                      
                      <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list fs-2" ></i>
                      </button>
                      
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li class="list-unstyled"><a class="dropdown-item" href="#form1" data-bs-toggle="modal">수정</a></li> 
                        <li class="list-unstyled"><a class="dropdown-item" href="#form2" data-bs-toggle="modal">삭제</a></li> 
                      </ul>

                    </div>
      
                  </div><!-- 수정/삭제 버튼-->


                </div><!--댓글 박스 두번째 row -->
                
                
                <div class="reply">
                  <div class="review-block-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis deserunt aspernatur harum soluta voluptatem possimus fugit, laboriosam aut. Doloribus, quas?</div>
                </div>

                <hr> <!--리뷰 나누는 줄-->

              </div>

              <div class="review-box"> <!-- 리뷰 박스!!!!-->

                <div class="row"><!--댓글 박스 첫번째 row -->
                  <div class="col-1 d-flex justify-content-center"> <!---댓글 프로필 사진-->
                    <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                  </div> <!---댓글 프로필 사진 end-->

                  <div class="col-3 d-flex flex-column justify-content-center"> <!--댓글 별점&이름&날짜-->                    
                    <div class="stars">
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star fs-5"></i>
                    </div>

                    <div class="review-block-name">
                      <span class="review-name"><strong>홍길동</strong></span>
                      <span class="review-block-date ms-3">2022-01-01</span> 
                    </div>

                  </div><!--댓글 별점&이름&날짜 end--> 

                  <div class="hamburger-button col-8 d-flex justify-content-end"> <!-- 수정/삭제 버튼-->
                    
                    <div class="dropdown">
                      
                      <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list fs-2" ></i>
                      </button>
                      
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li class="list-unstyled"><a class="dropdown-item" href="#form1" data-bs-toggle="modal">수정</a></li> 
                        <li class="list-unstyled"><a class="dropdown-item" href="#form2" data-bs-toggle="modal">삭제</a></li> 
                      </ul>

                    </div>
      
                  </div><!-- 수정/삭제 버튼-->


                </div><!--댓글 박스 두번째 row -->
                
                
                <div class="reply">
                  <div class="review-block-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis deserunt aspernatur harum soluta voluptatem possimus fugit, laboriosam aut. Doloribus, quas?</div>
                </div>

                <hr> <!--리뷰 나누는 줄-->

              </div>

              <div class="review-box"> <!-- 리뷰 박스!!!!-->

                <div class="row"><!--댓글 박스 첫번째 row -->
                  <div class="col-1 d-flex justify-content-center"> <!---댓글 프로필 사진-->
                    <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                  </div> <!---댓글 프로필 사진 end-->

                  <div class="col-3 d-flex flex-column justify-content-center"> <!--댓글 별점&이름&날짜-->                    
                    <div class="stars">
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star fs-5"></i>
                    </div>

                    <div class="review-block-name">
                      <span class="review-name"><strong>홍길동</strong></span>
                      <span class="review-block-date ms-3">2022-01-01</span> 
                    </div>

                  </div><!--댓글 별점&이름&날짜 end--> 

                  <div class="hamburger-button col-8 d-flex justify-content-end"> <!-- 수정/삭제 버튼-->
                    
                    <div class="dropdown">
                      
                      <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list fs-2" ></i>
                      </button>
                      
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li class="list-unstyled"><a class="dropdown-item" href="#form1" data-bs-toggle="modal">수정</a></li> 
                        <li class="list-unstyled"><a class="dropdown-item" href="#form2" data-bs-toggle="modal">삭제</a></li> 
                      </ul>

                    </div>
      
                  </div><!-- 수정/삭제 버튼-->


                </div><!--댓글 박스 두번째 row -->
                
                
                <div class="reply">
                  <div class="review-block-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis deserunt aspernatur harum soluta voluptatem possimus fugit, laboriosam aut. Doloribus, quas?</div>
                </div>

                <hr> <!--리뷰 나누는 줄-->

              </div>

              <div class="review-box"> <!-- 리뷰 박스!!!!-->

                <div class="row"><!--댓글 박스 첫번째 row -->
                  <div class="col-1 d-flex justify-content-center"> <!---댓글 프로필 사진-->
                    <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                  </div> <!---댓글 프로필 사진 end-->

                  <div class="col-3 d-flex flex-column justify-content-center"> <!--댓글 별점&이름&날짜-->                    
                    <div class="stars">
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star star-color fs-5"></i>
                      <i class="fa fa-star fs-5"></i>
                    </div>

                    <div class="review-block-name">
                      <span class="review-name"><strong>홍길동</strong></span>
                      <span class="review-block-date ms-3">2022-01-01</span> 
                    </div>

                  </div><!--댓글 별점&이름&날짜 end--> 

                  <div class="hamburger-button col-8 d-flex justify-content-end"> <!-- 수정/삭제 버튼-->
                    
                    <div class="dropdown">
                      
                      <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list fs-2" ></i>
                      </button>
                      
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li class="list-unstyled"><a class="dropdown-item" href="#form1" data-bs-toggle="modal">수정</a></li> 
                        <li class="list-unstyled"><a class="dropdown-item" href="#form2" data-bs-toggle="modal">삭제</a></li> 
                      </ul>

                    </div>
      
                  </div><!-- 수정/삭제 버튼-->


                </div><!--댓글 박스 두번째 row -->
                
                
                <div class="reply">
                  <div class="review-block-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis deserunt aspernatur harum soluta voluptatem possimus fugit, laboriosam aut. Doloribus, quas?</div>
                </div>

                <hr> <!--리뷰 나누는 줄-->

              </div>
                    
            </div> <!-- 유저리뷰 전체 블락 end-->
          
            <div class="page-number d-flex flex-row justify-content-center">   <!--페이지 넘버-->
              <nav aria-label="Page navigation example">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item active"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
            </div> <!--페이지 넘버end-->

          </div> <!--우측섹션 end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

        </div> <!--전체 row end-->
          


        <!--------------------------=====================================모달 창=======================================------------------------------------------->
        
        <!--수정모달-->
        <div class="modal fade" id="form1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content d-flex flex-column justify-content-center">
               
              <div class="modal-body" > 
                <div class="pop-up-body d-flex flex-column align-items-center"> 

                  <div class="rating "> 
                    <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                    <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label> 
                    <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                    <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                    <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
                  </div>
  
                  <p class="my-3">별점을 선택해주세요.</p>

                  <form class="was-validated col-12 d-flex flex-column">
                      <div class="text-box">                              
                        <textarea class="form-control" placeholder="" id="floatingTextarea1" style="height: 150px" required></textarea>
                      </div>
                      <!-- <div class="invalid-feedback">
                        리뷰를작성해주세요.
                      </div> -->

                      <div class="pop-up-button-box align-self-end">
                        <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
                        <button type="submit" class="btn btn-outline-primary">확인</button>
                      </div> 
                  </form>

                </div>

              </div>
            
            </div>
          </div>
        </div>
        <!--수정모달-->
        
        <!--삭제모달-->
        <div class="modal fade" id="form2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content d-flex flex-column justify-content-center">
               
              <div class="modal-body"> 
                <div class="pop-up-body d-flex flex-column align-items-center"> 

                  <div class="warnning-img">
                    <i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
                  </div>
  
                  <p class="my-3 "><strong class="fs-4">삭제하시겠습니까?</strong></p>

                  <div class="pop-up-button-box d-flex flex-row align-self-center">
                    <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-outline-primary">확인</button>
                  </div> 
    
                </div>

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