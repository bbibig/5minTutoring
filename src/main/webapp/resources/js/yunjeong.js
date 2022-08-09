function loginbt() {
    Swal.fire({
        text: '아이디 또는 비밀번호가 일치하지 않습니다', // Alert 내용
        icon: 'warning',      // Alert 타입
        showCloseButton: true
    });
};//login


function findmypwbt() {
    Swal.fire({
        title: 'abcdefg@gmail.com' ,
        text: '이 주소로 임시 비밀번호가 전송 되었습니다.',
        icon: 'success',      // Alert 타입
        confirmButtonText: '<a href="1-02_login.html">로그인하러 가기</a>',
        confirmButtonAriaLabel: '',
        showCloseButton: true
    });
    // if(){

    // } else {

    // }
};//login

function findmypwNotfoundbt() {
    Swal.fire({
        title: '안내' ,
        text: '일치하는 정보가 없습니다.',
        icon: 'error',      // Alert 타입
        confirmButtonText: '확인',
        showCloseButton: true
    });
    // if(){

    // } else {

    // }
};//login



// function nickcheck(){
//     Swal.fire({
//         position: 'top-end',
//         icon: 'success',
//         title: 'Your work has been saved',
//         showConfirmButton: false,   //버튼보이기
//         timer: 1500
//     })
// };//nickcheck