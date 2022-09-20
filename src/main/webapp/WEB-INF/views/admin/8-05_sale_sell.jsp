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

        <title>admin - 매출관리 </title>

    </head>

    <body>
        <!-- ======= Admin Header ======= -->
        <header id="header" class="header fixed-top">
            <div class="container-fluid container-xl d-flex align-items-center">
                <a href="/admin" class="logo d-flex align-items-center img-fluid animated ">
                    <img src="${path}/resources/img/admin_logo.png" alt=""></a>

                <div class="administrator">
                    <ul>
                        <li><a href="#" onclick="logout()">${_ADMIN_.ad_name}</a></li>
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
                    <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
                    <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4 fw-bold ">매출 관리</a>
                    <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
                </div>
            </div>

            <div id="contents" class="col-lg-9">

                <h3 class="fw-bold">매출 관리</h3>

                <!-- FROM -->

                <div class="ad-search">
                    <ul>
                        <!-- Tap -->
                        <li>
                            <div class="manage-tap">
                                <a href="/admin/sale/sell" class="btn bg-blue">판매 내역</a>
                                <a href="/admin/sale/withdrow" class="btn btn-dark">출금 내역</a>
                            </div>
                        </li>

                        <!-- sales management card -->
                        <li>
                            <div class="sales-info">
                                <ul>
                                    <li><p>매출액 : </p></li>
                                    <li><fmt:formatNumber value="${countSale}" pattern="###,###,### 원"/></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

                <!-- Content List -->
                <div class="container card p-4 bg-card">
                    <table class="table table-hover table-padding">
                        <thead>
                            <tr>
                                <th>회원 계정</th>
                                <th>상품</th>
                                <th>판매 금액</th>
                                <th>판매 날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="buy" items="${_BUYLIST_}">
                                <tr>
                                    <td>${buy.user_email}</td>
                                    <td>${buy.h_name}</td>
                                    <td>${buy.b_price}</td>
                                    <td><fmt:formatDate pattern="yyyy.MM.dd" value="${buy.b_date}" /></td>
                                </tr>
                            </c:forEach>
                            
                        </tbody>
                    </table>

                </div>
                <!--End main contents card(박스)-->

                <nav aria-label="Page navigation example">
                    <form action="#" id="adPaginationFrom">
                        <input type="hidden" name="currPage">
                        <input type="hidden" name="amount">

                        <ul class="pagination justify-content-center p-5">

                            <c:if test="${_ADMINPAGINATION_.prev}">
                                <li class="page-item"><a class="page-link rounded-circle" href="/admin/sale/sell?currPage=${_ADMINPAGINATION_.startPage-1}">&laquo;</a></li>
                            </c:if>
                        
                            <c:forEach var="page" begin="${_ADMINPAGINATION_.startPage}" end="${_ADMINPAGINATION_.endPage}">
                                <li class="page-item"><a class="page-link rounded-circle ${page==_ADMINPAGINATION_.cri.currPage?'page':''}" href="/admin/sale/sell?currPage=${page}">${page}</a></li>
                            </c:forEach>

                            <c:if test="${_ADMINPAGINATION_.next}">
                                <li class="page-item"><a class="page-link rounded-circle" href="/admin/sale/sell?currPage=${_ADMINPAGINATION_.endPage+1}">&raquo;</a></li>
                            </c:if>
                            
                        </ul>
                    </form>
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
    </body>

    </html>