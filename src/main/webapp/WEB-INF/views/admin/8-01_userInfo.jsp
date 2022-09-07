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

            <title>ADMIN-회원상세정보</title>

        </head>
        <body>
            <div class="modal-body">
                <div class="user">
                    <ul>
                        <div id="user1">
                            <label for="userInfo" class="userinfo">회원정보</label>
                        </div>
                        <li>${_USERINFO_.user_name}</li>
                        <li>${_USERINFO_.user_email}</li>
                        <li>${_USERINFO_.user_nick}</li>
                        <li>${_USERINFO_.user_phone}</li>
                        <li>${_USERINFO_.user_phone}</li>
                        <div class="school">
                            <c:choose>
                                <c:when test="${_USERINFO_.st_school != null}">
                                    <li>${_USERINFO_.st_school}</li>
                                </c:when>
                                <c:otherwise>
                                    <li>${_USERINFO_.tt_school}</li>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${_USERINFO_.st_grade != null}">
                                    <li>${_USERINFO_.st_grade}</li>
                                </c:when>
                                <c:otherwise>
                                    <li>${_USERINFO_.tt_subject}</li>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${_USERINFO_.tt_depart != null}">
                                <li>${_USERINFO_.tt_depart != null}</li>
                            </c:if>
                        </div>
                        <button type="button" class="btn btn-secondary btn-sm" onclick="closeUserPop()">닫기</button>
                    </ul>
                </div>
            </div>
        
        <script src="${path}/resources/js/8_admin.js"></script>
        </body>