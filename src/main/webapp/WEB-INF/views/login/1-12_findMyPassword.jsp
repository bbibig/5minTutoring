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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js?ver=1"></script>
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js?ver=1"></script>

            <title>오분과외</title>
            <script>
                $(function () {
                    $('#findPwBtn').on('click', function () {
                        let findKey = $("#user_email");

                        if (findKey.val() == "") {
                            Swal.fire({
                                icon: 'error',
                                title: 'Email을 입력하세요',
                            })
                            return;
                        }//이메일 미입력시 

                        console.log(findKey.val());

                        $.ajax({
                            url: "/login/findMyPassword",
                            type: "POST",
                            data: { "user_email": findKey.val() },
                            success: function (result) {
                                Swal.fire(result)
                            }//success

                        });//ajax
                    });//비밀번호찾기
                })
            </script>

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
                            <!-- 이메일을 통해 비밀번호 수정 링크가 전송됩니다. -->
                        </p>

                        <form action="#">
                            <div class="findMyEmailForm">
                                <input type="email" class="findMy-email" placeholder="이메일 계정 입력하기" id="user_email">
                            </div>

                            <button class="moveTologinBtn" type="button" id="findPwBtn">비밀번호 찾기</button>
                        </form>

                    </div>

                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
            <jsp:include page="../footer_before.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <!-- ============================================== -->

        </html>