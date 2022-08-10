<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>

        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-02_login.css" rel="stylesheet">
            <link href="${path}/resources/css/1-09-12_findMy.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>오분과외</title>

        </head>

        <body>
            <!-- ======= header : 로그인 전 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- ======= header ======= -->
            
             <!-- ======= Section ======= -->
            <div class="container">

                <section class="findMy">

                <div>
                    <img src="${path}/resources/img/exclamation.png" alt="">
                </div>

                <div class="findMy-content">

                    <p class="title">
                    <strong>이메일 찾기</strong>
                    </p>
                    <p class="txt">
                    이전에 가입한 계정을 잊으셨나요?<br>
                    <strong>휴대 전화 번호 인증</strong>으로 가입한 계정을 찾을 수 있습니다.
                    </p>

                    <a href="#">
                    <button class="moveTologinBtn" type="">인증하고 계정찾기</button>
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