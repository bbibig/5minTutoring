<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>

        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-02_login.css" rel="stylesheet">
            <link href="${path}/resources/css/1-03_signin_stu_or_tutor.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>오분과외</title>

        </head>

        <body>
            <!-- ======= header : 로그인 전 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- ======= header ======= -->

            <!-- ======= Section ======= -->
            <div class="container">

                <section class="signin-stu-or-tutor">
                
                <!-- 타이틀 -->
                <h1>
                    오분과외<br>
                    <strong>회원가입을 환영합니다!</strong>
                </h1>

                <div>

                    <!-- 학생가입 -->
                    <span class="spanbox">
                    
                    <div class="signin-stu">
                        <!-- 학생 이미지 -->
                        <div>
                        <img src="${path}/resources/img/student.png" alt="">
                        </div>
                
                        <p class="signin-txt">
                        독학이거나, <br>
                        <strong>질문이 많은 학생이라면</strong>
                        </p>
                
                        <!-- 버튼 -->
                        <a href="/login/signUp_student">
                        <button class="moveTosigninBtn" type=""  ><strong>학생</strong> 회원가입</button>
                        </a>
                    </div>
                
                    </span>
                    
                    <!-- 튜터가입 -->
                    <span class="spanbox">
                    <div class="signin-tutor">
                        <!-- 튜터이미지 -->
                        <div>
                        <img src="${path}/resources/img/tutor.png" alt="">
                        </div>
                
                        <p class="signin-txt">
                        지식을 나누거나, <br>
                        <strong>좋은 멘토가 되고싶은 튜터라면</strong>
                        </p>
                
                        <!-- 버튼 -->
                        <a href="/login/signUp_tutor">
                        <button class="moveTosigninBtn" type=""  ><strong>튜터</strong> 회원가입</button>
                        </a>
                
                    </div>
                    </span>

                </div>
            
                </section>
            </div>
  <!-- End Section -->

            <!-- ============= 로그인 전 풋터  =============== -->
             <jsp:include page="../footer_before.jsp" flush="true" />
            <!-- ============================================== -->

        </html>