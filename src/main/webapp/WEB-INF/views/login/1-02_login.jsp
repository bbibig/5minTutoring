<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>

        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-02_login.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>오분과외</title>

        </head>

        <body>
            <!-- ======= header : 로그인 전 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- ======= header ======= -->

            <!-- ======= Section ======= -->
            <div class="container">

                <!-- 로그인 폼 -->
                <section class="form-signin">

                    <form>
                        <!-- 로고 -->
                        <div>
                            <img src="${path}/resources/img/5mtutoring_grey.png" alt="">
                        </div>

                        <!-- 이메일 -->
                        <div class="form-floating">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">Email address</label>
                        </div>

                        <!-- 비밀번호 -->
                        <div class="form-floating">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                    </form>

                    <!-- 로그인 버튼 -->
                    <a class="loginBtn" href="#">
                        <button type="submit" onclick="loginbt()">로그인</button>
                    </a>


                    <!-- 이메일/비번찾기 회원가입 -->
                    <div class="sign-in-more-action">
<<<<<<< HEAD
                        <a href="/login/findMyEmail">이메일 찾기</a>
                        <a href="/login/findMyPassword">비밀번호 찾기</a>
=======
                        <a href="/login/foundEmail">이메일 찾기</a>
                        <a href="/login/1-12_findMyPassword">비밀번호 찾기</a>
>>>>>>> 5187491 (login js)
                        <a href="/login/selectAccount">회원가입</a>
                    </div>

                    <!-- 구분선 -->
                    <hr>

                    <!-- 간편 로그인 -->
                    <div class="sns-l">

                        <p>간편로그인</p>

                        <!-- 네이버 로그인 -->
                        <span class="ico-sns-loin-naver">
                            <a href="#">
                                <img src="${path}/resources/img/naver_logo.png" alt="naver_logo">
                            </a>
                        </span>

                        <!-- 카카오 로그인 -->
                        <span class="ico-sns-loin-kakao">
                            <a href="#">
                                <img src="${path}/resources/img/kakao_logo.png" alt="kakao_logo">
                            </a>
                        </span>

                    </div>


                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <!-- ============================================== -->

        </html>