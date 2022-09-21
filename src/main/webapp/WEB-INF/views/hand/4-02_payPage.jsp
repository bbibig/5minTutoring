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

              <!-- jQuery -->
            <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>
            <!-- iamport.payment.js -->
            <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

        </head>

        <body>
            <!-- ======= header : 로그인 후 ======= -->
			<jsp:include page="../header_login.jsp" flush="true" />
            <!-- ======= header ======= -->
            
            <!-- ======= Section ======= -->
            <div class="container">



                <!-- 손들기 구매하기 -->
                <section class="pay-page">

                    <form name="orderform" id="orderform" method="post" class="orderform" action="/Order" onsubmit="return false;">
                
                        <input type="hidden" name="cmd" value="order">

                        <!-- buyer-info -->
                        <h3>구매자 정보</h3>

                        <table id="buyer-info" class="pay-table">

                            <thead class="table-head">
                            <tr>
                                <td class="buyer-info-title">이름</td>
                                <td class="buyer-info-txt">${__LOGIN_USER__.user_name}</td>
                                <td class="buyer-info-title">전화번호</td>
                                <td class="buyer-info-txt">${__LOGIN_USER__.user_phone}</td>
                            </tr>
                            </thead>

                        </table>


                        <!-- product-info -->
                        <h3>상품 정보</h3>

                        <table id="product-info" class="pay-table">

                            <thead class="table-head">
                                <tr>
                                    <th class="product-info-title">구매상품</th>
                                    <th class="product-info-title">상품금액</th>
                                    <th class="product-info-title">수량 / 합계</th>
                                    <!-- <th class="product-info-title">합계</th> -->
                                </tr>
                            </thead>
                            
                            <tbody class="table-body">
                                <tr>
                                    <td class="product-info-data">
                                    <img src="${path}/resources/img/buy_hands.png">
                                        ${_HandVO_.h_name}


                                        
                                    </td>

                                    <td class="product-info-data">   
                                        <!-- 상품금액 -->
                                        ${_HandVO_.h_price}원
                                    </td>

                                    <td class="product-info-data">
                                        <div class="basketprice">
                                            <input type="hidden" name="p_price" id="p_price" class="p_price" value="${_HandVO_.h_price}">
                                        </div>
                                        <!-- 수량 -->
                                        <span class="num">
                                            <span class="updown">
                                                <input type="text" name="p_num" id="p_num" size="2" maxlength="4" class="p_num" value="1" onkeyup="javascript:basket.changePNum(1);">

                                                <input class="up" type="button" value="+" name="up" onclick="javascript:basket.changePNum();">
                                                <input class="down" type="button" value="-" name="down" onclick="javascript:basket.changePNum();">
                                            </span>
                                        </span>

                                        <!-- 합계 -->
                                        <span id="sum_p_price" class="sum" value="${_HandVO_.h_price}">
                                            &nbsp;&nbsp;&nbsp;&nbsp; ${_HandVO_.h_price}원
                                        </span>
                                    </td>

                                    <!-- <p>
                                        ${_HandVO_}
                                    </p>
                                    <p>
                                        ${_UserDTO_}
                                    </p> -->
                                    <!-- <td class="product-info-data">
                                        <span id="sum_p_price" class="sum" value="javascript:basket.total();">
                                            ${'${_HandVO_.h_price}'}원
                                        </span>
                                    </td>    -->

                                        
                                </tr>
                            </tbody>

                        </table>


                        <!-- total-amount -->
                        <!-- <h3>총 금액</h3>
                        <table id="total-amount" class="pay-table">
                            <tbody class="table-body">
                            <tr>
                                <td class="total-amount-title">총 금액</td>
                                <td class="sum" id="total" onclick="javascript:basket.total();">${_HandVO_.h_price} 원</td>
                            </tr>
                            </tbody>
                        </table> -->

                        <button onclick="requestPay()" type="submit" class="payBtn">결제하기</button>

                    </form>
                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/4_hand.js"></script>
            <!-- ============================================== -->

        </html>