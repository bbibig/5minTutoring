<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>

        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-02_login.css" rel="stylesheet">
            <link href="${path}/resources/css/4_buy_hands.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>오분과외</title>

        </head>

        <body>
            <!-- ======= header : 로그인 후 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- ======= header ======= -->
            
             <!-- ======= Section ======= -->
            <div class="container">

                <!-- 뒤로가기 -->
                <!-- <div class="page-back">
                <a href="#">
                    <img src="${path}/resources/img/back.png" alt="">
                </a>
                </div> -->

                <!-- 손들기 구매하기 -->
                <section class="buy-hands">

                <!-- 타이틀 -->
                <h1>
                    손들기 <strong>구매하기</strong>
                </h1>  


                <!-- 상품 리스트 -->
                <div>

                    <!-- 상품1 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">

                        <span>
                            <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                            <p>
                            손들기 15개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>3,300원</strong>
                            </p>
                        </span>
                    </div>
                    </a>
                    <!-- 상품2 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">
            
                        <span>
                        <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                        <p>
                            손들기 30개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>6,600원</strong>
                        </p>
                        </span>
                
                    </div>
                    </a>
                    
            
                    <!-- 상품3 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">
            
                        <span>
                        <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                        <p>
                            손들기 60개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>13,200원</strong>
                        </p>
                        </span>
                
                    </div>
                    </a>

            
                    <!-- 상품4 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">
            
                        <span>
                        <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                        <p>
                            손들기 90개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>19,800원</strong>
                        </p>
                        </span>
                
                    </div>
                    </a>

            
                    <!-- 상품5 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">
            
                        <span>
                        <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                        <p>
                            손들기 120개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>16,400원</strong>
                        </p>
                        </span>
                
                    </div>
                    </a>

            
                    <!-- 상품6 -->
                    <a  href="/hand/payPage">
                    <div class="buy-link">
            
                        <span>
                        <img src="${path}/resources/img/buy_hands.png" alt="">
                        </span>
                
                        <span class="price-txt">
                        <p>
                            손들기 150개
                            <i class="fas fa-arrow-right-long"></i>
                            <strong>33,800원</strong>
                        </p>
                        </span>
                
                    </div>
                    </a>


                </div>
                
            
                </section>

            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <!-- ============================================== -->

        </html>