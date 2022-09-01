<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
<script>
    let user = '${__LOGIN_KEY__}';
    
</script>

<header id="header" class="header fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center">

      <a href="/login/home" class="logo d-flex align-items-center img-fluid animated ">
        <img src="${path}/resources/img/5mtutoring.png" alt=""></a>
      
      <nav id="navbar" class="navbar">
        <ul>
            
          <li><a class="nav-link scrollto active d-xs-none " style="margin-left:100px" href="/tutor/main">튜터</a></li>
          <li><a class="nav-link scrollto d-xs-none" href="/community">커뮤니티</a></li>
          <li>
            <div class="hand" style="margin-left: 650px;">
                <a href="/hand/buyHands"><i class="fa-solid fa-hand fa-2x"></i>
                <div class="count">${__LOGIN_USER__.hands_wallet}</div> </a>
            </div>
          </li>

          <li class="dropdown"><a href="#"><img src="${path}/resources/img/profile.png" width="40px" height="40px">
                <span class="ms-2">${__LOGIN_USER__.user_nick}</span>
              <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="/tutor/info">튜터페이지</a></li>
              <li><a href="/mypage/studentPage">마이페이지</a></li>
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