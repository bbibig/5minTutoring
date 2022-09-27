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

            .number, .name, .phone, .bank, .hands, .price, .date {
                margin-top: 16px;
                font-size: 13pt;
            }
            
            lable{
                font-weight: bold;
                font-size: 15pt;
            }
            
            a:hover { 
            	
               color: #fff;
            }
        </style>

        
       
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

            <div id="contents" class="col-lg-7">
                <h3 class="fw-bold">매출 관리</h3>
                <!-- FROM -->
               
                <div class="container card p-4 bg-card">
					<div class="content7-9">

                        <form action="/admin/sale/withdrowOK" method="post">

                            <ul>
                        
                                <li>
                                    <div class="number">
                                        <label for="">출금 신청 번호: </label>
                                        <input type="text" name="w_num" value="${_WITHDRAWALINFO_.w_num}" style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="name">
                                        <label for="">이름: </label>
                                        <input type="text" name="user_name" value="${_WITHDRAWALINFO_.user_name}" disabled style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="phone">
                                        <label for="">휴대폰 번호: </label>
                                        <input type="text" name="user_phone"value="${_WITHDRAWALINFO_.user_phone}" disabled style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="bank">
                                        <label for="">계좌 번호: </label>
                                        <input type="text" name="bank_account" value="${_WITHDRAWALINFO_.bank_account}" disabled style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="hands">
                                        <label for="">손들기 수량: </label>
                                        <input type="text" name="w_quantity" value="${_WITHDRAWALINFO_.w_quantity}"  disabled style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="price">
                                        <label for="">출금 신청 금액: </label>
                                        <!-- <td><fmt:formatNumber value="${_WITHDRAWALINFO_.w_price}" pattern="###,###,### 원"/></td> -->
                                        <input type="text" name="w_price" value="<fmt:formatNumber value="${_WITHDRAWALINFO_.w_price}" pattern="###,###,### 원"/>" disabled style="border: none; background: transparent;">
                                    </div>
                                </li>

                                <li>
                                    <div class="date">
                                        <label for="">출금 신청일: </label>
                                        <!-- <input type="text" name="w_date" disabled> ${_WITHDRAWALINFO_.w_date} -->
                                        <!-- <fmt:formatDate pattern="yyyy.MM.dd" value="${_WITHDRAWALINFO_.w_date}"/> -->
                                        <input type="text" name="w_date" value=" <fmt:formatDate pattern="yyyy.MM.dd" value="${_WITHDRAWALINFO_.w_date}"/>" disabled style="border: none; background: transparent;">
                                    </div>
                                </li>
                                
                            </ul>   

                            <div style="margin-top: 30px; margin-left:40%;">
                                <a class="btn btn-outline-dark" href="/admin/sale/withdrow" role="button">취소</a>
                              	<a href="/admin/sale/withdrow"><button type="submit" class="btn btn-dark">승인</button></a>
                            </div>
                        
					    </form>
						
					</div>
				</div>
                
               

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