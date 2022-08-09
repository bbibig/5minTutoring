// function withdrawalbt() {
//     Swal.fire({
//         title: '회원탈퇴를 하시겠습니까?',
//         // text: "You won't be able to revert this!",
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         cancelButtonText: '취소',
//         confirmButtonText: '탈퇴하기'
//       }).then((result) => {
//         if (result.isConfirmed) {
//           Swal.fire(
//             '탈퇴가 완료되었습니다.',
//             '오분과외를 이용해주셔서 감사합니다.',
//             'success'
//           )
//         }
//       })
// }


function withdrawalbt() {
  Swal.fire({
      title: '회원탈퇴를 하시겠습니까?',
      // text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: '취소',
      confirmButtonText: '탈퇴하기'
    }).then((result) => {
      if (result.isConfirmed) {
        newPage()
      }
    })

}

function newPage()  {
window.location.href = '/mypage/unregister/completed'
}
