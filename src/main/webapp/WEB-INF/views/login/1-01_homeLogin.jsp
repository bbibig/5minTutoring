<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>
        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <!-- ========================================================= -->

            <title>메인(로그인후)</title>

        </head>

        <body>
            <!-- ============= 공통 Header : 로그인 후 =================== -->
            <jsp:include page="../header_login.jsp" flush="true" />
            <!-- ========================================================= -->

            <!-- ======= Hero Section ======= -->
            <section id="hero" class="hero d-flex align-items-center">

                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 d-flex flex-column justify-content-center">
                            <h1 data-aos="fade-up">오분과외를 소개합니다.</h1>
                            <h2 data-aos="fade-up" data-aos-delay="400">오분과외에 대한 모든 것</h2><br>
                            <div data-aos="fade-up" data-aos-delay="600">
                                <div class="text-center text-lg-start">
                                    <a href="/login/"
                                        class="btn-get-started scrollto d-inline-flex align-items-center justify-content-center align-self-center">
                                        <span>시작하기</span>
                                        <i class="bi bi-arrow-right"></i>
                                    </a>

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 hero-img" data-aos="zoom-out" data-aos-delay="200">
                            <img src="${path}/resources/img/teaching.png" class="img-fluid animated" alt="" width="500px"
                                height="500px">
                        </div>
                    </div>
                </div>

            </section><!-- End Hero -->

            <!-- ============= 공통 footer + js =============== -->
            <jsp:include page="../footer.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <!-- ============================================== -->
        </body>

        </html>