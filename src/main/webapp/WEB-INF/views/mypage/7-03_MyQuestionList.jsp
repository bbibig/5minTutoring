<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
  <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
  <jsp:include page="../htmlHead.jsp" flush="true" />
  <!-- =======mypage 공통 CSS=================================== -->
  <link href="${path}/resources/css/mypage.css" rel="stylesheet">

  <title>나의 질문글 목록</title>

  <script>
     $(document).ready(function () {
      let p1 = document.querySelector("#bPrev1");
      let p2 = document.querySelector("#bPrev2");
      let n1 = document.querySelector("#bNext1");
      let n2 = document.querySelector("#bNext2");

      let bPrev = '${_MYQLISTPAGENATION_.prev}';
      let bNext = '${_MYQLISTPAGENATION_.next}';

      if(bPrev == 'false') { p1.classList.add("invisible"); p2.classList.add("invisible") };
      if(bNext == 'false') { n1.classList.add("invisible"); n2.classList.add("invisible") };
    });


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
              class="list-group-item list-group-item-action px-lg-4">손들기
              내역</a></c:if>
          <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorHands/get?group=1"
              class="list-group-item list-group-item-action px-lg-4">손들기
              내역</a></c:if>

          <c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/myQuestion"
              class="list-group-item list-group-item-action px-lg-4 fw-bold">나의
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

        <h3 class="fw-bold">나의 질문글</h3>

        <!-- FROM -->
        <div class="container card p-4 bg-card">
          <table class="table table-hover table-padding">
            <thead>
              <tr>
                <th scope="col">NO.</th>
                <th scope="col" colspan="2" class="text-center">제목</th>
                <th scope="col" class="text-center">작성일자</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="myquestion" items="${_MYQLIST_}">
                <tr>
                  <th scope="row"> ${myquestion.qb_number} </th>
                  <td colspan="2"><a href="/tutor/watchAnswer?num=${myquestion.qb_number}&tp=${myquestion.tp_number}"> 
                    <c:set var="title" value = "${myquestion.qb_title}" />
                    ${fn:substring(title,0,35)}
                  </a></td>
                  <td class="text-center">
                    <fmt:formatDate value="${myquestion.regdate}" pattern="yyyy.MM.dd" />
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

        </div>
        <!--End main contents card(박스)-->

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center p-5">
            <li class="page-item" id="bPrev1"><a class="page-link rounded-circle" href="/mypage/myQuestion?currPage=1">&laquo;</a>
            </li>
            <li class="page-item" id="bPrev2"><a class="page-link rounded-circle"
                href="/mypage/myQuestion?currPage=${_MYQLISTPAGENATION_.cri.currPage - 1}">&lt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle bg-blue"
                href="/mypage/myQuestion?currPage=${_MYQLISTPAGENATION_.cri.currPage}">${_MYQLISTPAGENATION_.cri.currPage}</a>
            </li>
            <li class="page-item" id="bNext1"><a class="page-link rounded-circle"
                href="/mypage/myQuestion?currPage=${_MYQLISTPAGENATION_.cri.currPage + 1}">&gt;</a></li>
            <li class="page-item" id="bNext2"><a class="page-link rounded-circle"
                href="/mypage/myQuestion?currPage=${_MYQLISTPAGENATION_.realEndPage}">&raquo;</a></li>
          </ul>
        </nav>

        <!-- TO -->

      </div><!-- End main contents -->

    </div><!-- End Section -->

  </section><!-- End main Section -->



  <!-- ======= footer + js  ======= -->
  <jsp:include page="../footer.jsp" flush="true" />
  <!-- ============================= -->

</body>

</html>