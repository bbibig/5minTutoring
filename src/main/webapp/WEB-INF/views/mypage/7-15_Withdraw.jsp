<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
<jsp:include page="../htmlHead.jsp" flush="true" />
<!-- =======mypage 공통 CSS=================================== -->
<link href="${path}/resources/css/mypage.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

<title>출금신청</title>

<script>
$(function(){

$('input.hands').on('keyup',function(){
		  var cnt = $("input.num_sum").length;     
		  console.log(cnt);
		  
  for( var i=1; i< cnt; i++){
	 var sum = parseInt($(this).val() || 0 );
	 sum++
	console.log(sum);
  }
  
		var sum1 = parseInt($("#price").val() || 0 ); 

		var sum = sum1 * 180 - 2000;
		console.log(sum);
		$("#total").val(sum);
		});

});
</script>


</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Hero Section ======= -->

	<section id="main" class="container">
		<div class="container">
			<div class="row justify-content-md-center">


				<div id="contents" class="col-lg-8">

					<h2 id="withdrawTitle" class="fw-bold text-center">출금신청</h2>

					<div id="withdraw" class="container">

						<!-- 튜터 보유자산 박스-->
						<form action="/mypage/withdrawAppilcation" method="post">
						
							<div class="withdrawInfo">
								<h5 class="text-center" style="color: rgb(103, 102, 102);">${__LOGIN_USER__.user_nick}
									튜터님의 보유자산</h5>
								<div class="d-flex flex-row justify-content-center">
									<p class="ownHands fw-bold col-3">${__LOGIN_USER__.hands_wallet}개 손들기</p>
									<p class="price col-2">
										= <fmt:formatNumber value="${__LOGIN_USER__.hands_wallet*180}" type="number"/>원
									<p>
								</div>
							</div>

							<!-- 출금 수량, 개수 입력폼 -->
						
							<div class="d-flex flex-row justify-content-center" id="account">
								<div class="col-2">
									<label for="address">출금 계좌</label>
								</div>
								<div class="col-10">
									<input type="text" class="form-control" id="address" name="bank_account"
										placeholder="출금 계좌번호를 입력해주세요" required>
								</div>
							</div>
							<div class="max_amount">1일 최대 출금 가능 수량: 500개</div>
							<div class="d-flex flex-row justify-content-center" id="amount">
								<div class="col-2">
									<label for="address">출금 수량</label>
								</div>
								<div class="col-10">
									<input type="text" class="form-control hands hands_sum" id="price" name="w_quantity"
										placeholder="최소 출금 수량: 손들기 50개" required>
								</div>

								<input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}">
								<input type="hidden" name="approval" value="승인 대기">
								
							</div>
								<!-- 출금 수수료 -->
								<div class="row" id="fee">
									<div class="fee col-6">출금 수수료</div>
									<div class="won col-6">2,000원</div>
								</div>
								<hr>

								<div class="d-flex flex-row justify-content-center"> 
									<div class="fee col-9" style="padding-top:5px">출금 신청액</div>
									<div class="won col-2" style="mergin-left:12px">
										<input type="text" class="form-control hands hands_sum"  id="total" name="w_price" dir="rtl" required>
									</div>
									 <div class="won col-1" style="padding-right:16px; padding-top:5px" >원</div> 
								</div>

							

							<!-- 출금 유의사항 -->
							<div class="check">
								<div>
									<i class="bi bi-exclamation-circle-fill" style="float: left;"></i>
									<h6>출금 유의사항을 확인해주세요.</h6>
								</div>
								<ul>
									<li>손들기 1개당 180원으로 환전됩니다.</li>
									<li>출금가능 손들기는 50개 이상이어야 가능합니다.</li>
									<li>출금 신청이 완료되면 취소할 수 없습니다. 신청 전 수량이 맞는지 꼭 확인해주세요.</li>
									<li>은행 서비스 점검시간(23:00~00:30)내에는 출금신청이 어려울 수 있습니다.</li>
									<li>매주 목요일 19:00 이전으로 입금됩니다.</li>
								</ul>
							</div>

							<!-- 취소, 신청 버튼 -->
							<div class="d-flex justify-content-center">
								<a class="btn btn-outline-dark" id="cancel" style="margin-right:5px"
									href="/mypage/tutorHands/withdraw" role="button">취소</a> 
								<a href="/mypage/withdraw/completed">
									<button type="submit" class="btn btn-dark" style="margin-left:5px" id="apply">신청</button>
								</a>
	
							</div>
						</form>
					</div>

				</div>
				<!-- End main contents -->
			</div>
		</div>
		<!--End card-->
	</section>
	<!-- End main Section -->


	<!-- ======= End Hero Section ======= -->


	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
	<script src="${path}/resources/js/7-15_mypage_withdrawal.js"></script>
</body>

	<!--	<script> 제이쿼리 실행 되는지 확인하고 지우기 
		$('#hands').on('change','#result', function() {

		$('#result').val( Number($('#hands').val()) * 180 )

		});
	 </script> -->
</html>
