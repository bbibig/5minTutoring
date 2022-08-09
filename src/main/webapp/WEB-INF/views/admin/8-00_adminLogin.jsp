<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>
        <html lang="ko">
        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/admin_header_footer.css" rel="stylesheet">
            <link href="${path}/resources/css/8_admin_page.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>ADMIN</title>

        </head>

        <body>

            <header id="header" class="header fixed-top">
                <div class="container-fluid container-xl d-flex align-items-center">
                    <a href="/admin" class="logo d-flex align-items-center img-fluid animated ">
                        <img src="${path}/resources/img/admin_logo.png" alt=""></a>

                    <nav id="navbar" class="navbar">
                        <a class="getstarted scrollto" style="margin-left: 1060px;" href="/admin">로그인</a>
                    </nav>
                </div>
            </header>
            <!-- ======= Admin Header ======= -->
            
            <!-- ======= Section ======= -->
            <div class="container">

                <section class="admin-login">
                    <form>

                        <div>
                            <img src="${path}/resources/img/5mtutoring_grey.png" alt="">
                        </div>

                        <div class="form-floating">
                            <input type="id" class="form-control" id="floatingInput">
                            <label for="floatingInput">id</label>
                        </div>

                        <div class="form-floating">
                            <input type="password" class="form-control" id="floatingPassword">
                            <label for="floatingPassword">Password</label>
                        </div>

                    </form>

                    <a class="loginBtn" >
                        <button type="submit" onclick="loginbt()"> 관리자 로그인</button>
                    </a>

                </section>
            </div>
            <!-- End Section -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/8_admin.js"></script>
            <!-- ============================================== -->

        </body>

        </html>