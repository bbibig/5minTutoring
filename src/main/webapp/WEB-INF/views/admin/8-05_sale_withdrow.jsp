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

        <title>admin - 출금내역 </title>
        <style>
            #approval{
                height: 35px;
            }
        </style>
        <script>
            $(function(){

                $('#approval').on('change',function(){

                    let currPage = $('#adPaginationFrom').find('input[name=currPage]').val();
                    let amount = $('#adPaginationFrom').find('input[name=amount]').val();
                    let approval = $(this).val();

                    let data = { approval : approval }

                    console.log(data)

                    
                    $.ajax({
                        url : "/admin/sale/withdrow",
                        data : data, 
                        method : 'GET',
                        success : function(result){
                            self.location="/admin/sale/withdrow?approval="+approval;
                        }
                    })//ajax
                });//approval

            });
        </script>
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
                    <a href="/admin/answerBoard_NO" class="list-group-item list-group-item-action px-lg-4">문의 게시판</a>
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
                                <select name="approval" id="approval">
                                    <option value="">승인상태</option>
                                    <option value="대기">승인 대기</option>
                                    <option value="완료">승인 완료</option>
                                </select>
                            </div>
                        </li>

                        <!-- sales management card -->
                        <li>
                            <div class="withdrawal-info">
                                <ul>
                                    <li>출금내역 :</li>
                                    <li><fmt:formatNumber value="${totalDrawal}" pattern="###,###,### 원"/></li>
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
                                <th>신청 번호</th>
                                <th>튜터 계정</th>
                                <th>출금 금액</th>
                                <th>승인 상태</th>
                                <th>출금신청일자</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <c:forEach var="draw" items="${_DRAWLIST_}">
                                <tr>
                                    <td>${draw.w_num}</td>
                                    <td>${draw.user_email}</td>
                                    <td><fmt:formatNumber value="${draw.w_price}" pattern="###,###,### 원"/></td>
                                    <td>${draw.approval}</td>
                                    <td><fmt:formatDate pattern="yyyy.MM.dd" value="${draw.w_date}" /></td>
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
                                <li class="page-item"><a class="page-link rounded-circle" href="/admin/sale/withdrow?currPage=${_ADMINPAGINATION_.startPage-1}">&laquo;</a></li>
                            </c:if>
                        
                            <c:forEach var="page" begin="${_ADMINPAGINATION_.startPage}" end="${_ADMINPAGINATION_.endPage}">
                                <li class="page-item"><a class="page-link rounded-circle ${page==_ADMINPAGINATION_.cri.currPage?'page':''}" href="/admin/sale/withdrow?currPage=${page}">${page}</a></li>
                            </c:forEach>

                            <c:if test="${_ADMINPAGINATION_.next}">
                                <li class="page-item"><a class="page-link rounded-circle" href="/admin/sale/withdrow?currPage=${_ADMINPAGINATION_.endPage+1}">&raquo;</a></li>
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
    </body>

    </html>