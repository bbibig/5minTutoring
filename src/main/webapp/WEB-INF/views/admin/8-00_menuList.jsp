<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <!-- ======= main Section ======= -->
        <section id="main" class="container">
            <div class="row">

        <!-- ========== admin side ========== -->
        <div id="nav" class="col-lg-3">

            <div class="list-group">
                <a href="/admin/student" class="list-group-item list-group-item-action px-lg-4 fw-bold ">회원 관리</a>
                <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4">관리자</a>
                <a href="/admin/answerBoard_OK" class="list-group-item list-group-item-action px-lg-4">문의 게시판</a>
                <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
                <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4">매출 관리</a>
                <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4">튜터 가입 승인</a>
            </div>
        </div>
