<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!doctype html>
        <html lang="ko">

        <head>
            <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
            <jsp:include page="../htmlHead.jsp" flush="true" />
            <link href="${path}/resources/css/1-04_signup.css" rel="stylesheet">
            <!-- ========================================================= -->

            <title>회원가입(학생)</title>

        </head>

        <body>
            <!-- ======= header : 로그인 전 ======= -->
            <jsp:include page="../header.jsp" flush="true" />
            <!-- =========== header=============== -->

            <!-- ======= Hero Section / sign up Student ======= -->

            <section id="hero" class="hero align-items-center">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4"> </div>
                        <div class="col-md-4">
                            <h1 class="display-4 text-center">Student Sign up</h1>
                        </div>
                        <div class="col-md-4 offset-md-4 mt-3">

                            <div class="login_form">

                                <form action="#" method="" enctype="text/plain" class="needs-validation" novalidate>

                                    <div class="form-group">
                                        <label for="email">이메일</label>
                                        <input type="email" class="form-control" id="email" data-kv="email"
                                            placeholder="example@email.com" required>
                                        <div class="invalid-feedback">이메일을 입력해주세요.</div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">비밀번호</label>
                                        <input type="password" class="form-control" id="password" value
                                            data-kv="password" spellcheck="false" placeholder="영문 숫자 포함8~15자로 입력하세요."
                                            required>
                                        <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                                    </div>

                                    <div class="form-group">
                                        <label for="passwordCheck">비밀번호 재확인</label>
                                        <input type="password" class="form-control" id="passwordCheck" value
                                            data-kv="passwordCheck" spellcheck="false" required>
                                        <div class="invalid-feedback">비밀번호와 맞지 않습니다.</div>
                                    </div>

                                    <div class="form-group">
                                        <label for="nickName">닉네임</label>
                                        <div class="nick">
                                            <input type="text" class="form-control" id="nickName"
                                                placeholder="영문 한글 숫자 2~10자" onclick="" required
                                                style="width:73%;float:left">
                                            <button type="button" class="btn btn-outline-primary mt-2"
                                                style="float: right" onclick="checkNickName()"> 중복체크</button>
                                            <div style="clear: both"></div>
                                            <div class="invalid-feedback" style="clear: both">올바른 닉네임이 아닙니다.</div>
                                        </div>
                                    </div>

                                    <div class="form-group mt-2">
                                        <label for="userName">이름</label>
                                        <input type="text" class="form-control" id="userName" required>
                                        <div class="invalid-feedback">이름을 입력해주세요.</div>
                                    </div>

                                    <div class="form-group">
                                        <label for="userbrith">생년월일</label>
                                        <div class="bir_yy">
                                            <span class="ps_box">
                                                <input type="text" class="form-control" id="yy" placeholder="년(4자)"
                                                    maxlength="4" required>
                                            </span>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="bir_mm">
                                            <span class="ps_box">
                                                <select class="form-select" id="mm" required>
                                                    <option> </option>
                                                    <option value="1">1월</option>
                                                    <option value="2">2월</option>
                                                    <option value="3">3월</option>
                                                    <option value="4">4월</option>
                                                    <option value="5">5월</option>
                                                    <option value="6">6월</option>
                                                    <option value="7">7월</option>
                                                    <option value="8">8월</option>
                                                    <option value="9">9월</option>
                                                    <option value="10">10월</option>
                                                    <option value="11">11월</option>
                                                    <option value="12">12월</option>
                                                </select>
                                            </span>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="bir_dd">
                                            <span class="ps_box">
                                                <input type="text" class="form-control" id="dd" placeholder="일"
                                                    maxlength="2" required>
                                            </span>
                                            <div class="invalid-feedback"></div>
                                        </div>

                                    </div>
                                    <!--생년월일-->

                                    <div class="form-group">
                                        <label for="gender">성별 </label>
                                        <select class="form-select" id="gender" required>
                                            <option selected> </option>
                                            <option value="남자"> 남자 </option>
                                            <option value="여자"> 여자 </option>
                                        </select>
                                        <div class="invalid-feedback"></div>
                                    </div>

                                    <div class="form-group">
                                        <label for="phone">휴대전화</label>
                                        <div>
                                            <input type="text" class="form-control" id="phone"
                                                style="width:75%;float:left">
                                            <button type="button" class="btn btn-outline-primary mt-2"
                                                style="float: right">
                                                인증하기</button>
                                            <div style="clear: both"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="school">
                                            <span class="ps_box">
                                                <label for="schoolGrade"> 학교 </label>
                                                <select class="form-select" id="school" required>
                                                    <option> </option>
                                                    <option value="중"> 중학교</option>
                                                    <option value="고">고등학교</option>
                                                </select>
                                            </span>
                                        </div>

                                        <div class="grade">
                                            <span class="ps_box">
                                                <label for="schoolGrade"> 학년</label>
                                                <select class="form-select" id="grade" required>
                                                    <option> </option>
                                                    <option value="1">1학년</option>
                                                    <option value="2">2학년</option>
                                                    <option value="3">3학년</option>
                                                </select>
                                            </span>
                                        </div>
                                    </div>
                                    <!--학교/학년-->
                                    <div class="d-grid gap-2 col-12 mx-auto">
                                        <br>
                                        <button class="btn btn-primary" id="loginBtn" type="submit"
                                            onclick="login()">회원가입하기</button>
                                    </div>
                                </form>

                                <!--button-->

                            </div>
                            <!--login_form-->
                        </div>

                    </div>
                    <!--row-->
            </section>
            <!-- End Hero -->
            
            <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
             <jsp:include page="../footer_before.jsp" flush="true" />
            <script src="${path}/resources/js/1-04_signUp.js"></script>
            <!-- ============================================== -->

        </body>

        </html>