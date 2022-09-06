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
    <!-- ========================================================= -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <title>ADMIN-회원상세정보</title>

</head>
<body>
    <div class="modal-body">
        <div class="user">
            <div id="user1">
                <label for="userInfo" class="userinfo">회원정보</label>
            </div>
            <ul>
                <li>${_USERINFO_.user_name}</li>
                <li>${_USERINFO_.user_email}</li>
                <li>${_USERINFO_.user_nick}</li>
                <li>${_USERINFO_.user_birth}</li>
                <li>${_USERINFO_.user_phone}</li>
                <div class="school">
                    <li>${_USERINFO_.tt_school}</li>
                    <li>${_USERINFO_.tt_subject}</li>
                    <li>${_USERINFO_.tt_depart}</li>
                </div>
                <button type="button" class="btn btn-secondary btn-sm" onclick="closeUserPop()">닫기</button>   
            </ul>   
                <a href="" id="imgbtn">파일확인</a>
            <div id="uploadResult">
                <!-- <div id="result_card">
                    <img src="/resources/img/back.png" class="img">
                </div> -->
            </div>
        </div>
    </div>
    <script>
        let ttFile = '${_TUTOR_FILE_}';
        let uploadPath = '${_TUTOR_FILE_.uploadPath}';
        let uuid = '${_TUTOR_FILE_.uuid}';
        let fileName = '${_TUTOR_FILE_.fileName}';
        $('#imgbtn').click(function(e){
            e.preventDefault();
            // alert(ttFile);

            let uploadResult = $("#uploadResult");
            let str = "";
            let fileCallPath = encodeURIComponent(uploadPath + uuid + "_" + fileName);
            str += "<div id='result_card'>";
            str += "<img src='/admin/display?fileName=" + fileCallPath +"'>";
            str += "</div>";
            uploadResult.append(str);
        })
    </script>
<script src="${path}/resources/js/8_admin.js"></script>
</body>