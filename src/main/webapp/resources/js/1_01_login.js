function kakaoLogin() {

    $.ajax({
        url: '/test/getKakao',
        type: 'get',
        async: false,
        dataType: 'text',
        success: function (res) {
            location.href = res;
        }
    });//ajax
};//kakao

$(document).ready(function () {

    var kakaoInfo = '${kakaoInfo}';

    if (kakaoInfo != "") {
        var data = JSON.parse(kakaoInfo);

        alert("카카오로그인 성공 \n accessToken : " + data['accessToken']);
        alert(
            "user : \n" + "email : "
            + data['email']
            + "\n nickname : "
            + data['nickname']);
    }
});  
