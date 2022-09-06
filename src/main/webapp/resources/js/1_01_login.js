// const CLIENT_ID = "f242881542c06c438c6f81728a868bf9";
// const REDIRECT_URI = "http://localhost:8080/test/getKakao";

// export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

// import { KAKAO_AUTH_URL } from "../shared/OAuth";

const naverLogin = new naver.LoginWithNaverId(
    {
        clientId: "m64F8j6rzmuQpJTdLytb", 
        callbackUrl: "http://localhost:8080",
        isPopup:false,
        callbackHandle:true
        }
    );


naverLogin.init();  //초기화

naverLogin.getLoginStatus(function (status) {
if (status) {
    const nickName=naverLogin.user.getNickName();
    const age=naverLogin.user.getAge();
    const birthday=naverLogin.user.getBirthday();
    const email=naverLogin.user.getEmail();
    console.log(naverLogin.user);

    if(email===null||email===undefined ){
        alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
        naverLogin.reprompt();
        return ;  
    }else{
    console.log("callback 처리에 실패하였습니다.");
    }
}
});

let testPopup;

function openPopUp() {
testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}

function closePopUp(){
    testPopUp.close();
}

function naverLogout() {
    openPopUp();
    setTimeout(function() {
        closePopUp();
        }, 1000);


}

function setLoginStatus(){

    const message_area=document.getElementById('message');
    message_area.innerHTML=`
    <h3> Login 성공 </h3>
    <div>user Nickname : ${naverLogin.user.nickname}</div>
    <div>user Age(범위) : ${naverLogin.user.age}</div>
    <div>user Birthday : ${naverLogin.user.birthday}</div>
    `;
}