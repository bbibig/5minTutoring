/* ============================= */
/*      기본정보(7-01,7-02)      */
/* ============================= */

/* ============================= */
/*      파일첨부 미리보기         */
/* ============================= */
const realFile = document.querySelector(".file_name");
const profileImg = document.querySelector(".profileImg");

// 첨부파일 가져오는 function
function getProfileImgFile(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;

    [...files].forEach(file => {

        //두개 이상 올리면 return
        if ([...files].length >= 2) {
            return;
        }//if

        //이미지 파일이 아니면 경고창뜨고 return처리
        if (!file.type.match("image/.*")) {
            Swal.fire({
                icon: 'warning',
                title: '이미지 파일을 업로드해 주세요.'
            })
            return;
        }// if

        uploadFiles.push(file);

        const reader = new FileReader();
        reader.onload = e => {
            const prieview = createElement(e, file);
            profileImg.appendChild(prieview);
        };

        reader.readAsDataURL(file);

    });

}// getProfileImgFile

//<img>태그 생성 funtion
function createElement(e, file) {
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('date-file', file.name);

    profileImg.appendChild(img);
}// createElement()

// 프로필 사진 클릭 = input.file 클릭
profileImg.addEventListener('click', () => realFile.click());

//사진 변경
realFile.addEventListener('change', getProfileImgFile)


/* ============================= */
/*    비밀번호 변경 유효성        */
/* ============================= */

function pwCheck() {

    $(document).ready(function () {
        let userForm = document.querySelector("#userInfo");

        const pass = document.querySelector("#user_pw").value;            //현재 비밀번호 값
        let chk1;

        const pass1 = document.querySelector("#user_newPw").value;        //새로운 비밀번호 값
        const pass2 = document.querySelector("#user_newPw2").value;       //새로운 비밀번호 확인 값
        const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,15}$/;       // 영문.숫자 8~15자리
    
        const check1 = document.querySelector("#check_1").classList;
        const check2 = document.querySelector("#check_2").classList;
    
        //1. 현재 비밀번호 공백
        if(pass === ''){
            Swal.fire({
                icon: 'warning',
                text: '현재 비밀번호를 입력해주세요.',
                confirmButtonText: '확인'
            })
            return;
        }
    
        //2. 새로운 비밀번호 공백
        if ((pass1 === '') || (pass2 === '')) {
            check2.add("invisible");
            check2.remove("visible");
            Swal.fire({
                icon: 'warning',
                text: '변경할 비밀번호를 입력해주세요.',
                confirmButtonText: '확인'
            });
            return;    
        }

        //2-2 새로운 비밀번호 유효성(영문.숫자 8~15자리)
        if(!regExp.test(pass1)){
            Swal.fire({
                icon: 'warning',
                text: '영문 숫자 포함8~15자로 입력해주세요.',
                confirmButtonText: '확인'
            });
            return;  
        }
    
        //3.새로운 비밀번호와 새로운 비밀번호 확인 유효성
        if (pass1 === pass2) {    //같으면
            check2.add("invisible");
            check2.remove("visible");
        } else if (pass1 !== pass2) {
            check2.add("visible");
            check2.remove("invisible");
            return;
        }// check()

        $.ajax({
            //4. 현재 비밀번호(DB 확인) 유효성
            url: "/mypage/pwCheck",
            type: "POST",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: $("#userInfo").serializeArray(),
            success: function (data) {

                if (data == 1) {    //같으면
                    chk1 = true;
                } else if (data == 0) {
                    chk1 = false;
                }// if-else

                //5. 유효성 검사 결과! (UI, Form 제출 유효성)
                if (chk1 === false) {    //현재 비밀번호(DB 확인) 다르면
                    check1.add("visible");
                    check1.remove("invisible");
                } else if (chk1 === true) {   //유효성을 모두 충족시키면 제출(1~4)
                    check1.add("invisible");
                    check1.remove("visible");
                    Swal.fire({
                        icon: 'warning',
                        title: '기본정보 수정',
                        showDenyButton: true,
                        confirmButtonText: '확인',
                        denyButtonText: `취소`,
                        text: '수정하시겠습니까?',
                    }).then((result) => {
                        // 저장확인 누르면 저장완료 창!
                        if (result.isConfirmed) {
                            Swal.fire({
                                icon: 'success',
                                text: '수정 완료!',
                                confirmButtonText: '확인'
                            }).then(function (e) {
                                userForm.submit();
                            })// Swal.fire-then
                        }// if

                    })// Swal.fire-then
                }// if-eles if

            }// ajax(success)

        })// ajax

    })// jquery

}//pwCheck