<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">
  <head>
     <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
      <jsp:include page="../htmlHead.jsp" flush="true" />
      <link href="${path}/resources/css/3-01_community.css" rel="stylesheet">
      <!-- ========================================================= -->

    <title>튜터페이지-커뮤니티</title>
    
  </head>
  <body>
    <!-- ============= 공통 Header : 로그인 후 =================== -->
	    <jsp:include page="../header_login.jsp" flush="true" />
     <!-- ========================================================= -->
    
    <!-- ======= Section ======= -->
    <section class="">
      <div class="container">
        <div class="row">         <!--===============================배너================================-->
          <div class="banner">
            <p style="line-height: 50px">&nbsp;&nbsp;&nbsp;커뮤니티</p>
          </div>                 
        </div> <!--===============================배너================================-->

        <div class="coummunity-board"> <!--====================게시판(1.게시판 필터/검색/글쓰기 + 2.게시글) ==================================-->
          
          <div class="row d-flex flex-row" id="filter-box"> <!--============================1.게시판 필터/검색/글쓰기========================================-->

            <div class="col-1 d-flex flex-row align-items-center">
              <span class="badge text-dark fs-6">View&nbsp;&nbsp;</span>
            </div>
            
            <div class="col-4 d-flex flex-row align-items-center">
              <ul class="d-flex flex-row mb-0 ps-0 ">
                <li class="list-unstyled text-dark ms-5"><a href="#"><span class="text-dark">최신순</span></a></li>
                <li class="list-unstyled ms-5"><a href="#"><span class="text-dark">낮은평점순</span></a></li>
                <li class="list-unstyled ms-5"><a href="#"><span class="text-dark">높은평점순</span></a></li>
              </ul>
            </div>

            <div class="col-7">
              <div class="row d-flex flex-row justify-content-end">
                <form class="d-flex col-5"> 
                  <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search"> 
                  <button class="btn btn-primary" type="submit" id="search-button">검색</button> 
                </form>
  
                <button id="write-button" type="submit" class="btn btn-dark col-2" data-bs-toggle="modal" data-bs-target="#community-write">
                  글쓰기
                </button>

                <!--====================================글쓰기 모달=================================================-->
                <div class="modal fade" id="community-write">
                  <div class="modal-dialog modal-lg">
                      <div class="modal-content">
                          
                          <div class="modal-header">
                              <h5 class="modal-title" id="exampleModalLabel"># 글쓰기</h5>
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

                </div><!--====================================글쓰기 모달=================================================-->

                
              </div>  

            </div>
          </div> <!--============================1.게시판 필터/검색/글쓰기 end ========================================-->

          <!--============================ 2.게시글 ========================================-->
          <div class="board mt-5">
            
            <div class="row d-flex flex-column">  

              <a href="/community/post" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                <div class="board-box">
                <div class="row">
                  
                  <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->

                    <div class="title">
                      <h4>Lorem ipsum dolor sit, amet consectetur adipisicing.</h4>
                    </div>

                    <div class="content">
                      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste culpa accusamus illo eos error. Est libero molestiae commodi alias distinctio minus cumque praesentium laborum placeat quasi repellat, debitis voluptates ut!</p>
                    </div>

                    <div class="user_date d-flex flex-row">
                      <div class="user">
                        <strong>홍길동</strong>
                      </div>
                      <div class="date ms-4">
                        2022-01-1
                      </div>
                    </div>
                  </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                  

                  
                  <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->

                    <div class="question_comment">
                      <span class="comment_count">0</span>
                      <span class="comment_decription">답변</span>
                    </div>

                    <div class="heart d-flex mt-4">
                      <button class="heart-button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                        </svg>

                        <span class="heart-count ms-1">0</span>

                      </button>

                    </div>

                  </div> <!--========================답변 카운트 및 heart 카운트end==========================-->
                  
                  
                </div>
                
                </div>
              </a><!--===============================================커뮤니티 박스end============================================-->

              <a href="/community/post" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                <div class="board-box">
                <div class="row">
                  
                  <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->

                    <div class="title">
                      <h4>Lorem ipsum dolor sit, amet consectetur adipisicing.</h4>
                    </div>

                    <div class="content">
                      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste culpa accusamus illo eos error. Est libero molestiae commodi alias distinctio minus cumque praesentium laborum placeat quasi repellat, debitis voluptates ut!</p>
                    </div>

                    <div class="user_date d-flex flex-row">
                      <div class="user">
                        <strong>홍길동</strong>
                      </div>
                      <div class="date ms-4">
                        2022-01-1
                      </div>
                    </div>
                  </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                  

                  
                  <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->

                    <div class="question_comment">
                      <span class="comment_count">0</span>
                      <span class="comment_decription">답변</span>
                    </div>

                    <div class="heart d-flex mt-4">
                      <button class="heart-button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                        </svg>

                        <span class="heart-count ms-1">0</span>

                      </button>

                    </div>

                  </div> <!--========================답변 카운트 및 heart 카운트end==========================-->
                  
                  
                </div>
                
                </div>
              </a><!--===============================================커뮤니티 박스end============================================-->

              <a href="/community/post" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                <div class="board-box">
                <div class="row">
                  
                  <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->

                    <div class="title">
                      <h4>Lorem ipsum dolor sit, amet consectetur adipisicing.</h4>
                    </div>

                    <div class="content">
                      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste culpa accusamus illo eos error. Est libero molestiae commodi alias distinctio minus cumque praesentium laborum placeat quasi repellat, debitis voluptates ut!</p>
                    </div>

                    <div class="user_date d-flex flex-row">
                      <div class="user">
                        <strong>홍길동</strong>
                      </div>
                      <div class="date ms-4">
                        2022-01-1
                      </div>
                    </div>
                  </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                  

                  
                  <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->

                    <div class="question_comment">
                      <span class="comment_count">0</span>
                      <span class="comment_decription">답변</span>
                    </div>

                    <div class="heart d-flex mt-4">
                      <button class="heart-button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                        </svg>

                        <span class="heart-count ms-1">0</span>

                      </button>

                    </div>

                  </div> <!--========================답변 카운트 및 heart 카운트end==========================-->
                  
                  
                </div>
                
                </div>
              </a><!--===============================================커뮤니티 박스end============================================-->

              <a href="/community/post" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                <div class="board-box">
                <div class="row">
                  
                  <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->

                    <div class="title">
                      <h4>Lorem ipsum dolor sit, amet consectetur adipisicing.</h4>
                    </div>

                    <div class="content">
                      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste culpa accusamus illo eos error. Est libero molestiae commodi alias distinctio minus cumque praesentium laborum placeat quasi repellat, debitis voluptates ut!</p>
                    </div>

                    <div class="user_date d-flex flex-row">
                      <div class="user">
                        <strong>홍길동</strong>
                      </div>
                      <div class="date ms-4">
                        2022-01-1
                      </div>
                    </div>
                  </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                  

                  
                  <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->

                    <div class="question_comment">
                      <span class="comment_count">0</span>
                      <span class="comment_decription">답변</span>
                    </div>

                    <div class="heart d-flex mt-4">
                      <button class="heart-button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                        </svg>

                        <span class="heart-count ms-1">0</span>

                      </button>

                    </div>

                  </div> <!--========================답변 카운트 및 heart 카운트end==========================-->
                  
                  
                </div>
                
                </div>
              </a><!--===============================================커뮤니티 박스end============================================-->

              <a href="/community/post" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                <div class="board-box">
                <div class="row">
                  
                  <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->

                    <div class="title">
                      <h4>Lorem ipsum dolor sit, amet consectetur adipisicing.</h4>
                    </div>

                    <div class="content">
                      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste culpa accusamus illo eos error. Est libero molestiae commodi alias distinctio minus cumque praesentium laborum placeat quasi repellat, debitis voluptates ut!</p>
                    </div>

                    <div class="user_date d-flex flex-row">
                      <div class="user">
                        <strong>홍길동</strong>
                      </div>
                      <div class="date ms-4">
                        2022-01-1
                      </div>
                    </div>
                  </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                  

                  
                  <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->

                    <div class="question_comment">
                      <span class="comment_count">0</span>
                      <span class="comment_decription">답변</span>
                    </div>

                    <div class="heart d-flex mt-4">
                      <button class="heart-button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                        </svg>

                        <span class="heart-count ms-1">0</span>

                      </button>

                    </div>

                  </div> <!--========================답변 카운트 및 heart 카운트end==========================-->
                  
                  
                </div>
                
                </div>
              </a><!--===============================================커뮤니티 박스end============================================-->
           
            </div>

          </div><!--============================ 2.게시글 end========================================-->

          <div class="page-button d-flex flex-row justify-content-center mt-3"> <!---======================페이지 버튼 end =======================-->
            <nav aria-label="Page navigation example">
              <ul class="pagination">
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div> <!---======================페이지 버튼 end =========================-->
          
        </div> <!--====================게시판(1.게시판 필터/검색/글쓰기 + 2.게시글) end ==================================-->

        
        
       
        
      </div> <!--섹션 전체 컨테이너 end-->
    
    </section><!-- End section -->
      
    
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