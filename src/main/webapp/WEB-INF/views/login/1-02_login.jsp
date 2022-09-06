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
                        text: '아이디 또는 비밀번호가 일치하지 않습니다',
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
                    
                    let formObj = $('form');
                    formObj.attr('action','/login/Loginpost');
                    formObj.attr('method', 'POST');
                    formObj.submit();
                });//LOGIN

            })//jq
            // function saveToDos(token) { //item을 localStorage에 저장합니다. 
            // typeof(Storage) !== 'undefined' && sessionStorage.setItem('AccessKEY', JSON.stringify(token)); 
            // };

            // Kakao.init('de958c943b70794d33e58bbec3e2a1da');
            // console.log(Kakao.isInitialized());
            // function kakaoLogin() {
            //     window.Kakao.Auth.login({
            //     scope: 'account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
            //     success: function(response) {
            //         console.log(response) // 로그인 성공하면 받아오는 데이터
            //         window.Kakao.API.request({ // 사용자 정보 가져오기 
            //             url: '/v2/user/me',
            //             success: (res) => {
            //                 const kakao_account = res.kakao_account;
            //                 console.log(kakao_account)
            //             }
            //         });
            //         window.location.href='http://localhost:8080/test/getKakao' //리다이렉트 되는 코드
            //     },
            //     fail: function(error) {
            //         console.log(error);
            //     }
            // });
            // }
            // const login = document.querySelector('#kakaoLogin');
            // login.addEventListener('click', kakaoLogin);
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

                <form action="/login/Loginpost" method="post">
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
                        <!-- <a onclick="kakaoLogin()" href="javascript:void(0)">
                            <img src="${path}/resources/img/kakao_logo.png" alt="kakao_logo">        
                        </a> -->
                        <a href="https://kauth.kakao.com/oauth/authorize?client_id=f242881542c06c438c6f81728a868bf9&redirect_uri=http://localhost:8080/test/kakao&response_type=code">
                            <img src="${path}/resources/img/kakao_logo.png" alt="kakao_logo">                             
                        </a>

                        
                    </span>

                </div>


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
        </script>
        
        <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
        <!-- ============= 로그인 전 풋터 아 헷갈린다고요 지금... =============== -->
        <jsp:include page="../footer_before.jsp" flush="true" />
        <script src="${path}/resources/js/1_01_login.js"></script>
        <!-- ============================================== -->

    </html>