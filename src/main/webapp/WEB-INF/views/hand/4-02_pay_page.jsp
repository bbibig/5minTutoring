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
                <div class="page-back">
                <a href="#">
                    <img src="assets/img/back.png" alt="">
                </a>
                </div>

                <!-- 손들기 구매하기 -->
                <section class="pay-page">

                <!-- buyer-info -->
                <h3>구매자 정보</h3>

                <table id="buyer-info" class="pay-table">

                    <thead class="table-head">
                    <tr>
                        <td class="buyer-info-title">이름</td>
                        <td class="buyer-info-txt">이윤정</td>
                        <td class="buyer-info-title">전화번호</td>
                        <td class="buyer-info-txt">010-0000-0000</td>
                    </tr>
                    </thead>

                </table>


                <!-- product-info -->
                <h3>상품 정보</h3>

                <table id="product-info" class="pay-table">

                    <thead class="table-head">
                        <tr>
                            <th class="product-info-title">구매상품</th>
                            <th class="product-info-title">수량</th>
                            <th class="product-info-title">상품금액</th>
                        </tr>
                    </thead>

                    <tbody class="table-body">
                        <tr>
                            <td class="product-info-data">
                            <img src="assets/img/buy_hands.png">손들기 30개
                            </td>
                                                
                            <td class="product-info-data">
                            수량  <input type="hidden" name="sell_price" value="3300">
                            <input type="text" name="amount" value="1" size="3" max="10">
                            <input type="button" value="+" name="add">
                            <input type="button" value="-" name="minus">
                            </td>

                            <td class="product-info-data">
                            상품금액  <input type="text" name="sum" size="11" readonly>원
                            </td>
                        </tr>
                    </tbody>

                </table>


                <!-- total-amount -->
                <h3>총 금액</h3>
                <table id="total-amount" class="pay-table">
                    <tbody class="table-body">
                    <tr>
                        <td class="total-amount-title">총 금액</td>
                        <td class="total-amount-txt">6600원</td>
                    </tr>
                    </tbody>
                </table>

                </section>

            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <!-- ============================================== -->

        </html>