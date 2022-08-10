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
                <title>admin - 문의게시판( 답변중 )</title>

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
				            <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4 ">관리자</a>
				            <a href="/admin/answerBoard_OK" class="list-group-item list-group-item-action px-lg-4 fw-bold ">문의 게시판</a>
				            <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
				            <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4">매출 관리</a>
				             <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
				        </div>
				    </div>
                    <div id="contents" class="col-lg-9">

                        <h3 class="fw-bold">문의 게시판</h3>

                        <!-- Content List -->
                        <!-- <div class="container card p-4" bg-card> -->

                        <div class="container question">
                            <!--문의하기 전체박스-->

                            <div class="titlebox row">
                                <!--제목상자-->
                                <div class="col-1 fs-2 fw-bold">Q.</div>
                                <div class="col-6 fs-4 fw-bold">문의합니다.</div>
                                <div class="col-3">dkdkd@naver.com</div>
                                <div class="col-2">2022-07-21</div>
                            </div>
                            <div class="contentbox">
                                <!-- 문의 내용 글상자(회색) -->

                                <div class="content fs-6">
                                    <!-- 문의 내용 -->
                                    어떤 점을 문의해야 답변이 되는건지 문의합니다.
                                </div>
                            </div>
                        </div>

                        
                        <div class="container answer">
                            <!--답변하기 전체박스-->
            
                            <div class="titlebox row">
                            <!--제목상자-->
                            <div class="col-1 fs-2 fw-bold">A.</div>
                            <div class="col-9 fs-4 fw-bold">답변입니다.</div>
                            <div class="col-2">2022-07-21</div>
                            </div>
            
                            <div class="col-lg-12 answer">
                
                            <form action="#">
                                <!-- <textarea name="text" id="answerWrite"></textarea> -->
                                <div class="text-box">                              
                                    <textarea class="form-control" placeholder="" id="floatingTextarea1" style="height: 150px" required></textarea>
                                </div>
                            </form>
            
                            </div>
            
                        <!-- CKEditor5 CDN 연결 및 언어 설정 -->
                        <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
                        <script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/translations/ko.js"></script>
                        <script>
                            ClassicEditor.create(document.querySelector('#answerWrite'), {
                                language: "ko"
                            });
                        </script>
                    </div>
            
                        <!-- 목록으로 버튼 -->
                        <div class="buttonlist d-flex flex-row justify-content-end">
                            <ul>
                            <li>
                                <a href="#">
                                <button class="btn-inquiry" type="" >목록으로</button>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                <button class="btn-inquiry" type="submit" >답변등록</button>
                                </a>
                            </li>
                            </ul>
                        </div>
            
                            

                        <!--End main contents card(박스)-->
                    </div>

                </div><!-- End main contents -->

                </div>

                </section><!-- End main Section -->

                </div>
                <!-- End Section -->

                <!-- ============= 공통 footer + js =============== -->
                <jsp:include page="../footer.jsp" flush="true" />
                <script src="${path}/resources/js/8_admin.js"></script>
                <!-- ============================================== -->