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

  <title>손들기 내역(학생)_구매 상세 내역</title>
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
          <span class="h3 fw-bold">구매 상세 내역</span>
        </div>

        <!-- FROM -->
        <div class="container row">

          <div class="col">
            <div class="my-3">
              <span class="btn btn-dark">구매자 정보</span>
            </div>

            <div class="container card bg-card">
              <table class="table table-padding">
                <tbody>
                  <tr>
                    <th class="text-center">구매 번호</th>
                    <td class="text-center">
                      <fmt:formatDate value="${_BUYINFO_.b_date}" pattern="yyMMdd" />-
                      <fmt:formatNumber minIntegerDigits="6" type="number" pattern="######" value="${_BUYINFO_.b_number}" />
                    </td>
                  </tr>
                  <tr>
                    <th class="text-center">이름</th>
                    <td class="text-center"> ${_BUYINFO_.user_name} </td>
                  </tr>
                  <tr>
                    <th class="text-center">구매 일자</th>
                    <td class="text-center"> <fmt:formatDate value="${_BUYINFO_.b_date}" pattern="yyyy.MM.dd" /> </td>
                  </tr>
                  <tr>
                    <th class="text-center">휴대폰 번호</th>
                    <td class="text-center"> ${_BUYINFO_.user_phone} </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 구매자 정보-->

          <div class="col">
            <div class="my-3">
              <span class="btn btn-dark">상품 정보</span>
            </div>

            <div class="container card bg-card">
              <table class="table table-padding">
                <thead>
                  <tr>
                    <th scope="col" class="text-center">구매 상품</th>
                    <th scope="col" class="text-center">수량</th>
                    <th scope="col" class="text-center">가격</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="text-center"> ${_BUYINFO_.h_name} </td>
                    <td class="text-center"> ${_BUYINFO_.b_count} </td>
                    <td class="text-center"> ${_BUYINFO_.b_price} </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <div class="my-3">
              <span class="btn btn-dark">결제 정보</span>
            </div>

            <div class="container card bg-card">
              <table class="table table-padding">
                <thead>
                  <tr>
                    <th scope="col" class="text-center">결제 방법</th>
                    <th scope="col" class="text-center">결제 금액</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="text-center">무통장 입금</td>
                    <td class="text-center"> ${_BUYINFO_.b_price} </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- 뒤로가기 -->
            <!-- 1. 기간조회 X -->
            <c:if test="${_CURRENTPAGE_.dateFrom eq null}">
              <a href="/mypage/studentHands/buy?currPage=${_CURRENTPAGE_.currPage}" class="btn rounded-circle bg-blue mt-3 float-end">&lt;</a>
            </c:if>
            <!-- 2. 기간조회 O -->
            <c:if test="${_CURRENTPAGE_.dateFrom ne null}">
              <a href="/mypage/studentHands/buy?currPage=${_CURRENTPAGE_.currPage}&dateFrom=${_CURRENTPAGE_.dateFrom}&dateTo=${_CURRENTPAGE_.dateTo}" class="btn rounded-circle bg-blue mt-3 float-end">&lt;</a>
            </c:if>

          </div>
          <!-- 상품 정보, 결제 정보-->

        </div>
        <!--End main contents card(박스)-->

        <!-- TO -->

      </div><!-- End main contents -->

    </div><!-- End Section -->

  </section><!-- End main Section -->



  <!-- ======= footer + js  ======= -->
  <jsp:include page="../footer.jsp" flush="true" />
  <!-- ============================= -->
</body>

</html>