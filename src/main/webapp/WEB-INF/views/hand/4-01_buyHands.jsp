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
            <jsp:include page="../header_login.jsp" flush="true" />
            <!-- ======= header ======= -->
            
             <!-- ======= Section ======= -->
            <div class="container">

                <!-- 손들기 구매하기 -->
                <section class="buy-hands">

                    <!-- 타이틀 -->
                    <h1>
                        손들기 <strong>구매하기</strong>
                    </h1>  


                    <!-- 상품 리스트 -->
                    <div>
                        <c:forEach var="hand" items="${_BuyHands_}">

                        <!-- 상품 -->
                        <a  href="/hand/payPage?h_number=${hand.h_number}">
                            <div class="buy-link">

                                <span>
                                    <img src="${path}/resources/img/buy_hands.png" alt="">
                                </span>
                        
                                <span class="price-txt">
                                    <p>
                                        ${hand.h_name}
                                    <i class="fas fa-arrow-right-long"></i>
                                    <strong>${hand.h_price}</strong>
                                    </p>
                                </span>
                            </div>
                        </a>

                        </c:forEach>

                    </div>

                </section>

            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <!-- ============================================== -->

        </html>