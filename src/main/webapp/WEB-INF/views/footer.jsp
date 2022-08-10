<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="path" value="${pageContext.request.contextPath}" />

    <footer class="main-footer">
      <div class="container">
        <div class="footer-content">
          <img src="${path}/resources/img/hand.png" alt="" />

          <div class="foot-link">
            <ul>
              <li><a href="/faq">자주 묻는 질문(FAQ)</a></li>
              <li><a href="/inquiry">문의하기</a></li>
            </ul>
          </div>

          <div class="foot-info">
            <ul>
              <li>법인명: 오분과외 주식회사</li>
              <li>사업자 등록번호: 144-81-25874</li>
              <li>대표자명: 문희철</li>
              <li>개인정보보호책임 : 이윤정</li>
              <li>통신판매업신고 : 2019-서울강남-02231호</li>
            </ul>
          </div>
        </div>

        <hr />

        <p>
          &copy; Copyright <strong><span>2022 5mtutor</span></strong>. All Rights Reserved
        </p>
      </div>
    </footer>

    <!-- ======= JS ======= -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"></script>
    <!-- alert javascript -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- js -->
    <script src="${path}/resources/js/main.js"></script>