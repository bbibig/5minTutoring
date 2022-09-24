// 현재 비밀번호 정보가 일치해아만 한다.


function withdrawalbtn() { 

  $(document).ready(function () { 

    const pass = document.querySelector("#user_pw").value;            //현재 비밀번호 값
    let chk1;
      
    // 1. 안내사항 체크 확인
    if($("#item1").is(":checked")){
    
    } else {    // 체크 되어있지 않은 있는경우
      Swal.fire({
          icon: 'warning',
          text: '안내사항에 동의해주세요.',
          confirmButtonText: '확인'
       });
      // alert('안내사항에 동의해주세요.');
        return ;
    } 
    
    // 2. 비밀번호 입력 공백 확인
    if(pass === '') {
      Swal.fire({
        icon: 'warning',
        text: '비밀번호를 입력해주세요.',
        confirmButtonText: '확인'
      });
      return;
    } 
   
  

    $.ajax({
      //4. 현재 비밀번호(DB 확인) 유효성
      url: "/mypage/unregisterConfirm",
      type: "POST",
      dataType: "json",
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
      data: { user_pw : pass },
      success: function (data) {

        if (data == 1) {   
          chk1 = true;
      } else if (data == 0) {
          chk1 = false;
      }
         // 5. 유효성 검사 결과! (UI, Form 제출 유효성)
          if (chk1 === false) {    
            Swal.fire({
              icon: 'warning',
              text: '비밀번호가 일치하지 않습니다.',
              confirmButtonText: '확인'
            });
          } else if (chk1 === true) {   
              Swal.fire({
                icon: 'warning',
                title: '회원탈퇴를 하시겠습니까?',
                showDenyButton: true,
                confirmButtonColor: '#6495ED',
                denyButtonColor: '#E57373',
                denyButtonText: '취소',
                confirmButtonText: '확인'
              }).then((result) => {
                  // 저장확인 누르면 저장완료 창!
                  if (result.isConfirmed) {
                    location.href = '/mypage/unregister/completed'
                  }// if

              })// Swal.fire-then
          }// if-eles if

      }// ajax(success)

  })// ajax
  
  }) 

} 

