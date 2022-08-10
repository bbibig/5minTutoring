<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>

        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-02_login.css" rel="stylesheet">
            <link href="${path}/resources/css/1-05-06-08_signup.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>오분과외</title>

        </head>

        <body>
            <!-- ======= header : 로그인 전 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- ======= header ======= -->

           <!-- ======= Section ======= -->
            <div class="container">

                <section class="sign-up-confirm">

                <div>
                    <img src="${path}/resources/img/check.png" alt="">
                </div>

                    <div class="confirm-content">

                    <p class="title">
                        <strong>5분과외 회원가입이</strong><br>
                        완료되었습니다.
                    </p>
                    <!-- 버튼 -->
                    <a href="/login">
                        <button class="moveTologinBtn" type=""  >로그인 페이지로 이동</button>
                    </a>

                    </div>

                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
             <jsp:include page="../footer_before.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <!-- ============================================== -->

        </html>