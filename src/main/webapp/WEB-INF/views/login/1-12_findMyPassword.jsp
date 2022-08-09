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
                    <strong>비밀번호 찾기</strong>
                    </p>
                    <p class="txt">
                    비밀번호를 잊으셨나요?<br>
                    <strong>가입한 계정을 정확히 입력해 주세요.</strong> <br>
                    이메일을 통해 비밀번호 수정 링크가 전송됩니다.
                    </p>

                    <div class="findMyEmailForm">
                    
                    <input type="email" class = "findMy-email" placeholder="이메일 계정 입력하기">

                    </div>


                    <a>
                    <button class="moveTologinBtn" type="submit"  onclick="findmypwbt()">인증하고 비밀번호 찾기</button>
                    </a>

                </div>

                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <!-- ============================================== -->

        </html>