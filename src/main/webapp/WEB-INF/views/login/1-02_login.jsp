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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

        <script>
            $(function(){
            
                let loginResult = '${_LOGIN_}';
                if (loginResult != null && loginResult.length > 0) {
                    Swal.fire({
                        text: loginResult,
                        icon: 'error',
                        showCloseButton: true
                    });
                }//로그인 실패시 

                $('#LOGIN').on('click',function(){
                    let idVal = $('#floatingInput').val();
                    let pwVal = $('#floatingPassword').val();
                    if(idVal==null | idVal==""){
                        Swal.fire({
                        text: '아이디를 입력해주세요.',
                        icon: 'error',
                        showCloseButton: true
                        });
                        return false;
                    } else if( pwVal==null | pwVal==""){
                        Swal.fire({
                        text: '비밀번호를 입력해주세요.',
                        icon: 'error',
                        showCloseButton: true
                        });
                        return false;
                    }//if-else
                    
                    let formObj = $('#loginForm');
                    formObj.attr('action','/login/Loginpost');
                    formObj.attr('method', 'POST');
                    formObj.submit();
                });//LOGIN

            })//jq

        </script>

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

                <form action="/login/Loginpost" method="post" id="loginForm">
                    <!-- 로고 -->
                    <div>
                        <img src="${path}/resources/img/5mtutoring_grey.png">
                    </div>

                    <!-- 이메일 -->
                    <div class="form-floating">
                        <input type="email" class="form-control" id="floatingInput" name="user_email"
                            placeholder="name@example.com">
                        <label for="floatingInput">Email address</label>
                    </div>

                    <!-- 비밀번호 -->
                    <div class="form-floating">
                        <input type="password" class="form-control" id="floatingPassword" name="user_pw"
                            placeholder="Password">
                        <label for="floatingPassword">Password</label>
                    </div>

                    <!-- 로그인 버튼 -->
                    <a class="loginBtn" href="#">
                        <button type="button" id="LOGIN">로그인</button>
                    </a>
                </form>

                <!-- 이메일/비번찾기 회원가입 -->
                <div class="sign-in-more-action">
                    <a href="/login/findMyEmail">이메일 찾기</a>
                    <a href="/login/findMyPassword">비밀번호 찾기</a>
                    <a href="/login/selectAccount">회원가입</a>
                </div>

                <!-- 구분선 -->
                <hr>

                <!-- 간편 로그인 -->
                <div class="sns-l">

                    <p>간편로그인</p>
                    <!-- 네이버 로그인 -->
                        <span>
                            <a id="naverIdLogin_loginButton" href="javascript:void(0)">
                                <span><img src="${path}/resources/img/naver_logo.png" alt="naver_logo"></span>
                            </a>
                        </span>

                    <!-- 카카오 로그인 -->
                    <span class="ico-sns-loin-kakao">
                        <!-- REST API key 사용 -->
                        <!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=f242881542c06c438c6f81728a868bf9&redirect_uri=http://localhost:8080/test/kakao&response_type=code">
                            <img src="${path}/resources/img/kakao_logo.png" alt="kakao_logo">                             
                        </a> -->
                        <!-- javaScript key 사용 -->
                        <a href="javascript:kakaoLogin()"><img src="${path}/resources/img/kakao_logo.png" alt="kakao_logo"></a>
                        
                    </span>
                    
                </div>
                <form name="kakaoForm" method="post" action="/kakao/setStudent" id="kakaoForm">
                    <input type="hidden" name="user_email" id="kakaoEmail">
                    <input type="hidden" name="kakaologin" id="kakaoId">
                </form>


            </section>
        </div>
        <!-- End Section -->
        <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
        <script>
            Kakao.init('de958c943b70794d33e58bbec3e2a1da'); //발급받은 키 중 javascript키를 사용해준다.
            console.log(Kakao.isInitialized()); // sdk초기화여부판단

            function kakaoLogin() {
                Kakao.Auth.login({
                success: function (response) {
                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function (response) {
                            kakaoLoginPro(response)
                            //값들어왔는지 확인
                            console.log(response)
                        },
                        fail: function (error) {
                            console.log(error)
                        },
                    })
                },
                fail: function (error) {
                    console.log(error)
                },
                })
            }
            //카카오로그인
            function kakaoLoginPro(response){
            var data = {
                id:response.id,
                email:response.kakao_account.email
            }
            $.ajax({
                type : 'POST',
                url : '/kakao/kakaoLoginPro',
                data : data,
                success : function(data){
                    console.log(data)
                    if(data.JavaData == "YES"){
                        alert("로그인되었습니다.");
                        location.href = '/login/home'

                    }else if(data.JavaData == "register"){
                        alert("필수항목을 입력해주세요.");
                        let kakaoform = $('#kakaoForm');
                        $("#kakaoEmail").val(response.kakao_account.email);
                        $("#kakaoId").val(response.id);
                        kakaoform.submit();
                    }else{
                        alert("로그인에 실패했습니다");
                    }
                },
                error: function(xhr, status, error){
                    alert("오류가 발생했습니다. 확인해주세요."+error);
                }
                });//ajax
            }//kakaoLoginPro
        </script>
        
        <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
        <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
        <jsp:include page="../footer_before.jsp" flush="true" />
        <script src="${path}/resources/js/1_01_login.js"></script>
        <!-- ============================================== -->

    </html>