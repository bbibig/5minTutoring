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
            <link href="${path}/resources/css/8-02-userInfo.css" rel="stylesheet" >

<!--             <script> 
                 //회원정보조회
                 let popup;
                 function openUserPop() {
                     popup = window.open("8-02-04_admin_user_info.html", "이거뭐지?",
                         "width=310, height=400, left=1000, top=100, status=0 location=0",
                         scrollbars = "no");
                 }//openUserPop;
                 function closeUserPop() {
                     popup = window.close();
                 }//closeUserPop
             </script> -->
            <!-- ========================================================= -->

            <title>ADMIN-회원상세정보</title>

        </head>
        <body>
            <div class="modal-body">
                <div class="user">
                    <ul>
                        <div id="user1">
                            <label for="userInfo" class="userinfo">
                                회원정보
                            </label>
                        </div>
                        <li>김회원</li>
                        <li>abcde@이메일</li>
                        <li>잘웃는진도개</li>
                        <li>010-0000-0000</li>
                        <li>0000년 00월 00일</li>
                        <div class="school">
                            <li>학교이름</li>
                            <li>학년</li>
                        </div>
                        <button type="button" class="btn btn-secondary btn-sm" onclick="closeUserPop()">닫기</button>
                    </ul>
                </div>
            </div>
        
        <script src="${path}/resources/js/8_admin.js"></script>
        </body>