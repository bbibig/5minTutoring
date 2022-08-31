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
	
    <title>커뮤니티 작성글목록</title>
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
                    <a href="/mypage/studentPage" class="list-group-item list-group-item-action px-lg-4">기본
                        정보</a>
                    <a href="/mypage/studentHands/use" class="list-group-item list-group-item-action px-lg-4">손들기
                        내역</a>
                    <a href="/mypage/myQuestion" class="list-group-item list-group-item-action px-lg-4">나의
                        질문글</a>
                    <a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4 fw-bold">나의
                        커뮤니티</a>
                    <a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4">1:1
                        문의하기</a>
                    <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4">회원
                        탈퇴</a>
                </div>
            </div><!-- End main nav -->


      <div id="contents" class="col-lg-9">

        <h3 class="fw-bold">나의 커뮤니티</h3>

        <!-- FROM -->
        <div class="my-3">
          <a href="/mypage/community/write" class="btn bg-blue">작성글</a>
          <a href="/mypage/community/comments" class="btn btn-dark">댓글</a>
        </div>

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
              <c:forEach var="mycommunity" items="${_MYCOMMUNITY_}">
                <tr>
                  <th scope="row"> ${mycommunity.fb_number} </th>
                  <td colspan="2"><a href="/tutor/watchAnswer"> ${mycommunity.fb_title} </a></td>
                  <td class="text-center"> <fmt:formatDate value="${mycommunity.fb_date}" pattern="yyyy-MM-dd" /> </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

        </div>
        <!--End main contents card(박스)-->

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center p-5">
            <li class="page-item"><a class="page-link rounded-circle" href="/mypage/community/write?currPage=1">&laquo;</a></li>
            <li class="page-item"><a class="page-link rounded-circle" href="/mypage/community/write?currPage=${_MYCOMMUNITYPAGENATION_.cri.currPage - 1}">&lt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle bg-blue" href="/mypage/community/write?currPage=${_MYCOMMUNITYPAGENATION_.cri.currPage}">${_MYCOMMUNITYPAGENATION_.cri.currPage}</a></li>
            <li class="page-item"><a class="page-link rounded-circle" href="/mypage/community/write?currPage=${_MYCOMMUNITYPAGENATION_.cri.currPage + 1}">&gt;</a></li>
            <li class="page-item"><a class="page-link rounded-circle" href="/mypage/community/write?currPage=${_MYCOMMUNITYPAGENATION_.realEndPage}">&raquo;</a></li>
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