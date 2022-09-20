<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
  <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
  <jsp:include page="../htmlHead.jsp" flush="true" />
  <!-- =======mypage 공통 CSS=================================== -->
  <link href="${path}/resources/css/mypage.css" rel="stylesheet">

  <title>손들기 내역(튜터)_획득 내역</title>

  <script>
    window.onload = function () {
      // 오늘날짜
      var date = new Date();
      var today = date.toISOString().slice(0, 10);
      var dateTo = document.getElementById("dateTo");
      dateTo.value = today;

      //오늘날짜 3개월 전
      var month = date.getMonth();
      var before = new Date(date.setMonth(month - 3)).toISOString().slice(0, 10);
      var dateFrom = document.getElementById("dateFrom");
      dateFrom.value = before;
    }
  </script>
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

          <c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentHands/use?group=1"
              class="list-group-item list-group-item-action px-lg-4 fw-bold">손들기
              내역</a></c:if>
          <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorHands/get?group=1"
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
          <span class="fw-bold float-end">
            <span>보유중인 손들기 ${__LOGIN_USER__.hands_wallet}개</span>
            <a href="/mypage/withdraw" class="btn btn-dark mx-3">출금하기</a>
          </span>
        </div>

        <!-- FROM -->
        <div class="my-3">
          <a href="/mypage/tutorHands/get?group=1" class="btn bg-blue">획득 내역</a>
          <a href="/mypage/tutorHands/withdraw" class="btn btn-dark">출금 내역</a>

          <span class="float-end">
            <form action="/mypage/tutorHands/get" method="get" onsubmit="return dateCheck();">
              <select name="group" id="group">
                <option value="1" <c:if test="${GROUP eq 1}">selected</c:if> >답변하기</option>
                <option value="2" <c:if test="${GROUP eq 2}">selected</c:if> >과외하기</option>
              </select>
              <!-- 1. 기간조회 X -->
              <c:if test="${_MYGETHANDPAGENATION_.cri.dateFrom eq null}">
                <input type="date" name="dateFrom" id="dateFrom" required>
                -
                <input type="date" name="dateTo" id="dateTo" required>
              </c:if>
              <!-- 2. 기간조회 O -->
              <c:if test="${_MYGETHANDPAGENATION_.cri.dateFrom ne null}">
                <input type="date" name="dateFrom" required value="${_MYGETHANDPAGENATION_.cri.dateFrom}">
                -
                <input type="date" name="dateTo" required value="${_MYGETHANDPAGENATION_.cri.dateTo}">
              </c:if>
              <button type="submit" class="btn bg-blue mx-3">조회</button>
            </form>
          </span>

        </div>

        <div class="container card p-4 bg-card">
          <table class="table table-hover table-padding">

            <thead>
              <tr>
                <th scope="col" class="text-center">획득 일자</th>
                <th scope="col" class="text-center">획득한 손들기</th>
                <th scope="col" class="text-center">답변학생</th>
              </tr>
            </thead>

            <tbody>
              <c:forEach var="gethand" items="${_MYGETHAND_}">
                <tr>
                  <td class="text-center">
                    <fmt:formatDate value="${gethand.regdate}" pattern="yyyy.MM.dd" />
                  </td>
                  <td class="text-center"> 손들기 3개 </td>
                  <td class="text-center">
                    <c:if test="${GROUP eq 1}"><a href="/tutor/watchAnswer?num=${gethand.qb_number}&tp=${TpNum}"></c:if>
                    <c:if test="${GROUP eq 2}"><a href="#"></c:if>
                     ${gethand.student_nick} </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>

          </table>

        </div>
        <!--End main contents card(박스)-->

        <nav aria-label="Page navigation example">
          <!-- 1. 기간 조회 안할때 페이징 -->
          <c:if test="${_MYGETHANDPAGENATION_.cri.dateFrom eq null}">
            <ul class="pagination justify-content-center p-5">
              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=1&group=${GROUP}">&laquo;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage - 1}&group=${GROUP}">&lt;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle bg-blue"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage}&group=${GROUP}">${_MYGETHANDPAGENATION_.cri.currPage}</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage + 1}&group=${GROUP}">&gt;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.realEndPage}&group=${GROUP}">&raquo;</a>
              </li>
            </ul>
          </c:if>
          <!-- 2. 기간조회 할 때 페이징 -->
          <c:if test="${_MYGETHANDPAGENATION_.cri.dateFrom ne null}">
            <ul class="pagination justify-content-center p-5">
              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=1&group=${GROUP}&dateFrom=${_MYGETHANDPAGENATION_.cri.dateFrom}&dateTo=${_MYGETHANDPAGENATION_.cri.dateTo}">&laquo;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage - 1}&group=${GROUP}&dateFrom=${_MYGETHANDPAGENATION_.cri.dateFrom}&dateTo=${_MYGETHANDPAGENATION_.cri.dateTo}">&lt;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle bg-blue"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage}&group=${GROUP}&dateFrom=${_MYGETHANDPAGENATION_.cri.dateFrom}&dateTo=${_MYGETHANDPAGENATION_.cri.dateTo}">${_MYGETHANDPAGENATION_.cri.currPage}</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.cri.currPage + 1}&group=${GROUP}&dateFrom=${_MYGETHANDPAGENATION_.cri.dateFrom}&dateTo=${_MYGETHANDPAGENATION_.cri.dateTo}">&gt;</a>
              </li>

              <li class="page-item"><a class="page-link rounded-circle"
                  href="/mypage/tutorHands/get?currPage=${_MYGETHANDPAGENATION_.realEndPage}&group=${GROUP}&dateFrom=${_MYGETHANDPAGENATION_.cri.dateFrom}&dateTo=${_MYGETHANDPAGENATION_.cri.dateTo}">&raquo;</a>
              </li>
            </ul>
          </c:if>
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