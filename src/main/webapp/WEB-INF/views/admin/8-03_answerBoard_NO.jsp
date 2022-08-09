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
                <title>admin - 문의게시판(미답변)</title>

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


                        <div class="manage-tap">
                            <a href="/admin/answerBoard_OK" class="btn bg-blue">답변 완료</a>
                            <a href="/admin/answerBoard_NO" class="btn btn-dark">미 답변</a>
                        </div>


                        <!-- Content List -->
                        <div class="container card p-4 bg-card">
                            <table class="table table-hover table-padding">
                                <thead>
                                    <tr>
                                        <th>문의 번호</th>
                                        <th>계정</th>
                                        <th>작성자</th>
                                        <th>접수일</th>
                                        <th>처리상태</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>220610-0000</td>

                                        <td class="inquiry-title">
                                            <a href="/admin/answerBoard/comment">문의글 제목</a>
                                        </td>

                                        <td>abcde@gmail.com</td>
                                        <td>2022-08-01</td>
                                        <td>미 답변</td>
                                    </tr>

                                    <tr>
                                        <td>220610-0001</td>

                                        <td class="inquiry-title">
                                            <a href="/admin/answerBoard/comment">문의글 제목</a>
                                        </td>

                                        <td>abcde@gmail.com</td>
                                        <td>2022-08-01</td>
                                        <td>미 답변</td>
                                    </tr>

                                    <tr>
                                        <td>220610-0002</td>

                                        <td class="inquiry-title">
                                            <a href="/admin/answerBoard/comment">문의글 제목</a>
                                        </td>

                                        <td>abcde@gmail.com</td>
                                        <td>2022-08-01</td>
                                        <td>미 답변</td>
                                    </tr>

                                    <tr>
                                        <td>220610-0003</td>

                                        <td class="inquiry-title">
                                            <a href="/admin/answerBoard/comment">문의글 제목</a>
                                        </td>

                                        <td>abcde@gmail.com</td>
                                        <td>2022-08-01</td>
                                        <td>미 답변</td>
                                    </tr>

                                    <tr>
                                        <td>220610-0004</td>

                                        <td class="inquiry-title">
                                            <a href="/admin/answerBoard/comment">문의글 제목</a>
                                        </td>

                                        <td>abcde@gmail.com</td>
                                        <td>2022-08-01</td>
                                        <td>미 답변</td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>
                        <!--End main contents card(박스)-->

                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center p-5">
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a></li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a></li>
                            </ul>
                        </nav>

                        <!-- TO -->

                    </div><!-- End main contents -->

                </div><!-- End Section -->

                </section><!-- End main Section -->
                </div>
                <!-- End Section -->

                <!-- ============= 공통 footer + js =============== -->
                <jsp:include page="../footer.jsp" flush="true" />
                <script src="${path}/resources/js/8_admin.js"></script>
                <!-- ============================================== -->