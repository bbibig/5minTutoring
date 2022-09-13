<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> -->


<!doctype html>
<html lang="ko">

<head>
  <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
  <jsp:include page="../htmlHead.jsp" flush="true" />
  <!-- =======mypage 공통 CSS=================================== -->
  <link href="${path}/resources/css/mypage.css" rel="stylesheet">

  <title>손들기 내역(학생)_사용 내역</title>
</head>

<body>
  <!-- ============= 공통 Header : 로그인 후 =================== -->
  <jsp:include page="../header_login.jsp" flush="true" />
  <!-- ========================================================= -->

  <!-- ======= Hero Section ======= -->

  <!-- ======= main Section ======= -->
  <section id="main" class="container">

    <div class="row">

      <div id="nav" class="col-lg-3">
        <ul class="list-group mb-2 bg-dark text-white">
          <li class="list-group-item bg-blue fw-bold fs-5 px-lg-4">마이페이지</li>
        </ul>
        <div class="list-group">
          <c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentPage"
              class="list-group-item list-group-item-action px-lg-4">기본
              정보</a></c:if>
          <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorPage"
              class="list-group-item list-group-item-action px-lg-4">기본
              정보</a></c:if>

          <c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentHands/use"
              class="list-group-item list-group-item-action px-lg-4 fw-bold">손들기
              내역</a></c:if>
          <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorHands/get"
              class="list-group-item list-group-item-action px-lg-4 fw-bold">손들기
              내역</a></c:if>

          <c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/myQuestion"
              class="list-group-item list-group-item-action px-lg-4">나의
              질문글</a></c:if>

          <a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
            커뮤니티</a>

          <a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4">1:1
            문의하기</a>

          <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4">회원
            탈퇴</a>
        </div>
      </div><!-- End main nav -->


      <div id="contents" class="col-lg-9">

        <div>
          <span class="h3 fw-bold">손들기 내역</span>
          <span class="fw-bold float-end mx-3">보유중인 손들기 00개</span>
        </div>

        <!-- FROM -->
        <div class="my-3">
          <a href="/mypage/studentHands/use" class="btn bg-blue">사용 내역</a>
          <a href="/mypage/studentHands/buy" class="btn btn-dark">구매 내역</a>

          <span class="float-end">
            <form action="/mypage/studentHands/use" method="get" onsubmit="return dateCheck();">
              <input type="date" name="dateFrom" id="dateFrom" required>
              -
              <input type="date" name="dateTo" id="dateTo" required>
              <button type="submit" class="btn bg-blue mx-3">조회</button>
            </form>
          </span>

        </div>

        <div class="container card p-4 bg-card">
          <table class="table table-hover table-padding">
            <thead>
              <tr>
                <th scope="col" class="text-center">사용일</th>
                <th scope="col" class="text-center">사용한 손들기</th>
                <th scope="col" class="text-center">사용튜터</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="usehand" items="${_MYUSEHAND_}">
                <tr>
                  <td class="text-center"> <fmt:formatDate value="${usehand.use_date}" pattern="yyyy.MM.dd" /> </td>
                  <td class="text-center">
                    <c:if test="${not empty usehand.qb_number}"> 질문하기(3개) </c:if>
                    <c:if test="${not empty usehand.tb_number}"> 과외받기(5개) </c:if>
                  </td>
                  <td class="text-center"> ${usehand.tutor_email} </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

        </div>
        <!--End main contents card(박스)-->

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center p-5">
            <li class="page-item"><a class="page-link rounded-circle" href="/mypage/studentHands/use?currPage=1">&laquo;</a>
            </li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/studentHands/use?currPage=${_MYUSEHANDPAGENATION_.cri.currPage - 1}">&lt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle bg-blue"
                href="/mypage/studentHands/use?currPage=${_MYUSEHANDPAGENATION_.cri.currPage}">${_MYUSEHANDPAGENATION_.cri.currPage}</a>
            </li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/studentHands/use?currPage=${_MYUSEHANDPAGENATION_.cri.currPage + 1}">&gt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle"
                href="/mypage/studentHands/use?currPage=${_MYUSEHANDPAGENATION_.realEndPage}">&raquo;</a></li>
          </ul>
        </nav>

        <!-- TO -->

      </div><!-- End main contents -->

    </div><!-- End Section -->

  </section><!-- End main Section -->



  <!-- ======= footer + js  ======= -->
  <jsp:include page="../footer.jsp" flush="true" />
  <!-- ========기간검색 유효성====== -->
  <script src="${path}/resources/js/7-10_mypage_sohee.js"></script>
</body>

</html>