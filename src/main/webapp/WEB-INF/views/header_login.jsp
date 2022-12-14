<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
<% String bbbsId = (String)request.getAttribute("bbsId"); %>

</style>

<header id="header" class="header fixed-top">
  <div class="container-fluid container-xl d-flex align-items-center">

    <a href="/login/home" class="logo d-flex align-items-center img-fluid animated ">
      <img src="${path}/resources/img/5mtutoring.png" alt=""></a>

    <nav id="navbar" class="navbar">
      <ul>

        <li><a class="nav-link scrollto d-xs-none" style="margin-left:100px" href="/tutor/main?">튜터</a></li>
        <li><a class="nav-link scrollto d-xs-none" it="cpage" href="/community">커뮤니티</a></li>
        <li>
          <div class="hand" style="margin-left: 650px;">
            <a href="/hand/buyHands"><i class="fa-solid fa-hand fa-2x"></i>
              <div class="count">${__LOGIN_USER__.hands_wallet}</div>
            </a>
          </div>
        </li>

        <li class="dropdown"><a href="#">
            <!-- 로컬에 프로필 사진이 존재해야 사진이 깨지지 않음 -->
            <c:if test="${_USER_PROFILE_ eq 'false'}"> <img src="${path}/resources/img/profile.png" width="40px"
                height="40px"> </c:if>
            <c:if test="${_USER_PROFILE_ eq 'true'}"> <img
                src="<spring:url value='/profile/${__LOGIN_USER__.user_nick}_profile.png'/>" width="40px" height="40px"
                class="rounded-circle"> </c:if>
                
            <span class="ms-2">${__LOGIN_USER__.user_nick}</span>
            <i class="bi bi-chevron-down"></i>
          </a>
          <ul>
            <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}">
              <li><a href="/tutor/info?num=${_TP_NUMBER_}">튜터페이지</a></li>
            </c:if>

            <c:if test="${__LOGIN_USER__.user_group eq 'Student'}">
              <li><a href="/mypage/studentPage">마이페이지</a></li>
            </c:if>
            <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}">
              <li><a href="/mypage/tutorPage">마이페이지</a></li>
            </c:if>

            <li>
              <a href="/login/logout">로그아웃<i class="fa-solid fa-arrow-right-from-bracket"></i> </a>
            </li>
          </ul>

        </li>

      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav><!-- .navbar -->
  </div>
</header><!-- End Header -->