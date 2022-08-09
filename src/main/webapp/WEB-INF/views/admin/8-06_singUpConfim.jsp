<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.request.contextPath}" />

        <%! String admin="이윤정" ; %>

            <!doctype html>

            <html lang="ko">

            <head>
                <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
                <jsp:include page="../htmlHead.jsp" flush="true" />
                <link href="${path}/resources/css/admin_header_footer.css" rel="stylesheet">
                <link href="${path}/resources/css/8_admin_page.css" rel="stylesheet">
                <!-- ========================================================= -->
                <title>admin - 튜터 가입 승인</title>

            </head>

            <body>
                <!-- ======= Admin Header ======= -->
                <header id="header" class="header fixed-top">
                    <div class="container-fluid container-xl d-flex align-items-center">
                        <a href="/admin" class="logo d-flex align-items-center img-fluid animated ">
                            <img src="${path}/resources/img/admin_logo.png" alt=""></a>

                        <div class="administrator">
                            <ul>
                                <li>
                                    <a href="#" onclick="logout()">
                                        <%= admin %> 관리자
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </header>
                <!-- ======= Admin Header ======= -->

                <!-- ======= Section ======= -->
                <div class="container">

                    <!-- ===== 메뉴리스트 첨부 ===== -->
                    <jsp:include page="8-00_menuList.jsp" flush="true" />
                    <!-- ========================== -->

                    <div id="contents" class="col-lg-9">

                        <div class="tutor-head-title">
                            <ul>
                                <li>
                                    <h3 class="fw-bold">튜터 가입 승인</h3>
                                </li>

                                <li>
                                    <div class="signup-confirm-tap">
                                        <a href="#" class="btn btn-dark" onclick="signupok()">가입 승인</a>
                                        <a href="#" class="btn btn-dark" onclick="signupcancle()">가입 거절</a>
                                    </div>
                                </li>
                            </ul>
                        </div>



                        <!-- Content List -->
                        <div class="container card p-4 bg-card">
                            <table class="table table-hover table-padding">
                                <thead>
                                    <tr>
                                        <th>
                                            <form action="">
                                                <input type="checkbox" id="CheckAll" name="CheckAll" value="allid">
                                            </form>
                                        </th>
                                        <th>회원 번호</th>
                                        <th>계정</th>
                                        <th>닉네임</th>
                                        <th>가입일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <form action="">
                                                <input type="checkbox" id="Checkbox" name="Checkbox" value="userid">
                                            </form>
                                        </td>
                                        <td>stu-0001</td>

                                        <td class="dropdown">
                                            <a href="" class="dropbtn">abcde@gmail.com</a>
                                            <div class="dropdown-content" id="myDropdown2">
                                                <a href="#" onclick="openUserPop()" >회원 정보 조회</a>
                                            </div>
                                        </td>

                                        <td>nickname</td>
                                        <td>2022-08-01</td>
                                    </tr>


                                    <tr>
                                        <td>
                                            <form action="">
                                                <input type="checkbox" id="Checkbox" name="Checkbox" value="userid">
                                            </form>
                                        </td>
                                        <td>stu-0002</td>

                                        <td class="dropdown">
                                            <a href="" class="dropbtn">abcde@gmail.com</a>
                                            <div class="dropdown-content" id="myDropdown2">
                                                <a href="#" onclick="openUserPop()" >회원 정보 조회</a>
                                            </div>
                                        </td>

                                        <td>nickname</td>
                                        <td>2022-08-01</td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <form action="">
                                                <input type="checkbox" id="Checkbox" name="Checkbox" value="userid">
                                            </form>
                                        </td>
                                        <td>stu-0003</td>

                                        <td class="dropdown">
                                            <a href="" class="dropbtn">abcde@gmail.com</a>
                                            <div class="dropdown-content" id="myDropdown3">
                                                <a href="#" onclick="openUserPop()" >회원 정보 조회</a>
                                            </div>
                                        </td>

                                        <td>nickname</td>
                                        <td>2022-08-01</td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <form action="">
                                                <input type="checkbox" id="Checkbox" name="Checkbox" value="userid">
                                            </form>
                                        </td>
                                        <td>stu-0004</td>

                                        <td class="dropdown">
                                            <a href="" class="dropbtn">abcde@gmail.com</a>
                                            <div class="dropdown-content" id="myDropdown4">
                                                <a href="#" onclick="openUserPop()" >회원 정보 조회</a>
                                            </div>
                                        </td>

                                        <td>nickname</td>
                                        <td>2022-08-01</td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <form action="">
                                                <input type="checkbox" id="Checkbox" name="Checkbox" value="userid">
                                            </form>
                                        </td>
                                        <td>stu-0005</td>

                                        <td class="dropdown">
                                            <a href="" class="dropbtn">abcde@gmail.com</a>
                                            <div class="dropdown-content" id="myDropdown5">
                                                <a href="#" onclick="openUserPop()" >회원 정보 조회</a>
                                            </div>
                                        </td>

                                        <td>nickname</td>
                                        <td>2022-08-01</td>
                                    </tr>

                                </tbody>
                            </table>

                        </div>
                        <!--End main contents card(박스)-->

                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center p-5">
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&laquo;</a>
                                </li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a>
                                </li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li>
                                <li class="page-item"><a class="page-link rounded-circle" href="#">&raquo;</a>
                                </li>
                            </ul>
                        </nav>

                        <!-- TO -->

                    </div><!-- End main contents -->

                </div><!-- End Section -->

                </section><!-- End main Section -->
                </div>
                <!-- End Section -->

                <!-- ============= 공통 footer + js =============== -->
                <jsp:include page="../footer.jsp" flush="true" />
                <script src="${path}/resources/js/8_admin.js"></script>
                <!-- ============================================== -->
            </body>

            </html>