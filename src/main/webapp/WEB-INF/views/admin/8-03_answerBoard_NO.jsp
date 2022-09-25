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
                    <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4 ">관리자</a>
                    <a href="/admin/answerBoard_NO" class="list-group-item list-group-item-action px-lg-4  fw-bold">문의 게시판</a>
                    <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
                    <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4">매출 관리</a>
                    <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
                </div>
            </div>

            <div id="contents" class="col-lg-9">

                <h3 class="fw-bold">문의 게시판</h3>


                <div class="manage-tap">
                  	<a href="/admin/answerBoard_NO" class="btn bg-blue">미답변</a>
                    <a href="/admin/answerBoard_OK" class="btn btn-dark">답변 완료</a>
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
                          <c:forEach var="board" items="${_RESULT_}">
                                <tr>
                                    <td>${board.iq_number}</td>
                                    <td class="inquiry-title">
                                    <c:set  var="iq_number" value="${board.iq_number}" />
                                        <a href="/admin/answerBoard/comment?iq_number=${board.iq_number}">${board.iq_title}</a>
                                    </td>

                                    <td>${board.user_email}</td>
                                    <td><fmt:formatDate pattern="yyyy.MM.dd" value="${board.iq_date}"/></td>
                                    <td>
	                                    <c:choose>
											<c:when test="${board.iq_pass eq 'N'}">
												<div>미답변</div>
											</c:when>
										</c:choose>
                                    </td>
                                    
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
                        <input type="hidden" name="pagesPerPage">

                        <ul class="pagination justify-content-center p-5">

                            <c:if test="${_ADMINPAGINATION_.prev}">
                                <li class="page-item"><a class="page-link rounded-circle" 
                                    href="/admin/answerBoard_NO?currPage=${_ADMINPAGINATION_.startPage-1}" id="prev">&laquo;</a></li>
                            </c:if>

                            <c:forEach var="page" begin="${_ADMINPAGINATION_.startPage}" end="${_ADMINPAGINATION_.endPage}">
                                <li class="page-item"><a class="page-link rounded-circle ${page==_ADMINPAGINATION_.cri.currPage?'page':''}" 
                                    href="/admin/answerBoard_NO?&currPage=${page}">${page}</a></li>
                                </c:forEach>

                            <c:if test="${_ADMINPAGINATION_.next}">
                                <li class="page-item"><a class="page-link rounded-circle" 
                                    href="/admin/answerBoard_NO?&currPage=${_ADMINPAGINATION_.endPage+1}">&raquo;</a></li>
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
        <jsp:include page="../footerAdmin.jsp" flush="true" />
        <script src="${path}/resources/js/8_admin.js"></script>
        <!-- ============================================== -->