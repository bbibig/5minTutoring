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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

        <title>회원가입(튜터)</title>
        <script>
            $(function(){
                var code2 = "";
                $("#phoneChk").click(function(){
                    alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인 해주세요.");
                    var phone = $("#user_phone").val();
                    console.log(phone);
                    $.ajax({
                        type:"GET",
                        url:"/login/phoneCheck?user_phone=" + phone,
                        cache : false,
                        success:function(data){
                            if(data == "error"){
                                alert("휴대폰 번호가 올바르지 않습니다.")
                                $(".successPhoneChk").text("유효한 번호를 입력해주세요.");
                                $(".successPhoneChk").css("color","red");
                                $("#user_phone").attr("autofocus",true);
                            }else{	        		
                                $("#phone").attr("disabled",false);
                                $("#phoneChk2").css("display","inline-block");
                                $(".successPhoneChk").text("휴대폰 번호 입력후 인증번호를 입력해주세요.");
                                $(".successPhoneChk").css("color","green");
                                $("#user_phone").attr("readonly",true);
                                code2 = data;
                            }
                        }//success
                    });//ajax
                });//인증번호발송

                $("#phoneChk2").click(function(){
                    if($("#phone").val() == code2){
                        $(".successPhoneChk").text("인증번호가 일치합니다.");
                        $(".successPhoneChk").css("color","green");
                        $("#phoneDoubleChk").val("true");
                        $("#phone").attr("disabled",true);
                    }else{
                        $(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
                        $(".successPhoneChk").css("color","red");
                        $("#phoneDoubleChk").val("false");
                        $(this).attr("autofocus",true);
                    }
                });//인증번호체크하기

            });//jq
        </script>
    </head>

    <body>
        <!-- ======= header : 로그인 전 ======= -->
        <jsp:include page="../header.jsp" flush="true" />
        <!-- =========== header=============== -->

        <!-- ======= Hero Section / sign up tutor ======= -->

        <section id="hero" class="hero align-items-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-4"> </div>
                    <div class="col-md-4">
                        <h1 class="display-4 text-center"> Tutor Sign up</h1>
                    </div>
                    <div class="col-md-4 offset-md-4 mt-3">

                        <div class="login_form">
                            <form action="/login/signUp_tutor" method="post" class="needs-validation" enctype="multipart/form-data" novalidate>

                                <div class="form-group">
                                    <label for="email">이메일</label>
                                    <input type="email" class="form-control" name="user_email" id="user_email" 
                                        placeholder="example@email.com" required>
                                    <div class="invalid-feedback">이메일을 입력해주세요.</div>
                                </div>

                                <div class="form-group">
                                    <label for="password">비밀번호</label>
                                    <input type="password" class="form-control mt-1" name="user_pw" id="user_pw" placeholder="영문 숫자 포함8~15자로 입력하세요." required>
                                    <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                                </div>

                                <div class="form-group">
                                    <label for="passwordCheck">비밀번호 재확인</label>
                                    <input type="password" class="form-control" id="pwcheck" required>
                                    <div class="invalid-feedback">비밀번호와 맞지 않습니다.</div>
                                </div>

                                <div class="form-group">
                                    <label for="nickName">닉네임</label>
                                    <div class="nick">
                                        <input type="text" class="form-control" name="user_nick" id="user_nick" placeholder="영문 한글 숫자 2~10자"
                                            style="width:73%;float:left">
                                        <button type="button" class="btn btn-outline-primary mt-2" style="float: right" id="nickCheck"> 중복체크</button>
                                        <div style="clear: both"></div>
                                        <div class="invalid-feedback" style="clear: both">올바른 닉네임이 아닙니다.</div>
                                        <div class="ni_input_re_1">사용 가능한 닉네임 입니다.</div>
                                        <div class="ni_input_re_2">중복된 닉네임 입니다.</div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="userName">이름</label>
                                    <input type="text" class="form-control" name="user_name" id="user_name" required>
                                    <div class="invalid-feedback">이름을 입력해주세요.</div>
                                </div>

                                <div class="form-group">
                                    <label for="userbrith">생년월일</label>
                                    <div class="birth">
                                        <span class="birth_box">
                                            <input type="text" class="form-control" name="user_birth" id="user_birth" placeholder="ex)20220815" maxlength="8" required>
                                        </span>
                                        <div class="invalid-feedback"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="gender">성별 </label>
                                    <select class="form-select" name="user_gender" id="user_gender">
                                        <option value="남자"> 남자 </option>
                                        <option value="여자"> 여자 </option>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group">
                                    <label for="phone">휴대전화</label>
                                    <div>
                                        <input type="text" class="form-control" name="user_phone" id="user_phone" style="width:75%;float:left" maxlength="11">
                                        <button type="button" class="btn btn-outline-primary mt-2" style="float: right" id="phoneChk">인증번호</button>

                                        <input type="text" class="form-control" id="phone" name="phone" style="width:75%;float:left" 
                                                placeholder="인증번호를 입력해주세요." disabled required>
                                        <button type="button" class="dobuleChk btn btn-outline-primary mt-2" style="float:right" id="phoneChk2" >인증확인</button>
                                        <div style="clear: both"></div>
                                        <span class="point successPhoneChk"></span>
                                        <input type="hidden" id="phoneDoubleChk">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="school">
                                        <span class="ps_box">
                                            <label for="schoolGrade"> 대학교 </label>
                                            <select class="form-select" name="tt_school" id="tt_school" required>
                                                <option> </option>
                                                <option value="재학">재학생</option>
                                                <option value="졸업">졸업생</option>
                                            </select>
                                        </span>
                                    </div>

                                    <div class="grade">
                                        <span class="ps_box">
                                            <label for="schoolGrade"> 대표과목</label>
                                            <select class="form-select"name="tt_subject" id="tt_subject" required>
                                                <option> </option>
                                                <option value="국어">국어</option>
                                                <option value="영어">영어</option>
                                                <option value="수학">수학</option>
                                            </select>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="TTdepart">전공/학과</label>
                                    <input type="text" class="form-control" name="tt_depart" id="tt_depart" required>
                                    <div class="invalid-feedback">학과를 입력하세요.</div>
                                </div>

                                <div class="form-group mt-2">
                                    <!-- <form action="#" method="post" enctype="multipart/form-data"> -->
                                        <label for="formFile" class="form-label">재학/졸업증명서 첨부</label>
                                        <input class="form-control" type="file" name="file" id="file" multiple>
                                        <div class="invalid-feedback">증명서를 첨부해주세요.</div>
                                </div>

                                <div class="d-grid gap-2 col-12 mx-auto">
                                    <br>
                                    <button class="btn btn-primary" id="signupTTBtn" type="button">회원가입하기</button>
                                </div>
                                <!--button-->
                            </form>
                        </div>
                        <!--login_form-->
                    </div>

                </div>
                <!--row-->
            </div>   
        </section>
        <!-- End Hero -->


                <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
                <jsp:include page="../footer_before.jsp" flush="true" />
            <script src="${path}/resources/js/1-04_signUp.js"></script>
                <!-- ============================================== -->

    </body>

    </html>