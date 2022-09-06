<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
    <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
    <jsp:include page="../htmlHead.jsp" flush="true" />
    <!-- =======mypage 공통 CSS=================================== -->
    <link href="${path}/resources/css/mypage.css" rel="stylesheet">

    <title>기본 정보(학생)</title>
</head>

<body>
    <!-- ============= 공통 Header : 로그인 후 =================== -->
    <jsp:include page="../header_login.jsp" flush="true" />
    <!-- ========================================================= -->

    <!-- ======= Hero Section ======= -->

    <!-- ======= main Section ======= -->
    <section id="main" class="container">

        <div class="row">

            <div id="nav" class="col-lg-3">
                <ul class="list-group mb-2 bg-dark text-white">
                    <li class="list-group-item bg-blue fw-bold fs-5 px-lg-4">마이페이지</li>
                </ul>
                <div class="list-group">
                    <a href="/mypage/studentPage" class="list-group-item list-group-item-action px-lg-4 fw-bold">기본
                        정보</a>
                    <a href="/mypage/studentHands/use" class="list-group-item list-group-item-action px-lg-4">손들기
                        내역</a>
                    <a href="/mypage/myQuestion" class="list-group-item list-group-item-action px-lg-4">나의
                        질문글</a>
                    <a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
                        커뮤니티</a>
                    <a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4">1:1
                        문의하기</a>
                    <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4">회원
                        탈퇴</a>
                </div>
            </div><!-- End main nav -->


            <div id="contents" class="col-lg-9">

                <h3 class="fw-bold">기본정보</h3>

                <!-- FROM -->
                <div class="container card p-4 bg-card">
                    <form action="/mypage/studentPageModify" method="post" id="userInfo">
                        <div class="row">

                            <div class="col-4">
                                <input type="file" name="file_name" id="file_name" class="d-none file_name"
                                    accept="image/*">
                                <div id="profileImg" class="ratio ratio-1x1 rounded-circle profileImg">
                                    <img src="${path}/resources/img/profile.png" alt="">
                                </div>
                            </div>

                            <div class="col-4">
                                <input type="hidden" name="user_email" value="${_USERINFO_.user_email}">
                                <label for="user_nick">닉네임</label>
                                <input type="text" class="form-control mb-3" id="user_nick" name="user_nick" readonly
                                    value="${_USERINFO_.user_nick}">
                                <label for="user_name">이름</label>
                                <input type="text" class="form-control mb-3" id="user_name" name="user_name" readonly
                                    value="${_USERINFO_.user_name}">
                                <label for="user_gender">성별</label>
                                <input type="text" class="form-control mb-3" id="user_gender" name="user_gender"
                                    readonly value="${_USERINFO_.user_gender}">
                                <label for="user_phone">휴대전화</label>
                                <input type="tel" class="form-control mb-3" id="user_phone" name="user_phone" readonly
                                    value="${_USERINFO_.user_phone}">
                                <div class="row">
                                    <span class="col">
                                        <label for="st_school">중/고등학생</label>
                                        <select class="form-select mb-3" id="st_school" name="st_school">
                                            <option value="중학생" <c:if test="${_USERINFO_.st_school eq '중학생'}">selected
                                                </c:if> > 중학생</option>
                                            <option value="고등학생" <c:if test="${_USERINFO_.st_school eq '고등학생'}">selected
                                                </c:if>> 고등학생</option>
                                        </select>
                                    </span>
                                    <span class="col">
                                        <label for="st_grade">학년</label>
                                        <select class="form-select mb-3" id="st_grade" name="st_grade">
                                            <option value="1학년" <c:if test="${_USERINFO_.st_grade eq '1학년'}">selected
                                                </c:if> > 1학년</option>
                                            <option value="2학년" <c:if test="${_USERINFO_.st_grade eq '2학년'}">selected
                                                </c:if> > 2학년</option>
                                            <option value="3학년" <c:if test="${_USERINFO_.st_grade eq '3학년'}">selected
                                                </c:if> > 3학년</option>
                                        </select>
                                    </span>
                                </div>
                                <!--select 태그-->

                            </div>

                            <div class="col-4" style="position: relative;">
                                <label for="user_pw">현재 비밀번호</label>
                                <input type="password" class="form-control" id="user_pw" name="user_pw" required
                                    placeholder="비밀번호를 입력해 주세요">
                                <p id=check_1 class="text-danger invisible">비밀번호가 일치하지 않습니다.</p>

                                <label for="user_newPw">비밀번호 변경</label>
                                <input type="password" class="form-control mb-3" id="user_newPw" name="user_newPw"
                                    required placeholder="새로운 비밀번호를 입력해 주세요">
                                <label for="user_newPw2">비밀번호 확인</label>
                                <input type="password" class="form-control" id="user_newPw2" name="user_newPw2"
                                    required>
                                <p id=check_2 class="text-danger invisible">비밀번호가 일치하지 않습니다.</p>

                                <button type="button" class="btn bg-blue"
                                    style="position: absolute; right: 20px; bottom: 10px;" onclick="pwCheck()">
                                    저장</button>
                            </div>
                        </div>
                    </form>

                </div>
                <!--End main contents card(박스)-->

                <!-- TO -->

            </div><!-- End main contents -->

        </div> <!-- End Section -->

    </section><!-- End main Section -->



    <!-- ======= footer + js  ======= -->
    <jsp:include page="../footer.jsp" flush="true" />
    <!-- ========유효성 체크========= -->
    <script src="${path}/resources/js/7-01_mypage.js"></script>
</body>

</html>