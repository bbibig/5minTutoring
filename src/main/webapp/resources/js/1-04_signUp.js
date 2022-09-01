

$('#nickCheck').on('click',function(){
  let newNick = $("#user_nick").val();
  let data = {newNick : newNick}
  
  $.ajax({
    type:"post",
    url:"/login/nick",
    data:data,
    success : function(result){
      if(result!='fail'){
        $('.ni_input_re_1').css("display","inline-block");
        $('.ni_input_re_2').css("display","none");
        Swal.fire('사용 가능한 닉네임 입니다.');
      } else {
        $('.ni_input_re_2').css("display","inline-block");
        $('.ni_input_re_1').css("display","none");
        Swal.fire('중복된 닉네임 입니다.');
      }//if-else
    }
  });//ajax
});//닉네임 중복체크 


$('#signupBtn').on('click', function(){
  if(email_V&&pw_V&&pwcheck_V&&nickName_V&&UserName_V&&birth_V&&
     gender_V&&phone_V&&stSchool_V&&stGrade_V){
      let formObj = $('form');
      formObj.attr('action','/login/signUp_student');
      formObj.attr('method','POST');
      formObj.submit();

      Swal.fire({
        title: "회원가입완료!",
        text: "5분과외 회원가입이 완료되었습니다.",
        icon: "success",
        confirmButtonColor: "#3085d6",
        timer: 1500,
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: '회원가입실패',
        text: '회원가입 양식을 다시 확인해주세요.',
        confirmButtonColor: "#3085d6",
        timer: 1500,
      })
    }//if-else
});//학생 가입 버튼

$('#signupTTBtn').on('click', function(){
  if(email_V&&pw_V&&pwcheck_V&&nickName_V&&UserName_V&&birth_V&&
    gender_V&&phone_V&&ttSchool_V&&ttSubject_V&&file_V){
      let formObj = $('form');
      formObj.attr('action','/login/signUp_tutor');
      formObj.attr('method','POST');
      formObj.attr('enctype','multipart/form-data');
      formObj.submit();
      console.log("왜안날라가요");

      Swal.fire({
        title: "회원가입 신청완료!",
        html: "인증메일이 발송되었습니다.",
        icon: "success",
        confirmButtonColor: "#3085d6",
        timer: 1500,
      });;//-- sweetAlert
    } else {
      Swal.fire({
        icon: 'error',
        title: '회원가입실패',
        text: '회원가입 양식을 다시 확인해주세요.',
        confirmButtonColor: "#3085d6",
        timer: 1500,
      })
    }//if-else
});//튜터 가입 버튼

//----------------- 회원가입 유효성 검사 

let email_V = false;
let pw_V = false;
let pwcheck_V = false;
let nickName_V = false;
let UserName_V = false;
let birth_V = false;
let gender_V = false;
let phone_V = false;
let stSchool_V = false;
let stGrade_V = false;
let ttSchool_V = false;
let ttSubject_V= false;
let ttDepart_V=false;
let file_V = false;

// 이메일 유효성
let emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
$("#user_email").on("input", function () {
  let vaildCheck = emailRegExp.test(this.value);
  if (!vaildCheck) {
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    email_V =false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    email_V =true;
  } //if-else
});////email

//패스워드 유효성
let pwRegExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
$("#user_pw").on("input",function(){
  let vaildCheck = pwRegExp.test(this.value);
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    pw_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    pw_V = true;
  }//if-else
});///password

$("#pwcheck").on("input",function(){
  let pw = $("#user_pw").val();
  let pwc = $("#pwcheck").val();
  if(!(pw==pwc)){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    pwcheck_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    pwcheck_V = true;
  }//if-else
});////pwcheck

//닉네임 유효성
let nickRegExp = /^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,10}$/;
$("#user_nick").on("input",function(){
  let vaildCheck = nickRegExp.test(this.value);
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    nickName_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    nickName_V = true;
  }//if-else
});////nickName

let nameRegExp = /^[가-힝a-zA-Z]{2,}$/;
$("#user_name").on("input",function(){
  let vaildCheck = nameRegExp.test(this.value);
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    UserName_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    UserName_V = true;
  }//if-else
});////userName

$("#user_birth").on("input",function(){
  let vaildCheck = this.value.length===8;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    birth_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    birth_V = true;
  }//if-else
});////user_birth

$("#user_gender").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    gender_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    gender_V = true;
  }//if-else
});////gender

$("#user_phone").on("input",function(){
  let vaildCheck = this.value.length===11;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    phone_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    phone_V = true;
  }//if-else
});////phone

$("#st_school").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    stSchool_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    stSchool_V = true;
  }//if-else
})////stSchool

$("#st_grade").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    stGrade_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    stGrade_V = true;
  }//if-else
})////stGrade

$("#tt_school").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    ttSchool_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    ttSchool_V = true;
  }//if-else
})////ttSchool

$("#tt_subject").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    ttSubject_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    ttSubject_V = true;
  }//if-else
})////ttSubject

$("#tt_depart").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    ttDepart_V = false;
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    ttDepart_V = true;
  }//if-else
})////ttDepart

$("#file").on("input",function(){
  let vaildCheck = this.value.length > 0;
  if(!vaildCheck){
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
    file_V = false;
    Swal.fire({
      text: '증명서를 첨부해주세요.',
      confirmButtonColor: "#3085d6",})
  } else {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid");
    file_V = true;
  }//if-else
})////ttFile

// ------------------------------------------------