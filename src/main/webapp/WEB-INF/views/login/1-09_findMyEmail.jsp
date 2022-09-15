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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

            <title>오분과외</title>
            <style>
                .findEmail input{
                    width: 250px;
                    height: 40px;
                    text-align: center;
                }
                .find{
                    font-size: 15px;
                    margin: 5px;
                }
                .moveTologinBtn{
                    width: 80px;
                }
                #phoneChk, #phoneChk2{
                    height: 40px;
                }
                .successPhoneChk{
                    font-size: 15px;
                }
                #m1{
                    margin-top: 20px;
                    font-size: 20px;
                    font-weight: bold;
                }
                #loginBTN{
                    margin: 10px;
                    background-color: #2961f0;
                    display: none;
                }
            </style>
            <script>
                $(function(){
                    $('#phoneChk').click(function(){
                        let phone = $('#user_phone').val();

                        if(phone < 11){
                            Swal.fire({
                                icon: 'error',
                                text: '휴대폰 번호가 올바르지 않습니다.',
                                })//alert
                            // alert("휴대폰 번호가 올바르지 않습니다.");
                            $(".successPhoneChk").text("유효한 번호를 입력해주세요.");
                            $(".successPhoneChk").css("color","red");
                            $("#user_phone").attr("autofocus",true);
                        } else {
                            Swal.fire({
                                title: '<strong>인증번호 <u>발송되었습니다.</u></strong>',
                                icon: 'info',
                                html: '휴대폰에서 인증번호를 확인해 주세요.',
                                })
                            // alert("인증번호 발송이 완료되었습니다. \n 휴대폰에서 인증번호를 확인해 주세요.");
                            console.log(phone);
                        };

                        $.ajax({
                            type:"GET",
                            url:"/login/phoneCheck?user_phone="+phone,
                            success : function(data){
                                if(data =='error'){
                                    alert("휴대폰 번호가 올바르지 않습니다.");
                                    $(".successPhoneChk").text("유효한 번호를 입력해주세요.");
                                    $(".successPhoneChk").css("color","red");
                                    $("#user_phone").attr("autofocus",true);
                                } else {
                                    $("#phoneCheck").attr("disabled",false);
                                    $("#phoneChk2").css("display","inline-block");
                                    $(".successPhoneChk").text("휴대폰 번호 입력후 인증번호를 입력해주세요.");
                                    $(".successPhoneChk").css("color","green");
                                    // $("#user_phone").attr("readonly",true);
                                    code2 = data;
                                }//if-else
                            }//;success
                        });//ajax
                         
                        $("#phoneChk2").click(function(){
                            if($("#phoneCheck").val() == code2){
                                $(".successPhoneChk").text("인증번호가 일치합니다.");
                                $(".successPhoneChk").css("color","green");
                                $("#phoneDoubleChk").val("true");
                                $("#phoneCheck").attr("disabled",true);

                                $.ajax({
                                    type:'POST',
                                    url : "/login/foundEmail?user_phone="+phone,
                                    dataType : "text",
                                    success: function(result){
                                        if(result==""){
                                            $('#m1').text("가입된 계정이 없습니다. 회원가입 해주세요.");
                                            $('#loginBTN').css("display","inline-block"); 
                                            alert("가입된 계정이 없습니다.");
                                        } else {
                                            $('#m1').text(result);
                                            $('#loginBTN').css("display","inline-block");   
                                            alert("회원님의 이메일 계정은 "+result+"입니다.");
                                        }
                                    },
                                });//ajax
                            }else{
                                $(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
                                $(".successPhoneChk").css("color","red");
                                $("#phoneDoubleChk").val("false");
                                $(this).attr("autofocus",true);
                            }
                        });//인증번호체크하기
                    });//phoneChk
                });//jq
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
                    <strong>이메일 찾기</strong>
                    </p>
                    <p class="txt">
                    이전에 가입한 계정을 잊으셨나요?<br>
                    <strong>휴대 전화 번호 인증</strong>으로 가입한 계정을 찾을 수 있습니다.
                    </p>

                    <div class="findEmail">
                        <div class="find">
                            <input type="text" name="user_phone" id="user_phone" placeholder="휴대번호를 입력하세요.">
                            <button class="moveTologinBtn" id="phoneChk" type="button">인증번호</button>
                        </div>
                        <div class="find">
                            <input type="text" name="phoneCheck" id="phoneCheck" placeholder="인증번호를 입력해주세요." disabled >
                            <button class="moveTologinBtn" type="button" id="phoneChk2">메일확인</button>
                        </div>
                        <span class="point successPhoneChk"></span>
                        <input type="hidden" id="phoneDoubleChk">  
                    </div>
                    <div id="m1"> <br>
                        <a href="/login">
                            <button class="btn btn-primary " id="loginBTN">이 계정으로 로그인 하기</button>
                        </a>
                    </div>
                </div>

                </section>
            </div>
            <!-- End Section -->


            <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
             <jsp:include page="../footer_before.jsp" flush="true" />
            <script src="${path}/resources/js/1_01_login.js"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <!-- ============================================== -->

        </html>