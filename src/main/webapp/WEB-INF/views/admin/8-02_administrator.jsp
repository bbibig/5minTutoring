<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:set var="path" value="${pageContext.request.contextPath}" />
    <!doctype html>
    <html lang="ko">

    <head>
        <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
        <jsp:include page="../htmlHead.jsp" flush="true" />
        <link href="${path}/resources/css/admin_header_footer.css" rel="stylesheet">
        <link href="${path}/resources/css/8_admin_page.css" rel="stylesheet">
        <!-- ========================================================= -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

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
                        <li><a href="#" onclick="logout()">${_ADMIN_.ad_name}???</a></li>
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
                    <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4 fw-bold ">관리자</a>
                    <a href="/admin/answerBoard_OK" class="list-group-item list-group-item-action px-lg-4">문의 게시판</a>
                    <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
                    <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4">매출 관리</a>
                        <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
                </div>
            </div>


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
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="ADMIN" items="${_USERLIST_}">
                            <tr>
                                <td>${ADMIN.ad_id}</td>
                                <td>${ADMIN.ad_name}</td>
                                <td>${ADMIN.ad_dep_name}</td>
                                <td>${ADMIN.ad_email}</td>
                            </tr>
                       </c:forEach>
                    </tbody>
                </table>

            </div>
            <!--End main contents card(박스)-->

            <nav aria-label="Page navigation example">
                <form action="#">
                    <ul class="pagination justify-content-center p-5">
                        <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a></li>
                        <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
                        <c:forEach var="page" begin="${_ADMINPAGINATION_.startPage}" end="${_ADMINPAGINATION_.endPage}">
                            <li class="page-item"><a class="page-link rounded-circle bg-blue" href="/admin/stator?currPage=${page}">${page}</a></li>
                        </c:forEach>
                        <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
                        <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a></li>
                    </ul>
                </form>
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