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

                <title>ADMIN-관리자</title>

            </head>

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

                <!-- ===== 메뉴리스트 첨부 ===== -->
                <jsp:include page="8-00_menuList.jsp" flush="true" />
                <!-- ========================== -->


                <div id="contents" class="col-lg-9">

                    <h3 class="fw-bold">관리자</h3>

                    <!-- Content List -->
                    <div class="container card p-4 bg-card">
                        <table class="table table-hover table-padding">
                            <thead>
                                <tr>
                                    <th>아이디</th>
                                    <th>이름</th>
                                    <th>부서</th>
                                    <th>이메일</th>
                                    <th>휴대폰번호</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>administratorid</td>
                                    <td>name</td>
                                    <td>departsments</td>
                                    <td>email@gmail.com</td>
                                    <td>010-0000-0000</td>
                                </tr>

                                <tr>
                                    <td>administratorid</td>
                                    <td>name</td>
                                    <td>departsments</td>
                                    <td>email@gmail.com</td>
                                    <td>010-0000-0000</td>
                                </tr>

                                <tr>
                                    <td>administratorid</td>
                                    <td>name</td>
                                    <td>departsments</td>
                                    <td>email@gmail.com</td>
                                    <td>010-0000-0000</td>
                                </tr>

                                <tr>
                                    <td>administratorid</td>
                                    <td>name</td>
                                    <td>departsments</td>
                                    <td>email@gmail.com</td>
                                    <td>010-0000-0000</td>
                                </tr>

                                <tr>
                                    <td>administratorid</td>
                                    <td>name</td>
                                    <td>departsments</td>
                                    <td>email@gmail.com</td>
                                    <td>010-0000-0000</td>
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



                </div><!-- End main contents -->

            </div><!-- End Section -->

            </section><!-- End main Section -->
            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/8_admin.js"></script>
            <!-- ============================================== -->