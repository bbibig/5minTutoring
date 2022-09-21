<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
 //   String name = (String)request.getAttribute("name");
 //   String email = (String)request.getAttribute("email");
 //  String phone = (String)request.getAttribute("phone");
 //  String address = (String)request.getAttribute("address");
  //  int totalPrice = (int)request.getAttribute("totalPrice");
     String name = (String)request.getParameter("name");
     String email = (String)request.getParameter("email");
     String phone = (String)request.getParameter("phone");
     String address = (String)request.getParameter("address");
     String stotalPrice = (String)request.getParameter("totalPrice");
     int totalPrice = Integer.parseInt(stotalPrice);
    
     System.out.println("name: "+name);
    System.out.println("email: "+email);
     System.out.println("phone: "+phone);
     System.out.println("address: "+address);
     System.out.println("stotalPrice: "+stotalPrice);
     System.out.println("totalPrice: "+totalPrice);
 
%>
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

            <script>
                var IMP = window.IMP; // 생략 가능
                IMP.init("imp88781134"); // 예: imp00000000

                function requestPay() {
                    let user_name = '${_UserDTO_.user_name}';
                    let h_name = '${_HandVO_.h_name}';

                    // IMP.request_pay(param, callback) 결제창 호출
                    IMP.request_pay({ // param
                        pg: "html5_inicis",     // PG사 선택
                        pay_method: "card",
                        merchant_uid: "33",   // 주문번호
                        name: h_name, // 상품명
                        amount: 6600,  // 결제금액
                        buyer_email: "test@gmail.com",
                        // buyer_name: "이윤정",
                        buyer_name: user_name,
                        buyer_tel: "010-3333-3333"
                    }, function (rsp) { // callback
                        console.log(rsp);
                        if (rsp.success) {
                        var msg = '결제가 완료되었습니다.';
                        alert(msg);
                        location.href = "/hand/buyhands"
                        } else {
                        var msg = '결제에 실패하였습니다.';
                        msg += '에러내용 : ' + rsp.error_msg;
                        alert(msg);
                        }
                    });
                }
            </script>
        </head>

        <body>
            <!-- ======= header : 로그인 후 ======= -->
			<jsp:include page="../header_login.jsp" flush="true" />
            <!-- ======= header ======= -->
            
            <!-- ======= Section ======= -->
            <div class="container">


            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/4_hand.js"></script>
            <!-- ============================================== -->

        </html>