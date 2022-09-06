function withdraw() {
    Swal.fire({
        text: '출금 신청을 진행하시겠습니까?',

        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Ok'
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(
            '출금 완료',
            '출금 신청이 완료되었습니다.',
            'success'
          )
        }
      })
};//withdraw


function signupok() {
    Swal.fire({
        text: '가입 신청을 승인하시겠습니까?',

        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Ok'
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(
            '가입 완료',
            '가입이 완료되었습니다.',
            'success'
          )
        }
      })
};//signupok


function signupcancle() {
    Swal.fire({
        text: '가입 신청을 거절하시겠습니까?',

        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Ok'
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(
            '가입 거절',
            '가입이 거절되었습니다.',
            'success'
          )
        }
      })
};//signupcancle


// 8-5 faq 수정 버튼(6개..)
function modifybt1() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm1');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()

function modifybt2() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm2');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()

function modifybt3() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm3');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()

function modifybt4() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm4');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()

function modifybt5() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm5');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()

function modifybt6() {

  Swal.fire({
    title: '게시글을 수정하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {

    if (result.isConfirmed) {
      let formObj = $('#faqForm6');

      formObj.attr('action', '/admin/adminFAQModify');
      formObj.attr('method', 'post');
      formObj.submit();
    }// if(수정폼 제출)

  }) // swal.fire-then
}// modifybt()


// 8-5 faq 삭제 버튼(6개..)
function deletebt1() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm1');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()

function deletebt2() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm2');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()

function deletebt3() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm3');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()

function deletebt4() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm4');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()

function deletebt5() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm5');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()

function deletebt6() {

  Swal.fire({
    title: '게시글을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    cancelButtonText: '취소',
    confirmButtonText: '확인'
  }).then((result) => {
    
    if (result.isConfirmed) {
      Swal.fire({
        icon: 'success',
        title: '게시글이 삭제되었습니다.',
      }).then(function(e) {
        let formObj = $('#faqForm6');

        formObj.attr('action', '/admin/adminFAQRemove');
        formObj.attr('method', 'post');
        formObj.submit();
      });// 삭제 폼 제출

    }// if
  }) // swal.fire-then
}// deletebt()



//관리자로그인
function loginbt() {
  Swal.fire({
      text: '${_ADMIN_.ad_name}'+'님 로그인되었습니다.',
      icon: 'info',  
      showCloseButton: true
  });
};//login

//관리자로그아웃
function logout() {
  Swal.fire({
      text: '로그아웃하시겠습니까?',
      icon: 'question',  
      showCloseButton: true
  }).then(function(){
    document.location.href="/admin";
  });
};//login

//회원정보조회
let popup;
function openUserPop() {
    popup = window.open("/admin/UserInfo", "이거뭐지?",
        "width=310, height=400, left=1000, top=100, status=0 location=0",
        scrollbars = "no");
}//openUserPop;

function closeUserPop() {
    popup = window.close();
}//closeUserPop

//튜터승인 정보 조회
function openTutorPop() {
    popup = window.open("/admin/tutorInfo", "이거뭐지?",
        "width=310, height=400, left=1000, top=100, status=0 location=0",
        scrollbars = "no");
}//openUserPop;