<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <a href="/mypage/studentPage" class="list-group-item list-group-item-action px-lg-4">기본
                        정보</a>
                    <a href="/mypage/studentHands/use" class="list-group-item list-group-item-action px-lg-4 fw-bold">손들기
                        내역</a>
                    <a href="/mypage/myQuestion" class="list-group-item list-group-item-action px-lg-4">나의
                        질문글</a>
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
                    <td class="text-center">220612-000001</td>
                  </tr>
                  <tr>
                    <th class="text-center">이름</th>
                    <td class="text-center">홍길동</td>
                  </tr>
                  <tr>
                    <th class="text-center">구매 일자</th>
                    <td class="text-center">2022-06-12</td>
                  </tr>
                  <tr>
                    <th class="text-center">휴대폰 번호</th>
                    <td class="text-center">010-0000-0000</td>
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
                    <td class="text-center">손들기 15개</td>
                    <td class="text-center">2</td>
                    <td class="text-center">6,600</td>
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
                    <td class="text-center">결제 금액</td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- 뒤로가기 -->
            <a href="/mypage/studentHands/buy" class="btn rounded-circle bg-blue mt-3 float-end">&lt;</a>

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