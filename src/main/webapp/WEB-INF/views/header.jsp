<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<header id="header" class="header fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center">

      <a href="/" class="logo d-flex align-items-center img-fluid animated ">
        <img src="${path}/resources/img/5mtutoring.png" alt=""></a>

      <nav id="navbar" class="navbar">
        <ul>

          <li><a class="nav-link scrollto active d-xs-none " style="margin-left:100px" href="/login">튜터</a></li>
          <li><a class="nav-link scrollto d-xs-none" href="/login">커뮤니티</a></li>
          <li><a class="getstarted scrollto" style="margin-left: 700px;" href="/login">로그인</a></li>

        </ul>
        <i class="bi bi-list mobile-nav-toggle" style="margin-left:150px"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->