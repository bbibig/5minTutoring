// function All_costom() {
//   Swal.fire({
//     title: '회원가입 요청완료!', // Alert 제목
//     text: '승인이 완료되면 메일로 안내드립니다', // Alert 내용
//     icon: 'success',      // Alert 타입
//     showConfirmButton: true,
//     showCancelButton: true,
//     confirmButtonText: `Save`,
//     denyButtonText: `Don't save`,
//      customClass : 'sweet-size',
//   }).then(function (isConfirm) {
//     if (isConfirm) {
//       document.location.href="main_TT.html";
//       Swal.fire('Saved!', '', 'success')
//     } else {
//       Swal.fire('Changes are not saved', '', 'info')
//     }
//   });
// };//login

function login() {
  Swal.fire({
    title: '회원가입완료!', // Alert 제목
    text: '오분과외에 오신걸 환영합니다.', // Alert 내용
    icon: 'success',      // Alert 타입
  }).then(function(){
    // document.location.href="1-01_homeST.html"
  });
};//login

function loginTutor() {
  Swal.fire({
    title: '회원가입 신청완료!', // Alert 제목
    text: '승인이 완료되면 메일로 안내드립니다.', // Alert 내용
    icon: 'success',      // Alert 타입
  }).then(function(){
    document.location.href="1-01_homeTT.html"
  });
};//login


function nickcheck() {
  Swal.fire({
    title: '사용하실 닉네임을 입력하세요.',
    input: 'text',
    // inputAttributes: {
    //   autocapitalize: 'on'
    // },
    showCancelButton: true,
    confirmButtonText: 'Check!',
    showLoaderOnConfirm: false,
    preConfirm: (login) => {
      return fetch(`//api.github.com/users/${login}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(response.statusText)
          }
          return response.json()
        })
        .catch(error => {
          Swal.showValidationMessage(
            `다시 입력하세요.`
          )
        })
    },
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        text: '중복확인 되었습니다.',
        icon: 'success',
      })
    }
  })
};//nicknameCheck 

