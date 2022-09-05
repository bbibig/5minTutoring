<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:set var="path" value="${pageContext.request.contextPath}" />
    <!doctype html>
    <html lang="ko">

    <head>
        <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
        <jsp:include page="../htmlHead.jsp" flush="true" />
        <link href="${path}/resources/css/admin_header_footer.css" rel="stylesheet">
        <link href="${path}/resources/css/8_admin_page.css" rel="stylesheet">
        <!-- ========================================================= -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
        <title>admin - 튜터 가입 승인</title>
        <script>
            $(function(){
                $('#CheckAll').click(function(){
                    if($('#CheckAll').prop("checked")){
                        $('input[type=checkbox]').prop("checked",true);
                    } else {
                        $('input[type=checkbox]').prop("checked",false);
                    }//if-else
                });//CheckAll-전체체크

                let var3;
                let checkList = [];
                $('input[name=checkbox]').click(function(){

                    if( $('input[name=checkbox]:checked').length == 5){
                        $('#CheckAll').prop("checked",true);
                    } else {
                        $('#CheckAll').prop("checked",false);
                    }//if-else-전체체크해제

                    if( $('input[name=checkbox]:checked')){

                        checkList.push($(this).val());
                        console.log("이거는요"+checkList);
                        

                        // let val = $(this).val();
                        // console.log("왜뭔지안찍혀주냐"+val); 
                        
                        // var3 = $(this).val();
                        // checkList.push($(this).val());
                        // console.log("찍어주라좀"+var3);
                        // console.log("찍어주라좀"+checkList);
                        
                    } else if($('input[name=checkbox]:unchecked')){
                        checkList.pop($(this).val());
                        console.log("이거는없애는거야."+checkList);
                    }
                   
                });//checkbox-

                // $('input[name=checkbox]:checked').each(function(){
                //     checkList.push($(this).val());
                //     console.log("yes:"+$(this).val());
                //     console.log("이거는요"+checkList);
                // })


                // let val3 = $('input[name=checkbox]:checked').val();
                // console.log(val3);

                // if( $('input[name=checkbox]').is(":checked") ){
                //     let checkList = [];

                //     $('#chk input:checked').each(function(){
                //         checkList.push(this.value);
                //         console.log(checkList);
                //     })
                // }
                // console.log(checkList);

                

                // function checkedArray(){
                //     let checkval = $('input[name=checkbx]').each(function(){
                //         checkList.push( $(this).val() );
                //     })
                //     return checkList;
                //     console.log(checkList);
                // };

                // function testCheck(){
                //     let falg = fasle;

                //     let values = document.getElementsByName("checkbx");
                //     alert(values.length);

                //     for(let i=0; i<values.length; i++){
                //         if( values[i].checked){
                //             alert(values[i].values);
                //         }
                //     }
                //     return falg;
                // }

                $('#signupOK').on('click', function(){
                    let tutorOk = $('form');
                    tutorOk.attr('action','/admin/signUp_comfim');
                    tutorOk.attr('method','POST');
                    tutorOk.submit();
                });//signupOK
            });//jq
        </script>

    </head>

    <body>
        <!-- ======= Admin Header ======= -->
        <header id="header" class="header fixed-top">
            <div class="container-fluid container-xl d-flex align-items-center">
                <a href="/admin" class="logo d-flex align-items-center img-fluid animated ">
                    <img src="${path}/resources/img/admin_logo.png" alt=""></a>

                <div class="administrator">
                    <ul>
                        <li><a href="#" onclick="logout()">${_ADMIN_.ad_name}???</a></li>
                    </ul>
                </div>
            </div>
        </header>
        <!-- ======= Admin Header ======= -->

        <!-- ======= Section ======= -->
        <div class="container">
        
        <!-- ======= main Section ======= -->
            <section id="main" class="container">
            <div class="row">
        
            <!-- ========== admin side ========== -->
            <div id="nav" class="col-lg-3">
        
                <div class="list-group">
                    <a href="/admin/student" class="list-group-item list-group-item-action px-lg-4 ">회원 관리</a>
                    <a href="/admin/stator" class="list-group-item list-group-item-action px-lg-4">관리자</a>
                    <a href="/admin/answerBoard_OK" class="list-group-item list-group-item-action px-lg-4">문의 게시판</a>
                    <a href="/admin/adminFAQ" class="list-group-item list-group-item-action px-lg-4 ">자주 묻는 질문</a>
                    <a href="/admin/sale/sell" class="list-group-item list-group-item-action px-lg-4 ">매출 관리</a>
                    <a href="/admin/signUp_comfim" class="list-group-item list-group-item-action px-lg-4 fw-bold ">튜터 가입 승인</a>
                </div>
            </div>

            <div id="contents" class="col-lg-9">

                <div class="tutor-head-title">
                    <ul>
                        <li>
                            <h3 class="fw-bold">튜터 가입 승인</h3>
                        </li>

                        <li>
                            <div class="signup-confirm-tap">
                                <button class="btn btn-dark" id="signupOK">가입 승인</button>
                                <!-- <a href="#" class="btn btn-dark" onclick="signupok()">가입 승인</a> -->
                                <a href="#" class="btn btn-dark" onclick="signupcancle()">가입 거절</a>
                            </div>
                        </li>
                    </ul>
                </div>



                <!-- Content List -->
                <div class="container card p-4 bg-card" id="chk">
                    <table class="table table-hover table-padding">
                        <thead>
                            <tr>
                                <th>
                                    <!-- <form action=""> -->
                                        <input type="checkbox" id="CheckAll" name="CheckAll" value="allid">
                                    <!-- </form> -->
                                </th>
                                <th>이메일</th>
                                <th>닉네임</th>
                                <th>가입일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${_USERLIST_}">
                                <tr>
                                    <form action="">
                                        <td><input type="checkbox" id="checkbox" name="checkbox" value="${user.user_email}"></td>
                                    </form>
                                        <td class="dropdown">
                                            <a href="" class="dropbtn">${user.user_email}</a>
                                            <div class="dropdown-content" id="myDropdown2"><a href="#" onclick="openTutorPop()">회원 정보 조회</a> </div>
                                        </td>
                                        <td>${user.user_nick}</td>
                                        <td><fmt:formatDate pattern="yyyy/MM/dd" value="${user.user_join}" /></td>
                                    </tr>
                                  
                                </c:forEach>
                        </tbody>
                    </table>

                </div>
                <!--End main contents card(박스)-->

                <nav aria-label="Page navigation example">
                    <form action="#" id="adPaginationFrom">
                        <input type="hidden" name="currPage">
                        <input type="hidden" name="amount">

                        <ul class="pagination justify-content-center p-5">

                            <c:if test="${_ADMINPAGINATION_.prev}">
                                <li class="page-item"><a class="page-link rounded-circle" href="${_ADMINPAGINATION_.startPage-1}">&laquo;</a></li>
                            </c:if>

                            <c:forEach var="page" begin="${_ADMINPAGINATION_.startPage}" end="${_ADMINPAGINATION_.endPage}">
                                <li class="page-item"><a class="page-link rounded-circle bg-blue" href="/admin/signUp_comfim?currPage=${page}">${page}</a></li>
                            </c:forEach>
                            <!-- <li class="page-item"><a class="page-link rounded-circle" href="#">&lt;</a></li> -->
                            <!-- <li class="page-item"><a class="page-link rounded-circle bg-blue" href="#">1</a> </li> -->
                            <!-- <li class="page-item"><a class="page-link rounded-circle" href="#">&gt;</a></li> -->

                            <c:if test="${_ADMINPAGINATION_.next}">
                                <li class="page-item"><a class="page-link rounded-circle" href="${_ADMINPAGINATION_.endPage+1}">&raquo;</a></li>
                            </c:if>

                        </ul>
                    </form>
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