<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <%! String admin="이윤정" ; %>

            <!doctype html>

            <html lang="ko">

            <head>
                <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
                <jsp:include page="../htmlHead.jsp" flush="true" />
                <link href="${path}/resources/css/admin_header_footer.css" rel="stylesheet">
                <link href="${path}/resources/css/8_admin_page.css" rel="stylesheet">
                <!-- ========================================================= -->
                <title>admin FAQ</title>

            </head>

            <body>
                <!-- ======= Admin Header ======= -->
                <header id="header" class="header fixed-top">
                    <div class="container-fluid container-xl d-flex align-items-center">
                        <a href="/admin" class="logo d-flex align-items-center img-fluid animated ">
                            <img src="${path}/resources/img/admin_logo.png" alt=""></a>

                        <div class="administrator">
                            <ul>
                                <li>
                                    <a href="#" onclick="logout()">
                                        <%= admin %> 관리자
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </header>
                <!-- ======= Admin Header ======= -->

                <!-- ======= Section ======= -->
                <div class="container">

                <!-- ======= main Section ======= -->
				  <section id="main" class="container">
				    <div class="row">
				
				    <!-- ========== admin side ========== -->
				    <div id="nav" class="col-lg-3">
				
				        <div class="list-group">
				            <a href="/admin/student" class="list-group-item list-group-item-action px-lg-4 ">회원 관리</a>
				            <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4">관리자</a>
				            <a href="/admin/answerBoard_OK" class="list-group-item list-group-item-action px-lg-4">문의 게시판</a>
				            <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 fw-bold ">자주 묻는 질문</a>
				            <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4">매출 관리</a>
				             <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
				        </div>
				    </div>

                    <div id="contents" class="col-lg-8">
                        <div class="faq_box">
                            <h3 class="fw-bold" id="aside_title">자주 묻는 질문</h3>

                            <div class="faq_write">
                                <button id="write-button" type="submit" class="btn btn-dark btn-sm col-1"
                                    data-bs-toggle="modal" data-bs-target="#community-write">글쓰기
                                </button>
                            </div>
                        </div>


                        <div class="row">
                            <!-- Content List -->
                            <div class="container card p-4">
                                <!-- FAQ 리스트 -->
                                <div class="accordion accordion-flush" id="accordionFlushExample">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-headingOne">
                                            <button class="accordion-button collapsed" type="button"
                                                style="background-color: white;" data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapseOne" aria-expanded="false"
                                                aria-controls="flush-collapseOne">
                                                Q. 질문입니다.
                                            </button>
                                        </h2>
                                        <div id="flush-collapseOne" class="accordion-collapse collapse"
                                            aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body row">
                                                <!-- ▼ 답변 영역  -->
                                                <div class="col-11">
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                        rows="3">A. 자주묻는 질문질문</textarea>
                                                </div>
                                                <!-- ▼수정삭제 버튼 -->
                                                <div class="col-1 sidebutton" style="text-align: center;">
                                                    <div>
                                                        <button type="submit">수정</button> | 
                                                        <button class="deleteBtn" type="submit" onclick="deletebt()">삭제</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- 아코디언1 -->

                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-headingTwo">
                                            <button class="accordion-button collapsed" type="button"
                                                style="background-color: white;" data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapseTwo" aria-expanded="false"
                                                aria-controls="flush-collapseTwo">
                                                Q. 질문입니다.
                                            </button>
                                        </h2>
                                        <div id="flush-collapseTwo" class="accordion-collapse collapse"
                                            aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body row">
                                                <!-- ▼ 답변 영역  -->
                                                <div class="col-11">
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                        rows="3">A. 자주묻는 질문질문</textarea>
                                                </div>
                                                <!-- ▼수정삭제 버튼 -->
                                                <div class="col-1 sidebutton" style="text-align: center;">
                                                    <div>
                                                        <button type="submit">수정</button> | 
                                                        <button class="deleteBtn" type="submit" onclick="deletebt()">삭제</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- 아코디언2 -->

                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-headingThree">
                                            <button class="accordion-button collapsed" type="button"
                                                style="background-color: white;" data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapseThree" aria-expanded="false"
                                                aria-controls="flush-collapseThree">
                                                Q. 질문입니다.
                                            </button>
                                        </h2>
                                        <div id="flush-collapseThree" class="accordion-collapse collapse"
                                            aria-labelledby="flush-headingThree"
                                            data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body row">
                                                <!-- ▼ 답변 영역  -->
                                                <div class="col-11">
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                        rows="3">A. 자주묻는 질문질문</textarea>
                                                </div>
                                                <!-- ▼수정삭제 버튼 -->
                                                <div class="col-1 sidebutton" style="text-align: center;">
                                                    <div>
                                                        <button type="submit">수정</button> | 
                                                        <button class="deleteBtn" type="submit" onclick="deletebt()">삭제</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- 아코디언3 -->

                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-headingFour">
                                            <button class="accordion-button collapsed" type="button"
                                                style="background-color: white;" data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapseFour" aria-expanded="false"
                                                aria-controls="flush-collapseFour">
                                                Q. 질문입니다.
                                            </button>
                                        </h2>
                                        <div id="flush-collapseFour" class="accordion-collapse collapse"
                                            aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body row">
                                                <!-- ▼ 답변 영역  -->
                                                <div class="col-11">
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                        rows="3">A. 자주묻는 질문질문</textarea>
                                                </div>
                                                <!-- ▼수정삭제 버튼 -->
                                                <div class="col-1 sidebutton" style="text-align: center;">
                                                    <div>
                                                        <button type="submit">수정</button> | 
                                                        <button class="deleteBtn" type="submit" onclick="deletebt()">삭제</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- 아코디언4 -->

                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-headingFive">
                                            <button class="accordion-button collapsed" type="button"
                                                style="background-color: white;" data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapseFive" aria-expanded="false"
                                                aria-controls="flush-collapseFive">
                                                Q. 질문입니다.
                                            </button>
                                        </h2>
                                        <div id="flush-collapseFive" class="accordion-collapse collapse"
                                            aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body row">
                                                <!-- ▼ 답변 영역  -->
                                                <div class="col-11">
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                        rows="3">A. 자주묻는 질문질문</textarea>
                                                </div>
                                                <!-- ▼수정삭제 버튼 -->
                                                <div class="col-1 sidebutton" style="text-align: center;">
                                                    <div>
                                                        <button type="submit">수정</button> | 
                                                        <button class="deleteBtn" type="submit" onclick="deletebt()">삭제</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- 아코디언5 -->

                                </div> <!-- FAQ리스트 -->

                            </div>
                            <!--End main contents card(박스)-->
                        </div>

                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center p-5">
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a>
                                </li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a>
                                </li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a>
                                </li>
                            </ul>
                        </nav>

                        <!-- TO -->

                    </div><!-- End main contents -->

                </div><!-- End Section -->

                </section><!-- End main Section -->
                </div>
                <!-- End Section -->

                <!--====================================글쓰기 모달=================================================-->
<<<<<<< HEAD
                
=======
              
>>>>>>> cabaaba (package rename)
                <div class="modal fade" id="community-write">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"># 글쓰기</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>

                            <div class="modal-body">
                                <form>
                                    <div class="mb-3">
                                        <label for="recipient-name" class="col-form-label">제목</label>
                                        <input type="text" class="form-control" id="askTitle" placeholder="제목을 입력해주세요.">
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
                <!--====================================글쓰기 모달=================================================-->






                <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
                <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/translations/ko.js"></script>
                <script>
                    ClassicEditor.create(document.querySelector('#askContent'), {
                        language: "ko"
                    });
                </script>

                <!-- ============= 공통 footer + js =============== -->
                <jsp:include page="../footer.jsp" flush="true" />
                <script src="${path}/resources/js/8_admin.js"></script>
                <script src="${path}/resources/js/yunjeong.js"></script>
                <!-- ============================================== -->

            </body>

            </html>