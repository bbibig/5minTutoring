<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

  <!doctype html>
  <html lang="ko">

  <head>
    <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
    <jsp:include page="../htmlHead.jsp" flush="true" />
    <link href="${path}/resources/css/2-00_tutorpage_baseform.css" rel="stylesheet">
    <link href="${path}/resources/css/2-03-04_tutorpage.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <!-- ========================================================= -->
    <style>
      #RVcount{
        font-weight: bold;
        margin-right: 10px;
      }
    </style>
    <script>
      $(function () {
        
        let resultReview = '${_RESULT_REVIEW_}';
        if (resultReview != "") {
          alert(resultReview);
        }//리뷰 결과

        let reviewForm = $('#reviewForm');
        $('#Review_btn').on('click', function () {
          reviewForm.attr("action", "/tutor/createReview");
          reviewForm.submit();
        });//리뷰 등록

        let removeForm = $('#removeForm')
        $('#removeReview').on('click', function(){          
          let tp_number = '${_TUTOR_INFO_.tp_number}';
          let rv_number = $('#rv_number').val();

          $.ajax({
            data : { 
              tp_number : tp_number,
              rv_number : rv_number 
            },
            url : "/tutor/removeReview",
            type : "GET",
            dataType : "text",
            success : function(result){
              if(result!="fail"){
                alert(result);
              }
              self.location="/tutor/writeReview?num="+tp_number;
            }//success
          })//ajax
        })//리뷰삭제
        
        $('#form1btn').on('click', function(){
          let array = new Array();
          let test1 = document.querySelectorAll("#modi");
          console.log(test1);

          $('#modi').each(function(){
            array.push(this.value);
            console.log(array);
          })

          let test = $('#rv_number').val();
          console.log(test);
          alert(test)
          
          
          // let rv_number = $('#rv_number').val();
          
          // $.ajax({
          //   data : { rv_number : rv_number },
          //   url : "/tutor/modify",
          //   type : "GET",
          //   success : function(result){
          //     console.log(result);
          //   }//success
          // })//ajax

        })//리뷰 수정
           
      });//jq
    </script>

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
                <img src="/resources/img/profile.png" alt="Admin" class="rounded-circle" width="150">
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

            <div class="headers d-flex flex-column">
              <div class="head-line">
                <h2>학생리뷰</h2>
              </div>
                <div class="review-count text-end"><span id="RVcount">후기 ${RVCOUNT} 개</span></div>
            </div>

            <div class="star-rating row d-flex flex-row justify-content-center">
              <!--우측섹션 평균별점 & 프로그래스 바 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

              <div class="rating-block col-4 d-flex flex-column justify-content-center align-items-center">
                <!-- 평균별점 -->
                <h2 class="average pb-5">${avgReview}</h2>
                <div class="stars">
                  <i class="fa fa-star star-color"></i>
                  <i class="fa fa-star star-color"></i>
                  <i class="fa fa-star star-color"></i>
                  <i class="fa fa-star star-color"></i>
                  <i class="fa fa-star"></i>
                </div>
              </div> <!-- 평균별점 end-->

              <div class="progress-number col-1 d-flex flex-column justify-content-around">
                <!---프로그래스바 점수-->
                <span class="badge bg-primary">5점</span>
                <span class="badge bg-primary">4점</span>
                <span class="badge bg-primary">3점</span>
                <span class="badge bg-primary">2점</span>
                <span class="badge bg-primary">1점</span>
              </div>
              <!---프로그래스바 점수 end-->

              <div class="star-progressbar col-7 d-flex flex-column justify-content-around">
                <!--프로그래스 바-->
                <div class="progress">
                  <div class="progress-bar bg-warning" role="progressbar" style="width: 100%" aria-valuenow="100"
                    aria-valuemin="0" aria-valuemax="100">
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-warning" role="progressbar" style="width: 80%" aria-valuenow="80"
                    aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-secondary" role="progressbar" style="width: 60%" aria-valuenow="60"
                    aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-secondary" role="progressbar" style="width: 40%" aria-valuenow="40"
                    aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-dark" role="progressbar" style="width: 20%" aria-valuenow="20"
                    aria-valuemin="" aria-valuemax="100"></div>
                </div>
              </div>
              <!--프로그래스 바 end-->

            </div>
            <!--우측 윗부분 평균별점 & 프로그래스 바 end !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            
            <!-- Tutor 는 리뷰 목록만 -->  
            <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}">
              <div class="div" style="height: 40px;"></div>
            </c:if> 

            <!-- Student 일때 리뷰 등록 가능 -->
            <c:if test="${__LOGIN_USER__.user_group eq 'Student'}">
              <!-- 리뷰 작성 박스-->
              <form method="post" id="reviewForm">
                <div class="comment-box d-flex flex-column align-items-center">
                  <input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" id="modi">
                  <input type="hidden" name="tp_number" value="${_TUTOR_INFO_.tp_number}" id="modi">

                  <div class="rating" id="modi">
                    <!-- <fieldset id="star_rating"> -->
                    <input type="radio" name="rv_star" value="5.0" id="5"><label for="5">☆</label>
                    <input type="radio" name="rv_star" value="4.0" id="4"><label for="4">☆</label>
                    <input type="radio" name="rv_star" value="3.0" id="3"><label for="3">☆</label>
                    <input type="radio" name="rv_star" value="2.0" id="2"><label for="2">☆</label>
                    <input type="radio" name="rv_star" value="1.0" id="1"><label for="1">☆</label>
                    <!-- </fieldset> -->
                  </div>
                  <p class="my-3">별점을 선택해주세요.</p>

                  <div class="text-box col-12">
                    <textarea name="rv_content" class="form-control" placeholder="후기를 남겨주세요!" id="floatingTextarea1 modi"
                      style="height: 150px"></textarea>
                  </div>

                  <div class="write-button align-self-end">
                    <a class="btn btn-dark my-2 col-push-4" id="Review_btn" type="button">등록</a>
                  </div>

                </div>
              </form>
              <!-- 리뷰 작성 박스 end-->
            </c:if> 


            <!-- 유저리뷰 전체-->
            <!-- 리뷰가 있어야 리스트 보임 -->
          <c:if test="${RVCOUNT != 0 }">

            <div class="review-block ">
              <!--최신순 / 낮은평점순 / 높은평점순 전체 필터-->
              <div class="filter">

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
              </div>
              <!--최신순 / 낮은평점순 / 높은평점순 전체 필터 end-->

              <div class="review-box">
                <!-- 리뷰 박스!!!!-->
                <c:forEach var="review" items="${_RIVIEWLIST_}">
                  <div class="test" value="${review.rv_star}"></div>
                  <div class="row d-flex flex-row">
                    <!--댓글 박스 첫번째 row -->
                    <div class="col-1 d-flex justify-content-center">
                      <!---댓글 프로필 사진-->
                      <div><img src="${path}/resources/img/profile.png" class="img-rounded" width="50"></div>
                    </div>
                    <!---댓글 프로필 사진 end-->


                    <div class="col-3 d-flex flex-column justify-content-center">
                      <!--댓글 별점&이름&날짜-->

                      <div class="stars_1" data-rate="${review.rv_star}">
                        <c:forEach begin="0" end="4" var="i">
                             <c:choose>
                                   <c:when test="${review.rv_star > i }">
                                       <i class="fa fa-star star-color fs-5"></i>
                                   </c:when>
                                   <c:otherwise>
                                       <i class="fa fa-star fs-5"></i>
                                   </c:otherwise>
                             </c:choose>
                        </c:forEach>
                    </div>

                      <div class="review-block-name">
                        <span class="review-name"><strong>${review.user_nick}</strong></span>
                        <span class="review-block-date ms-3">
                          <fmt:formatDate pattern="yyyy.MM.dd" value="${review.rv_date}" />
                        </span>
                      </div>

                    </div>
                    <!--댓글 별점&이름&날짜 end-->

                    <div class="hamburger-button col-8 d-flex justify-content-end">
                      <!-- 수정/삭제 버튼-->

                      <div class="dropdown">

                        <button class="btn pt-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
                          aria-expanded="false">
                          <i class="bi bi-list fs-2"></i>
                        </button>

                        <c:if test="javascript:loginReview">
                          111111111
                        </c:if>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                          <li class="list-unstyled">
                            <a class="dropdown-item" id="form1btn" data-bs-toggle="modal">수정</a>
                          </li>
                          <li class="list-unstyled">
                            <a class="dropdown-item" id="removeReview" data-bs-toggle="modal">삭제</a>
                          </li>
                        </ul>
                        <form id="removeForm">
                          <input type="hidden" name="tp_number" value="${_TUTOR_INFO_.tp_number}">
                          <input type="hidden" name="rv_number" value="${review.rv_number}" id="rv_number">
                        </form>
                      </div>

                    </div><!-- 수정/삭제 버튼-->

                  </div>
                  <!--댓글 박스 두번째 row -->

                  <div class="reply">
                    <div class="review-block-description">${review.rv_content}</div>
                  </div>
                  <hr>
                  <!--리뷰 나누는 줄-->
                </c:forEach>
              </div>

            </div> <!-- 유저리뷰 전체 블락 end-->
            
          </c:if>

            <div class="page-number d-flex flex-row justify-content-center">

              <!-- 페이징-->
              <nav aria-label="Page navigation example">
                <form action="#" id="PaginationFrom">
                    <input type="hidden" name="currPage">
                    <input type="hidden" name="amount">

                    <ul class="pagination justify-content-center p-5">

                        <li class="page-item"><a class="page-link rounded-circle" 
                            href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${_REVIEWPAGINATION_.startPage-1}" id="prev">&laquo;</a>
                        </li>

                        <li class="page-item"><a class="page-link rounded-circle" 
                          href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${_REVIEWPAGINATION_.cri.currPage-1}">&lt;</a>
                        </li>

                        <li class="page-item"><a class="page-link rounded-circle bg-blue" 
                            href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${_REVIEWPAGINATION_.cri.currPage}">${_REVIEWPAGINATION_.cri.currPage}</a>
                        </li>

                        <li class="page-item"><a class="page-link rounded-circle" 
                          href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${_REVIEWPAGINATION_.cri.currPage+1}">&gt;</a>
                        </li>

                        <li class="page-item"><a class="page-link rounded-circle" 
                            href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${_REVIEWPAGINATION_.endPage+1}">&raquo;</a>
                        </li>
                        <!-- <c:forEach var="page" begin="${_REVIEWPAGINATION_.startPage}" end="${_REVIEWPAGINATION_.endPage}">
                          <li class="page-item"><a class="page-link rounded-circle ${page==_ADMINPAGINATION_.cri.currPage?'page':''}" 
                              href="/tutor/writeReview?num=${_TUTOR_INFO_.tp_number}&currPage=${page}">${page}</a></li>
                       </c:forEach> -->
                    </ul>
                </form>
            </nav>
            <!--페이징-->

          </div>
          <!--우측섹션 end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

        </div>
        <!--전체 row end-->



        <!--------------------------=====================================모달 창=======================================------------------------------------------->

        <!--수정모달-->
        <div class="modal fade" id="form1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
          aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content d-flex flex-column justify-content-center">

              <div class="modal-body">
                <div class="pop-up-body d-flex flex-column align-items-center">

                  <div class="rating">
                    <input type="radio" name="rv_star" value="5.0" id="5"><label for="5">☆</label>
                    <input type="radio" name="rv_star" value="4.0" id="4"><label for="4">☆</label>
                    <input type="radio" name="rv_star" value="3.0" id="3"><label for="3">☆</label>
                    <input type="radio" name="rv_star" value="2.0" id="2"><label for="2">☆</label>
                    <input type="radio" name="rv_star" value="1.0" id="1"><label for="1">☆</label>
                  </div>

                  <p class="my-3">별점을 선택해주세요.</p>

                  <form class="was-validated col-12 d-flex flex-column">
                    <div class="text-box">
                      <textarea class="form-control" placeholder="" id="floatingTextarea1" style="height: 150px"
                        required value="${review.rv_content}"></textarea>
                    </div>

                    <div class="pop-up-button-box align-self-end">
                      <button type="button" class="btn btn-outline-primary"
                        data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
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
        <div class="modal fade" id="form2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
          aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content d-flex flex-column justify-content-center">

              <div class="modal-body">
                <div class="pop-up-body d-flex flex-column align-items-center">

                  <div class="warnning-img">
                    <i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
                  </div>

                  <p class="my-3 "><strong class="fs-4">삭제하시겠습니까?</strong></p>

                  <div class="pop-up-button-box d-flex flex-row align-self-center">
                    <button type="button" class="btn btn-outline-primary"data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-outline-primary" >확인</button>
                  </div>

                </div>

              </div>

            </div>
          </div>
        </div>



      </div>
      <!--섹션 전체 컨테이너 end-->

    </section>
    <!-- End section -->


    <!-- ============= 공통 footer + js =============== -->
    <jsp:include page="../footer.jsp" flush="true" />
    <!-- ============================================== -->

  </body>

  </html>