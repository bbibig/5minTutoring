<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
	<head>
		<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
			<jsp:include page="../htmlHead.jsp" flush="true" />
			<link href="/resources/css/1-02_login.css" rel="stylesheet">
		<!-- ========================================================= -->

	    <!-- css 추가 -->
	    <link rel="stylesheet" href="/resources/css/2-07_watchAnswer.css">
	    
	    <!-- CKeditor -->
		<script type="text/javascript" src="/resources/js/ckeditor/ckeditor.js"></script>

	    <!-- comment.js -->
		<script type="text/javascript" src="/resources/js/comment.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script type="text/javascript">
				
			// 댓글 등록 테스트
			// console.log("댓글 등록 Test");
			// var a_number = '<c:out value="${_A_.a_number}"/>';
			
			// commentService.add(
			// 	{a_number:a_number, user_email:"test@gmail.com", cm_content:"create comment test."},
			// 	function(result){
			// 		alert("RESULT: " + "댓글 등록 성공");
			// 	}
			// );

			$(document).ready(function() {
				var a_number = "${_A_.a_number}";
				var replyUL = $(".chat");
				
			
				//function showList(currPage) {
					// commentService.getList(
					// 	{ a_number:20, currPage:1 }, 
					// 	function(list) {
					// 		var str = "";
					// 		var commentDiv = $(".comment_box");
							
							
					// 		if(list==null || list.length==0) {
					// 			commentDiv.html("");
					// 			return;
					// 		} // if
					// 		for( var i=0, len=list.length || 0; i<len; ++i ) {
					// 			console.log(list[i]);
					// 			str +=  '<div class="comment_info d-flex" data_cno="'+list[i].cm_number+'">';
					// 			str += '<div class="sSPic">';
					// 			str += '<img src="/resources/img/profile.png">';
					// 			str += '</div>';
					// 			str += '<div class="Sname">'+list[i].user_name+'</div>';
					// 			str += '<div class="date">' +list[i].regdate+ '</div></div>';
					// 			str += '<p>' +${element.cm_content}+ '</p>';
					// 			commentDiv.append(str);
					// 		}
					// 		console.log(i);
					// });
				//}

				showList(1);
        
       		function showList(currPage) {
				commentService.getList({a_number:a_number, currPage:currPage||1}, function(list) {
					var str = "";
					if(list == null || list.length==0) {
						replyUL.html("");
						return;
					}
					for(var i=0, len=list.length || 0; i<len; i++) {
						str+= "<li class='left cleafix' data-rno='"+list[i].cm_number+"'>";
						str+= "    <div><div class='header'><string class='primary-font'>"+list[i].user_name+"</strong>";
						str+= "        <small class='pull-right text-muted'>" + list[i].regdate+"</small></div>";
						str+= "            <p>"+list[i].cm_content+"</p></div></li>";
					}
					
					replyUL.html(str);
				});
        }
    });

		</script>

	
	    <title>튜터페이지</title>
	</head>
	
	<body>
   	    <!-- ============= 공통 Header : 로그인 후 =================== -->
	    <jsp:include page="../header_login.jsp" flush="true" />
	    <!-- ========================================================= -->
	
	    <section class="d-flex align-items-center">
	        <div class="container">
	            <!-- ask -->
	            <div class="row">
	                <div class="col-lg-9">
	                    <div class="ask_card">
	
	                        <!-- 질문글 수정 modal -->
	                        <div class="modal fade" id="reviseQ">
	                            <div class="modal-dialog modal-lg">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
	                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                                    </div>
                                        <form action="modifyAsk?num=${_ONE_Q_.qb_number}" method="post">
                                    		<div class="modal-body">
	                                            <div class="mb-3">
	                                                <label for="recipient-name" class="col-form-label">제목</label>
	                                                <input type="hidden" name="qb_number" value="${_ONE_Q_.qb_number}" class="form-control" id="askTitle"/>
	                                                <input type="text" name="qb_title" value="${_ONE_Q_.qb_title}" class="form-control" id="askTitle"
	                                                    placeholder="제목을 입력해주세요.">
	                                            </div>
	                                            <div class="mb-3">
	                                                <label for="message-text" class="col-form-label">내용</label>
	                                           		<textarea class="form-control" name="qb_content" id="contentsQ" placeholder="내용을 입력해주세요.">${_ONE_Q_.qb_content}</textarea>
		                                            <script>
		                                            	var ckeditor_config = {
															filebrowserUploadUrl: "upload/img",
															font_defaultLabel : "맑은 고딕/Malgun Gothic",
												            fontSize_defaultLabel : "12",
												            language : "ko"
												        };
												        CKEDITOR.replace('contentsQ', ckeditor_config);
													</script>
	                                            </div>
                                    		</div>
		                                    <div class="modal-footer">
		                                        <button type="button" class="btn btn-secondary col-2"
		                                            data-bs-dismiss="modal">취소</button>
		                                        <button type="submit" class="btn btn-primary col-2">저장</button>
		                                    </div>
                                        </form>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <!-- 질문글 삭제 modal -->
	                        <div class="modal fade" id="deleteQ" data-bs-backdrop="static">
	                            <div class="modal-dialog modal-dialog-centered">
	                                <div class="modal-content d-flex flex-column justify-content-center">
	
	                                    <div class="modal-body">
	                                        <div class="pop-up-body d-flex flex-column align-items-center">
	                                            <div class="warnning-img">
	                                                <i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
	                                            </div>
	                                            <p class="my-3 "><strong class="fs-4">삭제하시겠습니까?</strong></p>
	                                            
	                                            <div class="pop-up-button-box d-flex flex-row align-self-center">
	                                                <button type="button" class="btn btn-outline-primary"
	                                                    data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
	                                                <button type="button" onclick="location.href='deleteAsk?num=${_ONE_Q_.qb_number}&tp=${_ONE_Q_.tp_number}'" 
	                                                	class="btn btn-outline-primary">확인</button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <!-- 답변글 수정 modal -->
	                        <div class="modal fade" id="reviseA">
	                            <div class="modal-dialog modal-lg">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLabel"># 수정하기</h5>
	                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                                    </div>
                                        <form action="modifyAnswer?num=${_ONE_Q_.qb_number}" method="post">
                                    		<div class="modal-body">
	                                            <div class="mb-3">
	                                                <label for="message-text" class="col-form-label">내용</label>
	                                                <input type="hidden" name="qb_number" value="${_ONE_Q_.qb_number}" />
	                                                <input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" />
	                                           		<textarea class="form-control" name="a_content" id="contentsA" placeholder="내용을 입력해주세요.">${_A_.a_content}</textarea>
		                                            <script>
														var ckeditor_config = {
															filebrowserUploadUrl: "upload/img",
															font_defaultLabel : "맑은 고딕/Malgun Gothic",
												            fontSize_defaultLabel : "12",
												            language : "ko"
												        };
												        CKEDITOR.replace('contentsA', ckeditor_config);
													</script>
	                                            </div>
                                    		</div>
		                                    <div class="modal-footer">
		                                        <button type="button" class="btn btn-secondary col-2" data-bs-dismiss="modal">취소</button>
		                                        <button type="submit" class="btn btn-primary col-2">저장</button>
		                                    </div>
                                        </form>
	                                </div>
	                            </div>
	                        </div>
	
	                        <!-- 댓글 수정 modal -->
	                        <div class="modal fade" id="comment_revise" data-bs-backdrop="static">
	                            <div class="modal-dialog modal-dialog-centered">
	                                <div class="modal-content d-flex flex-column justify-content-center">
	                                    <div class="modal-body">
	                                        <div class="pop-up-body d-flex flex-column align-items-center">
	
	                                            <p class="my-3 fs-5"># 수정할 댓글을 입력하세요.</p>
	
	                                            <form class="was-validated col-12 d-flex flex-column">
	                                                <div class="text-box">
	                                                    <textarea class="form-control" placeholder="" id="floatingTextarea1"
	                                                        style="height: 150px" required></textarea>
	                                                </div>
	
	                                                <div class="pop-up-button-box align-self-end">
	                                                    <button type="button" class="btn btn-outline-primary"
	                                                        data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
	                                                    <button type="submit" class="btn btn-outline-primary">확인</button>
	                                                </div>
	                                            </form>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	
	                        <!-- 댓글 삭제 modal -->
	                        <div class="modal fade" id="delete" data-bs-backdrop="static">
	                            <div class="modal-dialog modal-dialog-centered">
	                                <div class="modal-content d-flex flex-column justify-content-center">
	
	                                    <div class="modal-body">
	                                        <div class="pop-up-body d-flex flex-column align-items-center">
	                                            <div class="warnning-img">
	                                                <i class="bi bi-exclamation-circle" style="font-size: 5rem"></i>
	                                            </div>
	
	                                            <p class="my-3 "><strong class="fs-4">삭제하시겠습니까?</strong></p>
	
	                                            <div class="pop-up-button-box d-flex flex-row align-self-center">
	                                                <button type="button" class="btn btn-outline-primary"
	                                                    data-bs-dismiss="modal">취소</button>&nbsp;&nbsp;&nbsp;
	                                                <button type="button" class="btn btn-outline-primary">확인</button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	
							<!-- question -->
							<hr>
	                        <h2 class="fas fa-q ask_title">. ${_ONE_Q_.qb_title}</h2>
	                        <div class="student_info d-flex">
	                            <div class="SPic">
	                                <img src="/resources/img/profile.png" alt="튜터프로필">
	                            </div>
	                            <div class="Sname">${_ONE_Q_.user_name}</div>
	                            <div>&nbsp;학생</div>
	                            <br>
	                            <div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_ONE_Q_.regdate}"/></div>
	                            
	                            <div class="hamburger-button col-8 d-flex justify-content-end">
	                                <div class="dropdown">
	                                    <button class="btn pt-0" type="button" data-bs-toggle="dropdown"
	                                        aria-expanded="false">
	                                        <i class="bi bi-list fs-4"></i>
	                                    </button>
	                                    <ul class="dropdown-menu">
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#reviseQ">수정</a>
	                                        </li>
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#deleteQ">삭제</a>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="ask_content">${_ONE_Q_.qb_content}</div>
	                    </div>
	                </div>
	            </div>
	            
	            <!-- answer -->
	            <div class="row">
	                <div class="col-lg-9 answer">
	                    <div class="Tutor_info d-flex">
	                        <div class="TPic">
	                            <img src="/resources/img/profile.png" alt="튜터프로필">
	                        </div>
	                        <div class="Tname">${_A_.user_name}</div>
	                        <div>&nbsp;튜터</div>
	                        <br>
	                        <div class="date">&nbsp;<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${_A_.regdate}"/></div>
	                        <div class="hamburger-button col-8 d-flex justify-content-end">
	                            <div class="dropdown">
	                                <button class="btn pt-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
	                                    <i class="bi bi-list fs-4"></i>
	                                </button>
	                                <ul class="dropdown-menu">
                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
                                                href="#reviseA">수정</a>
                                        </li>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                    <p class="answer_content">${_A_.a_content}</p>
	
						   
	                    <!-- comment -->
	                    <span class="bi bi-chat-right-dots fs-4 comment_icon"><span> 3</span></span>
	                    <div class="comment_box">
	                        <div class="comment d-flex">
	                            <div class="sSPic">
	                                <img src="/resources/img/profile.png">
	                            </div>
	                            <form action="/comment/new" method="post">
	                            	<input type="hidden" name="a_number" value="${_A_.a_number}" />
	                            	<input type="hidden" name="user_email" value="${__LOGIN_USER__.user_email}" />
	                                <input id="comment_write" name="cm_content" type="text" size="80" placeholder="댓글을 입력하세요.">
	                                <input id="save" type="submit" value="등록">
	                            </form>
	                        </div>
	                        
	                        <!-- <div class="comment_info d-flex">
	                            <div class="sSPic">
	                                <img src="/resources/img/profile.png">
	                            </div>
	                            <div class="Sname">${_COMMENT_.user_name}</div>
	                            <div class="date">${_COMMENT_.regdate}</div>
	                            <div class="hamburger-button col-9 d-flex justify-content-end">
	                                <div class="dropdown">
	                                    <button class="btn pt-0" type="button" data-bs-toggle="dropdown"
	                                        aria-expanded="false">
	                                        <i class="bi bi-list fs-4"></i>
	                                    </button>
	                                    <ul class="dropdown-menu">
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#comment_revise">수정</a>
	                                        </li>
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#delete">삭제</a>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                        <p>${_COMMENT_.cm_content}</p> -->
	                        
	                        <hr>
	                        
	                        
	                        <div class="comment_info d-flex" data_cno="22">
	                            <div class="sSPic">
	                                <img src="/resources/img/profile.png">
	                            </div>
	                            <div class="Sname">박튜터</div>
	                            <div class="date">2022-07-22</div>
	                            <div class="hamburger-button col-9 d-flex justify-content-end">
	                                <div class="dropdown">
	                                    <button class="btn pt-0" type="button" data-bs-toggle="dropdown"
	                                        aria-expanded="false">
	                                        <i class="bi bi-list fs-4"></i>
	                                    </button>
	                                    <ul class="dropdown-menu">
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#comment_revise">수정</a>
	                                        </li>
	                                        <li class="list-unstyled"><a class="dropdown-item" data-bs-toggle="modal"
	                                                href="#delete">삭제</a>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quaerat, eveniet.</p>
	                   
	                   
	                    </div>
	                    
	                    
	                </div>
	            </div>

				<!-- 댓글 테스트 -->
				<div class="row">
			    <div class="col-lg-12">
			        <div class="panel panel-default">
			                <div class="panel-heading">
			                    <i claass="fa fa-comments fa-fw"></i> Reply
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                    <ul class="chat">
			                        <!-- start reply -->
			                        <li class="left clearfix" data-rno='12'>
			                            <div>
			                                <div class="header">
			                                    <strong class="primary-font">user00</strong>
			                                    <small class="pull-right text-muted">2021-03-18 18:13</small>
			                                </div>
			                                <p>Good job!</p>
			                            </div>
			                        </li>
			                        <!--  end reply -->
			                    </ul>
			                    <!--  ./end ul -->
			                </div>
			                <!-- ./ end row -->
			            </div>
			        </div>            
			<!-- /.row -->
			</div>
				


	        </div>
	    </section>
	
	    <!-- ============= 공통 footer + js =============== -->
	    <jsp:include page="../footer.jsp" flush="true" />
	    <!-- ============================================== -->

	</body>

</html>