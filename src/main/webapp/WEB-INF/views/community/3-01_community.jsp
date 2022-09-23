<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        


<!doctype html>
<html lang="ko">
  <head>
     <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
      <jsp:include page="../htmlHead.jsp" flush="true" />
      <link href="${path}/resources/css/3-01_community.css" rel="stylesheet">
      <!-- ========================================================= -->

      <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js" integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <script>
        $(function(){
          $('#regBtn').on('click',()=>{
            self.location = "/community";
          }); //onclick


        });
      </script> -->

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
                  <input class="form-control mr-2" type="search" name=keyword value="${_COMMUNITYPAGE_.cri.keyword}" placeholder="Search" aria-label="Search"> 
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
                              <form action="/community/register" method="post">
                                  <div class="mb-3">
                                      <label for="recipient-name" class="col-form-label">제목</label>
                                      <input type="hidden" name="user_email" value= "${__LOGIN_USER__.user_email}">
                                      <input type="text" class="form-control" name="fb_title" id="askTitle" placeholder="제목을 입력해주세요.">
                                  </div>
                                  <div class="mb-3">
                                      <label for="message-text" class="col-form-label">내용</label>
                                      <textarea class="form-control" id="askContent" name="fb_content" placeholder="내용을 입력해주세요."></textarea>
                                  </div>
                                  

                                  <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary col-2" data-bs-dismiss="modal">취소</button>
                                    <button type="submit" class="btn btn-primary col-2" id="regBtn">저장</button>
                                </div>
                              </form>
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
              <c:forEach var="communityBoard" items="${_LIST_}">
                <a href="/community/post?fb_number=${communityBoard.fb_number}" id="coummunity-box" class="mt-4"><!--===============================================커뮤니티 박스============================================-->
                  <div class="board-box">
                  <div class="row">
                    
                    <div class="col-10 d-flex flex-column"><!--============================================게시판 제목/내용/작성자/날짜================================================-->
  
                      <div class="title">
                        <h4>${communityBoard.fb_title}</h4>
                      </div>
  
                      <div class="content">
                        <p>${communityBoard.fb_content}</p>
                      </div>
  
                      <div class="user_date d-flex flex-row">
                        <div class="user">
                          <strong>${communityBoard.user_nick}</strong>
                        </div>
                        <div class="date ms-4">
                      	<fmt:formatDate value="${communityBoard.fb_date}" pattern="yyyy.MM.dd" />
                  
                        </div>
                      </div>
                    </div><!--============================================게시판 제목/내용/작성자/날짜 end================================================-->
                    
  
                    
                    <div class="col-2 d-flex flex-column align-items-center"><!--========================답변 카운트 및 heart 카운트==========================-->
  
                      <div class="question_comment">
                        <span class="comment_count">0</span>
                        <span class="comment_decription">댓글</span>
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
              </c:forEach>
            </div>

          </div><!--============================ 2.게시글 end========================================-->

          <div class="page-button d-flex flex-row justify-content-center mt-3"> <!---======================페이지 버튼 end =======================-->
            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-center p-5">
                <li class="page-item"><a class="page-link rounded-circle" href="/community?currPage=1">&laquo;</a></li>
                <li class="page-item"><a class="page-link rounded-circle" href="/community?currPage=${_COMMUNITYPAGE_.cri.currPage - 1}">&lt;</a></li>
                <li class="page-item"><a class="page-link rounded-circle bg-blue" href="/community?currPage=${_COMMUNITYPAGE__.cri.currPage}">${_COMMUNITYPAGE_.cri.currPage}</a></li>
                <li class="page-item"><a class="page-link rounded-circle" href="/community?currPage=${_COMMUNITYPAGE_.cri.currPage + 1}">&gt;</a></li>
                <li class="page-item"><a class="page-link rounded-circle" href="/community?currPage=${_COMMUNITYPAGE_.realEndPage}">&raquo;</a>
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